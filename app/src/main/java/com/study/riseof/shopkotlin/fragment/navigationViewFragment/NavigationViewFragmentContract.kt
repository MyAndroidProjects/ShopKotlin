package com.study.riseof.shopkotlin.fragment.navigationViewFragment

import android.content.Context
import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.model.data.Product

interface NavigationViewFragmentContract {
    interface View {

    }

    interface Presenter {
        fun navItemCatalogSelected(context: Context?)
        fun navItemSmartphonesSelected(context: Context?)
        fun navItemGraphicTabletsSelected(context: Context?)
        fun navItemLaptopsSelected(context: Context?)
        fun navItemCamerasSelected(context: Context?)
        fun navItemSpeakersSelected(context: Context?)
        fun navItemHeadphonesSelected(context: Context?)
        fun navItemMicrophonesSelected(context: Context?)
        fun navItemFlashDrivesSelected(context: Context?)
        fun anyCatalogSectionSelected()
    }

    interface Navigator {
        fun createFragment(fragment: Fragment)
        fun closeDrawerLayout()
        fun cleanBackStack()
    }

    interface Model {
        fun getProductListFromTable(context: Context, tableName: String): ArrayList<Product>
    }
}