package com.study.riseof.shopkotlin.activity.shopping_cart

import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager

object ShoppingCartActivityNavigator : ShoppingCartActivityContract.Navigator {

    private val manager = NavigationManager as NavigationContract.Manager

    override fun startMainActivity(fragmentType: Int, startSnackBarMessage:String?) {
        manager.startMainActivity(fragmentType, startSnackBarMessage)
    }
}

