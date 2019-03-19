package com.study.riseof.shopkotlin.navigation

import com.study.riseof.shopkotlin.model.Product

object NavigationManager : NavigationContract.Manager, NavigationContract.SetActivities {
    var mainActivity: NavigationContract.MainActivity? = null

    override fun setMainActivityToNavigationManager(mainActivity: NavigationContract.MainActivity?) {
        this.mainActivity = mainActivity
    }

    override fun createProductListFragment(productList: ArrayList<Product>) {
        mainActivity?.createProductListFragment(productList)
    }
}