package com.study.riseof.shopkotlin.fragment.navigation_view

import android.app.Application
import android.content.Context
import android.util.Log
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

    private val navigator = NavigationViewFragmentNavigator as NavigationViewFragmentContract.Navigator
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
                navigator.cleanBackStack()
                navigator.createFragment(
                    CatalogFragment.getInstance()
                )
                navigator.cleanBackStack()
            }
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.NON.ordinal, null)
        }
    }

    override fun navItemSmartphonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SMARTPHONES)
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.SMARTPHONES.ordinal, null)
        }
    }

    override fun navItemGraphicTabletsSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_GRAPHIC_TABLETS)
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.GRAPHIC_TABLETS.ordinal, null)
        }
    }

    override fun navItemLaptopsSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_LAPTOPS)
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.LAPTOPS.ordinal, null)
        }
    }

    override fun navItemCamerasSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_CAMERAS)
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.CAMERAS.ordinal, null)
        }
    }

    override fun navItemSpeakersSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SPEAKERS)
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.SPEAKERS.ordinal, null)
        }
    }

    override fun navItemHeadphonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_HEADPHONES)
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.HEADPHONES.ordinal, null)
        }
    }

    override fun navItemMicrophonesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_MICROPHONES)
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.MICROPHONES.ordinal, null)
        }
    }

    override fun navItemFlashDrivesSelected(context: Context?) {
        context ?: return
        when (currentActivity) {
            CurrentActivity.MainActivity
            -> productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_FLASH_DRIVES)
            CurrentActivity.ShoppingCartActivity
            -> navigator.startMainActivity(MainActivityPresenter.ProductListFragmentType.FLASH_DRIVES.ordinal, null)
        }
    }

    override fun anyCatalogSectionSelected() {
        if (currentActivity == CurrentActivity.MainActivity) {
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
}