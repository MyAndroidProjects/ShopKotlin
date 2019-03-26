package com.study.riseof.shopkotlin.activity.main

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.support.v4.app.FragmentManager
import android.util.Log
import com.study.riseof.shopkotlin.R
import com.study.riseof.shopkotlin.SharedPreferencesInfo
import com.study.riseof.shopkotlin.activity.shopping_cart.ShoppingCartActivity
import com.study.riseof.shopkotlin.fragment.catalog.CatalogFragment
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.model.database.DatabasesManager
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo
import com.study.riseof.shopkotlin.navigation.NavigationContract
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

object MainActivityPresenter : MainActivityContract.Presenter, NavigationContract.MainActivityPresenter {

    private var view: MainActivityContract.View? = null
    private val navigator: MainActivityContract.Navigator = MainActivityNavigator
    private val databasesManager: MainActivityContract.Model = DatabasesManager()
    private var currentSelectedProduct: Product? = null

    override fun setViewToPresenter(view: MainActivityContract.View?) {
        MainActivityPresenter.view = view
    }

    override fun activityIsOnStart(context: Context) {
        val prefs = context.getSharedPreferences(SharedPreferencesInfo.FILE_NAME, MODE_PRIVATE)
        if (prefs.getBoolean(SharedPreferencesInfo.KEY_APPLICATION_FIRST_LAUNCH, true)) {
            Log.d("myLog", " FIRST_LAUNCH ")
            databasesManager.fillShopDatabaseTables(context)
            prefs.edit().putBoolean(SharedPreferencesInfo.KEY_APPLICATION_FIRST_LAUNCH, false).apply()
        } else {
            Log.d("myLog", " NOT FIRST_LAUNCH ")
        }
    }

    override fun applicationIsStarting(context: Context) {
        navigator.createFragment(CatalogFragment.getInstance())
        databasesManager.deleteShoppingCartDatabase(context)
    }

    override fun menuButtonHomeSelected() {
        view?.openDrawer()
    }

    override fun backButtonSelected(supportFragmentManager: FragmentManager?) {
        view?.closeDrawer()
        val count = supportFragmentManager?.backStackEntryCount
        navigator.callSuperOnBackPressed()
        when (count) {
            2 -> navigator.cleanBackStack()
            1 -> navigator.callSuperOnBackPressed()
        }
        if (count == 2) {
            Log.d("myLog", " backButtonSelected cleanBackStack")
        }
        if (count == 1) {
            Log.d("myLog", " callSuperOnBackPressed ДВА РАЗА")
        } else {
            Log.d("myLog", " callSuperOnBackPressed ОДИН РАЗ")
        }
    }

    override fun fabBackSelected() {
        view?.hideProductInfoButtons()
        currentSelectedProduct = null
        navigator.callSuperOnBackPressed()
    }

    override fun fabAddToShoppingCartSelected(context: Context) {
       // view?.hideProductInfoButtons()
        // TODO увеличить количество товаров в корзине
        addProductToShoppingCartDatabase(
            context,
            currentSelectedProduct
        )
        // todo snack bar с надписью о добавлении
        view?.showSnackBar(currentSelectedProduct?.price.toString())

        currentSelectedProduct = null
        view?.hideProductInfoButtons()
        navigator.callSuperOnBackPressed()
    }

    private fun addProductToShoppingCartDatabase(context: Context, product: Product?) {
        product ?: return
        lateinit var type: String
        when (product.tableName) {
            ShopDatabaseInfo.TABLE_SMARTPHONES ->
                type = context.resources.getString(R.string.product_type_smartphone)
            ShopDatabaseInfo.TABLE_GRAPHIC_TABLETS ->
                type = context.resources.getString(R.string.product_type_graphic_tablet)
            ShopDatabaseInfo.TABLE_LAPTOPS ->
                type = context.resources.getString(R.string.product_type_laptop)
            ShopDatabaseInfo.TABLE_CAMERAS ->
                type = context.resources.getString(R.string.product_type_camera)
            ShopDatabaseInfo.TABLE_SPEAKERS ->
                type = context.resources.getString(R.string.product_type_speakers)
            ShopDatabaseInfo.TABLE_HEADPHONES ->
                type = context.resources.getString(R.string.product_type_headphones)
            ShopDatabaseInfo.TABLE_MICROPHONES ->
                type = context.resources.getString(R.string.product_type_microphone)
            ShopDatabaseInfo.TABLE_FLASH_DRIVES ->
                type = context.resources.getString(R.string.product_type_flash_drive)
        }
        databasesManager.putProductToShoppingCartDatabase(context, product, type)
        //todo добавить snake bar c продуктом
    }

    override fun fabShoppingCartSelected(context: Context) {
        startShoppingCart(context)
    }

    override fun productsQuantityViewSelected(context: Context) {
        startShoppingCart(context)
    }

    private fun startShoppingCart(context: Context) {
        view?.hideProductInfoButtons()
        currentSelectedProduct = null
        val list = databasesManager.getProductListFromShoppingCartDatabase(context)
        navigator.startShoppingCartActivity(list)
    }

    // реализация методов interface NavigationContract.MainActivityPresenter

    override fun closeDrawerLayout() {
        view?.closeDrawer()
    }

    override fun showProductInfoButtons() {
        view?.showProductInfoButtons()
    }

    override fun selectedProduct(product: Product) {
        currentSelectedProduct = product
    }
}