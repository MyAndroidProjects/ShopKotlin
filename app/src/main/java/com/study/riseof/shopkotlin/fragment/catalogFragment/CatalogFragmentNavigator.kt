package com.study.riseof.shopkotlin.fragment.catalogFragment

import com.study.riseof.shopkotlin.model.Product
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager

object CatalogFragmentNavigator : CatalogFragmentContract.Navigator {

   private val manager = NavigationManager as NavigationContract.Manager

    override fun createProductListFragment(productList: ArrayList<Product>) {
        manager.createProductListFragment(productList)
    }


}