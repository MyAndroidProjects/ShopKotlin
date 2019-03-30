package com.study.riseof.shopkotlin.fragment.navigation_view

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager

object NavigationViewFragmentNavigator : NavigationViewFragmentContract.Navigator {
    private val manager = NavigationManager as NavigationContract.Manager

    override fun createFragment(fragment: Fragment) {
        manager.createFragment(fragment)
    }

    override fun closeDrawerLayout() {
        manager.closeDrawerLayout()
    }

    override fun cleanBackStack() {
        manager.cleanBackStack()
    }

    override fun cleanAllInBackStack() {
        manager.cleanAllInBackStack()
    }

    override fun startMainActivity(fragmentType: Int, startSnackBarMessage: String?) {
        manager.startMainActivity(fragmentType, startSnackBarMessage)
    }

    override fun shoppingCartActivityCallSuperOnBackPressed() {
        manager.shoppingCartActivityCallSuperOnBackPressed()
    }

    override fun hideProductInfoButtons() {
        manager.hideProductInfoButtons()
    }
}