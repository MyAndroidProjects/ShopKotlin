package com.study.riseof.shopkotlin.fragment.catalog

import android.content.Context
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo
import com.study.riseof.shopkotlin.model.database.DatabasesManager
import com.study.riseof.shopkotlin.fragment.product_list.ProductListFragment
import com.study.riseof.shopkotlin.model.data.Product

object CatalogFragmentPresenter : CatalogFragmentContract.Presenter {

    private val navigator = CatalogFragmentNavigator as CatalogFragmentContract.Navigator

    override fun imageSmartphoneSelected(context: Context?) {
        context ?: return
        val productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SMARTPHONES)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageGraphicTabletSelected(context: Context?) {
        context ?: return
        val productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_GRAPHIC_TABLETS)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageLaptopSelected(context: Context?) {
        context ?: return
        val productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_LAPTOPS)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageCameraSelected(context: Context?) {
        context ?: return
        val productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_CAMERAS)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageSpeakersSelected(context: Context?) {
        context ?: return
        val productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_SPEAKERS)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageHeadphonesSelected(context: Context?) {
        context ?: return
        val productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_HEADPHONES)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageMicrophoneSelected(context: Context?) {
        context ?: return
        val productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_MICROPHONES)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageFlashDriveSelected(context: Context?) {
        context ?: return
        val productList = getProductListFromShopDatabase(context, ShopDatabaseInfo.TABLE_FLASH_DRIVES)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    private fun getProductListFromShopDatabase(context: Context, tableName: String): ArrayList<Product> {
        val databasesManager = DatabasesManager() as CatalogFragmentContract.Model
        return databasesManager.getProductListFromShopDatabase(context, tableName)
    }

}