package com.study.riseof.shopkotlin.fragment.catalogFragment

import android.content.Context
import android.util.Log
import com.study.riseof.shopkotlin.database.DatabaseInfo
import com.study.riseof.shopkotlin.database.DatabaseManager
import com.study.riseof.shopkotlin.model.Product
import org.jetbrains.anko.db.*

object CatalogFragmentPresenter : CatalogFragmentContract.Presenter {

    private val navigator = CatalogFragmentNavigator as CatalogFragmentContract.Navigator


    override fun imageSmartphoneSelected(context: Context) {
        Log.d("myLog", " imageSmartphoneSelected " + DatabaseInfo.TABLE_SMARTPHONES)

        navigator.createProductListFragment(
            getProductListFromTable(context, DatabaseInfo.TABLE_SMARTPHONES)
        )
    }

    override fun imageGraphicTabletSelected(context: Context) {
        navigator.createProductListFragment(
            getProductListFromTable(context, DatabaseInfo.TABLE_GRAPHIC_TABLETS)
        )
    }

    override fun imageLaptopSelected(context: Context) {
        navigator.createProductListFragment(
            getProductListFromTable(context, DatabaseInfo.TABLE_LAPTOPS)
        )
    }

    override fun imageCameraSelected(context: Context) {
        navigator.createProductListFragment(
            getProductListFromTable(context, DatabaseInfo.TABLE_CAMERAS)
        )
    }

    override fun imageSpeakersSelected(context: Context) {
        navigator.createProductListFragment(
            getProductListFromTable(context, DatabaseInfo.TABLE_SPEAKERS)
        )
    }

    override fun imageHeadphonesSelected(context: Context) {
        navigator.createProductListFragment(
            getProductListFromTable(context, DatabaseInfo.TABLE_HEADPHONES)
        )
    }

    private fun getProductListFromTable(context: Context, tableName: String): ArrayList<Product> {
        val databaseManager = DatabaseManager()
        val list = databaseManager.getProductListFromTable(context, tableName)
        Log.d("myLog", "presenter getProductListFromTable " + list.toString())
        if (list.isEmpty()) {
            Log.d("myLog", " list.isEmpty() ")
        }
        return list
    }

}