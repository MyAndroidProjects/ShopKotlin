package com.study.riseof.shopkotlin.fragment.catalog

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager

object CatalogFragmentNavigator : CatalogFragmentContract.Navigator {

    private val manager = NavigationManager as NavigationContract.Manager

    override fun createFragment(fragment: Fragment) {
        manager.createFragment(fragment)
    }
}