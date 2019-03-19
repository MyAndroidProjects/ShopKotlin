package com.study.riseof.shopkotlin.fragment.catalogFragment

import android.content.Context
import com.study.riseof.shopkotlin.model.Product

interface CatalogFragmentContract {
    interface View {

    }

    interface Presenter {
        fun imageSmartphoneSelected(context: Context)
        fun imageGraphicTabletSelected(context: Context)
        fun imageLaptopSelected(context: Context)
        fun imageCameraSelected(context: Context)
        fun imageSpeakersSelected(context: Context)
        fun imageHeadphonesSelected(context: Context)

    }

    interface Navigator{
        fun createProductListFragment(productList : ArrayList<Product>)

    }

    interface Model {

    }

}