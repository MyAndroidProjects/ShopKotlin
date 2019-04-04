package com.study.riseof.shopkotlin.activity.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct

interface MainActivityContract {
    interface View {
        fun openDrawer()
        fun closeDrawer()
        fun showProductInfoButtons()
        fun hideProductInfoButtons()
        fun showSnackBar(message: String)
        fun setMainActivityFlagsToFalse()
        fun setStartSnackBarMessage(startSnackBarMessage: String?)
        fun setToolbarText(text: String)
    }

    interface Presenter {
        fun setViewToPresenter(view: View?)
        fun activityIsOnStart(context: Context,
                              toDeleteShoppingCartDatabase: Boolean,
                              toCreateCatalogFragment: Boolean,
                              productListFragmentType: Int,
                              startSnackBarMessage: String?)
        fun menuButtonHomeSelected()
        fun closeDrawerLayout()
        fun backButtonSelected(supportFragmentManager: FragmentManager?)
        fun fabBackSelected()
        fun fabAddToShoppingCartSelected(context: Context)
        fun fabShoppingCartSelected(context: Context)
        fun productsQuantityViewSelected(context: Context)
    }

    interface Navigator {
        fun createFragment(fragment: Fragment)
        fun callSuperOnBackPressed()
        fun cleanBackStack()
        fun startShoppingCartActivity(list: ArrayList<ShoppingCartProduct>)

    }

    interface Model {
        fun fillShopDatabaseTables(context: Context)
        fun deleteShoppingCartDatabase(context: Context)
        fun deleteAllInShoppingCartDatabase(context: Context)
        fun putProductToShoppingCartDatabase(context: Context, product: Product, type: String): Boolean
        fun getProductListFromShoppingCartDatabase(context: Context): ArrayList<ShoppingCartProduct>
        fun getProductListFromShopDatabase(context: Context, tableName: String): ArrayList<Product>
        fun getProductQuantityFromShoppingCartDatabase(context: Context): Int
    }
}