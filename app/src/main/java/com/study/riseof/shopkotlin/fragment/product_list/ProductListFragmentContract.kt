package com.study.riseof.shopkotlin.fragment.product_list

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.model.data.Product

interface ProductListFragmentContract {
    interface View {

    }

    interface Presenter {
        fun selectProduct(product: Product)

    }

    interface Navigator{
        fun createFragment(fragment: Fragment)
        fun showProductInfoButtons()
        fun selectedProduct(product: Product)

    }

    interface Model {

    }
}