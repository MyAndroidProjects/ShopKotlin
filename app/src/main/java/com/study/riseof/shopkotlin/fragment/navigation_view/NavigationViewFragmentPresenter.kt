package com.study.riseof.shopkotlin.fragment.navigation_view

import android.app.Application
import android.content.Context
import android.util.Log
import com.study.riseof.shopkotlin.fragment.catalog.CatalogFragment
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo
import com.study.riseof.shopkotlin.model.database.DatabasesManager
import com.study.riseof.shopkotlin.fragment.product_list.ProductListFragment
import com.study.riseof.shopkotlin.model.data.Product

object NavigationViewFragmentPresenter : NavigationViewFragmentContract.Presenter {

    enum class CurrentActivity {
        MainActivity,
        ShoppingCartActivity
    }

    private val navigator = NavigationViewFragmentNavigator as NavigationViewFragmentContract.Navigator
    private lateinit var productList: ArrayList<Product>
    private lateinit var currentActivity: CurrentActivity

    override fun setCurrentActivity(activity: CurrentActivity) {
        currentActivity = activity
    }

    override fun navItemCatalogSelected(context: Context?) {
        context ?: return
        if (currentActivity ==
            NavigationViewFragmentPresenter.CurrentActivity.ShoppingCartActivity
        ) {
            navigator.startMainActivity()
        }
        navigator.closeDrawerLayout()
        navigator.cleanBackStack()
        navigator.createFragment(
            CatalogFragment.getInstance()
        )
        navigator.cleanBackStack()
    }

    override fun navItemSmartphonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.MainActivity ")
            CurrentActivity.ShoppingCartActivity ->{
                navigator.shoppingCartActivityCleanBackStack()
                navigator.startMainActivity()
            }

        }

        productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SMARTPHONES)
        Log.d("myLog", "shoppingCart productList "+productList.toString())
    }

    override fun navItemGraphicTabletsSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.MainActivity ")
            CurrentActivity.ShoppingCartActivity -> {
               // val con = context as ApplicationContext

                Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.ShoppingCartActivity ")
            }
        }
        productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_GRAPHIC_TABLETS)

    }

    override fun navItemLaptopsSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.MainActivity ")
            CurrentActivity.ShoppingCartActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.ShoppingCartActivity ")
        }
        productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_LAPTOPS)
    }

    override fun navItemCamerasSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.MainActivity ")
            CurrentActivity.ShoppingCartActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.ShoppingCartActivity ")
        }
        productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_CAMERAS)
    }

    override fun navItemSpeakersSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.MainActivity ")
            CurrentActivity.ShoppingCartActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.ShoppingCartActivity ")
        }
        productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SPEAKERS)
    }

    override fun navItemHeadphonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.MainActivity ")
            CurrentActivity.ShoppingCartActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.ShoppingCartActivity ")
        }
        productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_HEADPHONES)
    }

    override fun navItemMicrophonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.MainActivity ")
            CurrentActivity.ShoppingCartActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.ShoppingCartActivity ")
        }
        productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_MICROPHONES)
    }

    override fun navItemFlashDrivesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.MainActivity ")
            CurrentActivity.ShoppingCartActivity -> Log.d("myLog", " !!!!!!!!!!!!!!!!!!!! CurrentActivity.ShoppingCartActivity ")
        }
        productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_FLASH_DRIVES)
    }


    override fun anyCatalogSectionSelected() {
        Log.d("myLog", " anyCatalogSectionSelected ")
        navigator.closeDrawerLayout()
        navigator.cleanBackStack()
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    private fun getProductListFromShopDatabase(context: Context, tableName: String): ArrayList<Product> {
        val databasesManager = DatabasesManager() as NavigationViewFragmentContract.Model
/*   ***     val list = databasesManager.getProductListFromShopDatabase(context, tableName)
        Log.d("myLog", "presenter getProductListFromShopDatabase " + list.toString())
        if (list.isEmpty()) {
            Log.d("myLog", " list.isEmpty() ")
        }*/
        return databasesManager.getProductListFromShopDatabase(context, tableName)
    }

}