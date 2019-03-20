package com.study.riseof.shopkotlin.fragment.navigationViewFragment

import android.content.Context
import android.support.v4.app.Fragment

interface NavigationViewFragmentContract {
    interface View {

    }

    interface Presenter {
        fun navItemSmartphonesSelected(context: Context?)
        fun navItemGraphicTabletsSelected(context: Context?)
        fun navItemLaptopsSelected(context: Context?)
        fun navItemCamerasSelected(context: Context?)
        fun navItemSpeakersSelected(context: Context?)
        fun navItemHeadphonesSelected(context: Context?)
        fun anyNavigationItemSelected()

    }

    interface Navigator{
        fun createFragment(fragment: Fragment)
        fun closeDrawerLayout()
        fun cleanBackStack()
    }

    interface Model {

    }
}