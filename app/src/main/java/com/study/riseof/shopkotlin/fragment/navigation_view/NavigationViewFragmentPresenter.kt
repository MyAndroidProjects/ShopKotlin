package com.study.riseof.shopkotlin.fragment.navigation_view

import android.content.Context
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter
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

    private val navigator: NavigationViewFragmentContract.Navigator = NavigationViewFragmentNavigator
    private lateinit var productList: ArrayList<Product>
    private lateinit var currentActivity: CurrentActivity

    override fun setCurrentActivity(activity: CurrentActivity) {
        currentActivity = activity
    }

    override fun navItemCatalogSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> {
                navigator.closeDrawerLayout()
                navigator.cleanAllInBackStack()
                navigator.createFragment(
                    CatalogFragment.getInstance()
                )
            }
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.NON.ordinal)
        }
    }

    override fun navItemSmartphonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SMARTPHONES)
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.SMARTPHONES.ordinal)
        }
    }

    override fun navItemGraphicTabletsSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_GRAPHIC_TABLETS)
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.GRAPHIC_TABLETS.ordinal)
        }
    }

    override fun navItemLaptopsSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_LAPTOPS)
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.LAPTOPS.ordinal)
        }
    }

    override fun navItemCamerasSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_CAMERAS)
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.CAMERAS.ordinal)
        }
    }

    override fun navItemSpeakersSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SPEAKERS)
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.SPEAKERS.ordinal)
        }
    }

    override fun navItemHeadphonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_HEADPHONES)
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.HEADPHONES.ordinal)
        }
    }

    override fun navItemMicrophonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_MICROPHONES)
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.MICROPHONES.ordinal)
        }
    }

    override fun navItemFlashDrivesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_FLASH_DRIVES)
            CurrentActivity.ShoppingCartActivity
            -> startMainActivity(MainActivityPresenter.ProductListFragmentType.FLASH_DRIVES.ordinal)
        }
    }

    override fun anyCatalogSectionSelected() {
        if (currentActivity == CurrentActivity.MainActivity) {
            navigator.hideProductInfoButtons()
            navigator.closeDrawerLayout()
            navigator.cleanBackStack()
            navigator.createFragment(
                ProductListFragment.getInstance(productList)
            )
        }
    }

    private fun getProductListFromShopDatabase(context: Context, tableName: String): ArrayList<Product> {
        val databasesManager = DatabasesManager() as NavigationViewFragmentContract.Model
        return databasesManager.getProductListFromShopDatabase(context, tableName)
    }

    private fun startMainActivity(fragmentType: Int) {
        navigator.startMainActivity(fragmentType, null)
    }
}