package com.study.riseof.shopkotlin.fragment.catalogFragment

import android.content.Context
import android.util.Log
import com.study.riseof.shopkotlin.database.DatabaseInfo
import com.study.riseof.shopkotlin.database.DatabaseManager
import com.study.riseof.shopkotlin.fragment.productListFragment.ProductListFragment
import com.study.riseof.shopkotlin.model.Product

object CatalogFragmentPresenter : CatalogFragmentContract.Presenter {

    private val navigator = CatalogFragmentNavigator as CatalogFragmentContract.Navigator


    override fun imageSmartphoneSelected(context: Context?) {
        context?:return
        val productList = getProductListFromTable(context, DatabaseInfo.TABLE_SMARTPHONES)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageGraphicTabletSelected(context: Context?) {
        context?:return
        val productList = getProductListFromTable(context, DatabaseInfo.TABLE_GRAPHIC_TABLETS)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageLaptopSelected(context: Context?) {
        context?:return
        val productList =  getProductListFromTable(context, DatabaseInfo.TABLE_LAPTOPS)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageCameraSelected(context: Context?) {
        context?:return
        val productList =   getProductListFromTable(context, DatabaseInfo.TABLE_CAMERAS)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageSpeakersSelected(context: Context?) {
        context?:return
        val productList =  getProductListFromTable(context, DatabaseInfo.TABLE_SPEAKERS)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    override fun imageHeadphonesSelected(context: Context?) {
        context?:return
        val productList =  getProductListFromTable(context, DatabaseInfo.TABLE_HEADPHONES)
        navigator.createFragment(
            ProductListFragment.getInstance(productList)
        )
    }

    private fun getProductListFromTable(context: Context, tableName: String): ArrayList<Product> {
        val databaseManager = DatabaseManager()
/*   ***     val list = databaseManager.getProductListFromTable(context, tableName)
        Log.d("myLog", "presenter getProductListFromTable " + list.toString())
        if (list.isEmpty()) {
            Log.d("myLog", " list.isEmpty() ")
        }*/
        return databaseManager.getProductListFromTable(context, tableName)
    }

}