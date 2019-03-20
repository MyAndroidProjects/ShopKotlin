package com.study.riseof.shopkotlin.navigation

import android.support.v4.app.Fragment
import android.util.Log
import com.study.riseof.shopkotlin.activity.MainActivityPresenter
import com.study.riseof.shopkotlin.model.Product

object NavigationManager : NavigationContract.Manager, NavigationContract.SetActivities {
    private var mainActivity: NavigationContract.MainActivity? = null

    override fun setMainActivityToNavigationManager(mainActivity: NavigationContract.MainActivity?) {
        this.mainActivity = mainActivity
    }

    override fun createFragment(fragment: Fragment) {
        Log.d("myLog", " NavigationManager createFragment ")
        mainActivity?.createFragment(fragment)
    }

    override fun closeDrawerLayout() {
        val mainActivityPresenter = MainActivityPresenter
                as NavigationContract.MainActivityPresenter
        mainActivityPresenter.closeDrawerLayout()
    }

    override fun callSuperOnBackPressed() {
        mainActivity?.callSuperOnBackPressed()
    }

    override fun cleanBackStack() {
        mainActivity?.cleanBackStack()

    }
}