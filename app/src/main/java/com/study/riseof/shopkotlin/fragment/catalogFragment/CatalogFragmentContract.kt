package com.study.riseof.shopkotlin.fragment.catalogFragment

import android.content.Context
import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.model.data.Product

interface CatalogFragmentContract {
    interface View {

    }

    interface Presenter {
        fun imageSmartphoneSelected(context: Context?)
        fun imageGraphicTabletSelected(context: Context?)
        fun imageLaptopSelected(context: Context?)
        fun imageCameraSelected(context: Context?)
        fun imageSpeakersSelected(context: Context?)
        fun imageHeadphonesSelected(context: Context?)
        fun imageMicrophoneSelected(context: Context?)
        fun imageFlashDriveSelected(context: Context?)

    }

    interface Navigator{
        fun createFragment(fragment: Fragment)

    }

    interface Model {
        fun getProductListFromTable(context: Context, tableName: String): ArrayList<Product>
    }

}