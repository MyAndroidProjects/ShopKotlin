package com.study.riseof.shopkotlin.fragment.navigationViewFragment

import android.content.Context
import com.study.riseof.shopkotlin.fragment.catalogFragment.CatalogFragment
import com.study.riseof.shopkotlin.model.database.DatabaseInfo
import com.study.riseof.shopkotlin.model.database.DatabaseManager
import com.study.riseof.shopkotlin.fragment.productListFragment.ProductListFragment
import com.study.riseof.shopkotlin.model.data.Product

object NavigationViewFragmentPresenter : NavigationViewFragmentContract.Presenter {

    private val navigator = NavigationViewFragmentNavigator as NavigationViewFragmentContract.Navigator
    private lateinit var productList: ArrayList<Product>

    override fun navItemCatalogSelected(context: Context?) {
        context ?: return
        navigator.closeDrawerLayout()
        navigator.cleanBackStack()
        navigator.createFragment(
            CatalogFragment.getInstance()
        )
    }

    override fun navItemSmartphonesSelected(context: Context?) {
        context ?: return
        productList = getProductListFromTable(context, DatabaseInfo.TABLE_SMARTPHONES)
    }

    override fun navItemGraphicTabletsSelected(context: Context?) {
        context ?: return
        productList = getProductListFromTable(context, DatabaseInfo.TABLE_GRAPHIC_TABLETS)
    }

    override fun navItemLaptopsSelected(context: Context?) {
        context ?: return
        productList = getProductListFromTable(context, DatabaseInfo.TABLE_LAPTOPS)
    }

    override fun navItemCamerasSelected(context: Context?) {
        context ?: return
        productList = getProductListFromTable(context, DatabaseInfo.TABLE_CAMERAS)
    }

    override fun navItemSpeakersSelected(context: Context?) {
        context ?: return
        productList = getProductListFromTable(context, DatabaseInfo.TABLE_SPEAKERS)
    }

    override fun navItemHeadphonesSelected(context: Context?) {
        context ?: return
        productList = getProductListFromTable(context, DatabaseInfo.TABLE_HEADPHONES)
    }

    override fun navItemMicrophonesSelected(context: Context?) {
        context ?: return
        productList = getProductListFromTable(context, DatabaseInfo.TABLE_MICROPHONES)
    }

    override fun navItemFlashDrivesSelected(context: Context?) {
        context ?: return
        productList = getProductListFromTable(context, DatabaseInfo.TABLE_FLASH_DRIVES)
    }

    override fun anyCatalogSectionSelected() {
        navigator.closeDrawerLayout()
        navigator.cleanBackStack()
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    private fun getProductListFromTable(context: Context, tableName: String): ArrayList<Product> {
        val databaseManager = DatabaseManager() as NavigationViewFragmentContract.Model
/*   ***     val list = databaseManager.getProductListFromTable(context, tableName)
        Log.d("myLog", "presenter getProductListFromTable " + list.toString())
        if (list.isEmpty()) {
            Log.d("myLog", " list.isEmpty() ")
        }*/
        return databaseManager.getProductListFromTable(context, tableName)
    }

}