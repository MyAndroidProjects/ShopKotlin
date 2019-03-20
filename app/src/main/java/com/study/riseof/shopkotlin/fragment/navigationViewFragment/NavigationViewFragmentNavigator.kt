package com.study.riseof.shopkotlin.fragment.navigationViewFragment

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager

object NavigationViewFragmentNavigator :NavigationViewFragmentContract.Navigator{
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
}