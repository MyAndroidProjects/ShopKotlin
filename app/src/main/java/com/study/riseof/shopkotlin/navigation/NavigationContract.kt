package com.study.riseof.shopkotlin.navigation

import com.study.riseof.shopkotlin.model.Product

interface NavigationContract {
    interface SetActivities {
        fun setMainActivityToNavigationManager(mainActivity: NavigationContract.MainActivity?);
    //    fun setSecondActivityToNavigationManager(secondActivity: NavigationContract.SecondActivity); //for test
    }

    interface Manager {
        fun createProductListFragment(productList: ArrayList<Product>)
    }

    interface MainActivity {
        fun createProductListFragment(productList: ArrayList<Product>)
    }

    // for testing
/*    interface SecondActivity {

    }*/
}