package com.study.riseof.shopkotlin.fragment.navigationViewFragment

interface NavigationViewFragmentContract {
    interface View {

    }

    interface Presenter {
        fun navItemSmartphonesSelected()
        fun navItemGraphicTabletsSelected()
        fun navItemLaptopsSelected()
        fun navItemCamerasSelected()
        fun navItemSpeakersSelected()
        fun navItemHeadphonesSelected()
        fun anyNavigationItemSelected()

    }

    interface Navigator{

    }

    interface Model {

    }
}