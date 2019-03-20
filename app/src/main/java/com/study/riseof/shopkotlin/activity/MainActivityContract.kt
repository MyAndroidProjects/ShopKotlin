package com.study.riseof.shopkotlin.activity

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

interface MainActivityContract {
    interface View {
        fun openDrawer()
        fun closeDrawer()

    }

    interface Presenter {
        fun setViewToPresenter(view: MainActivityContract.View?)
        fun activityIsOnCreate(context: Context)
        fun applicationIsStarting()
        fun menuButtonHomeSelected()
        fun closeDrawerLayout()
        fun backButtonSelected(supportFragmentManager: FragmentManager?)
    }

    interface Navigator {
        fun createFragment(fragment: Fragment)
        fun callSuperOnBackPressed()
        fun cleanBackStack()

    }

    interface Model {

    }
}