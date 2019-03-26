package com.study.riseof.shopkotlin.activity.main

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager

object MainActivityNavigator: MainActivityContract.Navigator {
    private val manager = NavigationManager as NavigationContract.Manager

    override fun createFragment(fragment: Fragment) {
        manager.createFragment(fragment)
    }

    override fun callSuperOnBackPressed() {
        manager.callSuperOnBackPressed()
    }

    override fun cleanBackStack() {
        manager.cleanBackStack()
    }

    override fun startShoppingCartActivity(list: ArrayList<ShoppingCartProduct>) {
        manager.startShoppingCartActivity(list)
    }
}