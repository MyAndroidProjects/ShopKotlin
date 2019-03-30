package com.study.riseof.shopkotlin.fragment.product_list

import com.study.riseof.shopkotlin.fragment.product_info.ProductInfoFragment
import com.study.riseof.shopkotlin.model.data.Product

object ProductListFragmentPresenter : ProductListFragmentContract.Presenter {

    private val navigator = ProductListFragmentNavigator as ProductListFragmentContract.Navigator

    override fun selectProduct(product: Product) {
        navigator.createFragment(ProductInfoFragment.getInstance(product))
        navigator.showProductInfoButtons()
        navigator.selectedProduct(product)
    }
}