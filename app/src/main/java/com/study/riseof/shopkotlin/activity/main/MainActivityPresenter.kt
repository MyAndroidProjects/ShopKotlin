package com.study.riseof.shopkotlin.activity.main

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.support.v4.app.FragmentManager
import android.util.Log
import com.study.riseof.shopkotlin.R
import com.study.riseof.shopkotlin.SharedPreferencesInfo
import com.study.riseof.shopkotlin.fragment.catalog.CatalogFragment
import com.study.riseof.shopkotlin.fragment.product_list.ProductListFragment
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.model.database.DatabasesManager
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo
import com.study.riseof.shopkotlin.navigation.NavigationContract

object MainActivityPresenter : MainActivityContract.Presenter, NavigationContract.MainActivityPresenter {

    const val KEY_TO_DELETE_SHOPPING_CART_DATABASE = "toDeleteShoppingCartDatabase"
    const val KEY_TO_CREATE_CATALOG_FRAGMENT = "toCreateCatalogFragment"
    const val KEY_PRODUCT_LIST_FRAGMENT_TYPE = "productListFragmentType"
    const val KEY_START_SNACK_BAR_MESSAGE = "startSnackBarMessage"

    private var view: MainActivityContract.View? = null
    private val navigator: MainActivityContract.Navigator = MainActivityNavigator
    private var currentSelectedProduct: Product? = null
    private var productsQuantity: Int = 0

    enum class ProductListFragmentType {
        NON,
        CAMERAS,
        FLASH_DRIVES,
        GRAPHIC_TABLETS,
        HEADPHONES,
        LAPTOPS,
        MICROPHONES,
        SMARTPHONES,
        SPEAKERS
    }

    // Implements function in interface MainActivityContract.Presenter

    override fun setViewToPresenter(view: MainActivityContract.View?) {
        MainActivityPresenter.view = view
        ProductListFragmentType.CAMERAS.ordinal

    }

    override fun activityIsOnStart(
        context: Context,
        toDeleteShoppingCartDatabase: Boolean,
        toCreateCatalogFragment: Boolean,
        productListFragmentType: Int,
        startSnackBarMessage: String?
    ) {
        if (isApplicationFirstLaunch(context)) {
            val databasesManager: MainActivityContract.Model = DatabasesManager()
            databasesManager.fillShopDatabaseTables(context)
            Log.d("myLog", "isApplicationFirstLaunch  true")
        }
        if (toDeleteShoppingCartDatabase) {
            Log.d("myLog", "toDeleteShoppingCartDatabase  true")
            val databasesManager: MainActivityContract.Model = DatabasesManager()
            //  databasesManager.deleteShoppingCartDatabase(context)
            databasesManager.deleteAllInShoppingCartDatabase(context)
        }
        if (toCreateCatalogFragment) {
            navigator.createFragment(CatalogFragment.getInstance())
            Log.d("myLog", "toCreateCatalogFragment  true")
        }
        if (startSnackBarMessage != null) {
            view?.showSnackBar(startSnackBarMessage)
            view?.setStartSnackBarMessage(null)
        }
        productsQuantity = getProductQuantityFromShoppingCartDatabase(context)
        setTextToToolbar(productsQuantity.toString())
        startCreationProductListFragment(context, productListFragmentType)
        view?.setMainActivityFlagsToFalse()
    }

    override fun menuButtonHomeSelected() {
        view?.openDrawer()
    }

    override fun backButtonSelected(supportFragmentManager: FragmentManager?) {
        view?.closeDrawer()
        view?.hideProductInfoButtons()
        currentSelectedProduct = null
        val count = supportFragmentManager?.backStackEntryCount
        navigator.callSuperOnBackPressed()
        when (count) {
            2 -> navigator.cleanBackStack()
            1 -> navigator.callSuperOnBackPressed()
        }
    }

    override fun fabBackSelected() {
        view?.hideProductInfoButtons()
        currentSelectedProduct = null
        navigator.callSuperOnBackPressed()
    }

    override fun fabAddToShoppingCartSelected(context: Context) {
        addProductToShoppingCartDatabase(
            context,
            currentSelectedProduct
        )
        currentSelectedProduct = null
        productsQuantity++
        setTextToToolbar(productsQuantity.toString())
        view?.hideProductInfoButtons()
        navigator.callSuperOnBackPressed()
    }

    override fun fabShoppingCartSelected(context: Context) {
        startShoppingCartActivity(context)
    }

    override fun productsQuantityViewSelected(context: Context) {
        startShoppingCartActivity(context)
    }

    override fun closeDrawerLayout() {
        view?.closeDrawer()
    }

    // Implements function in interface NavigationContract.MainActivityPresenter
    override fun hideProductInfoButtons() {
        view?.hideProductInfoButtons()
    }

    override fun showProductInfoButtons() {
        view?.showProductInfoButtons()
    }

    override fun selectedProduct(product: Product) {
        currentSelectedProduct = product
    }


    private fun getProductQuantityFromShoppingCartDatabase(context: Context): Int {
        val databasesManager: MainActivityContract.Model = DatabasesManager()
        return databasesManager.getProductQuantityFromShoppingCartDatabase(context)
    }

    private fun setTextToToolbar(text: String) {
        view?.setToolbarText(text)
    }

    private fun startCreationProductListFragment(context: Context, productListFragmentType: Int) {
        var productList: ArrayList<Product>? = null
        when (productListFragmentType) {
            ProductListFragmentType.NON.ordinal
            -> return
            ProductListFragmentType.CAMERAS.ordinal
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_CAMERAS)
            ProductListFragmentType.FLASH_DRIVES.ordinal
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_FLASH_DRIVES)
            ProductListFragmentType.GRAPHIC_TABLETS.ordinal
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_GRAPHIC_TABLETS)
            ProductListFragmentType.HEADPHONES.ordinal
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_HEADPHONES)
            ProductListFragmentType.LAPTOPS.ordinal
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_LAPTOPS)
            ProductListFragmentType.MICROPHONES.ordinal
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_MICROPHONES)
            ProductListFragmentType.SMARTPHONES.ordinal
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SMARTPHONES)
            ProductListFragmentType.SPEAKERS.ordinal
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SPEAKERS)
        }
        productList ?: return
        Log.d("myLog", "startCreationProductListFragment  true")
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    private fun getProductListFromShopDatabase(context: Context, tableName: String): ArrayList<Product> {
        val databasesManager: MainActivityContract.Model = DatabasesManager()
        return databasesManager.getProductListFromShopDatabase(context, tableName)
    }

    private fun isApplicationFirstLaunch(context: Context): Boolean {
        val prefs = context.getSharedPreferences(SharedPreferencesInfo.FILE_NAME, MODE_PRIVATE)
        return if (prefs.getBoolean(SharedPreferencesInfo.KEY_APPLICATION_FIRST_LAUNCH, true)) {
            prefs.edit().putBoolean(SharedPreferencesInfo.KEY_APPLICATION_FIRST_LAUNCH, false).apply()
            true
        } else {
            false
        }
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
        val databasesManager: MainActivityContract.Model = DatabasesManager()
        databasesManager.putProductToShoppingCartDatabase(context, product, type)
        val messageProduct =
            "$type ${currentSelectedProduct?.brand} ${currentSelectedProduct?.name}," +
                    "\n${context.resources.getString(R.string.end_of_message_add_to_shopping_cart)}"
        view?.showSnackBar(messageProduct)
    }

    private fun startShoppingCartActivity(context: Context) {
        val databasesManager: MainActivityContract.Model = DatabasesManager()
        val list = databasesManager.getProductListFromShoppingCartDatabase(context)
        navigator.startShoppingCartActivity(list)
    }

}