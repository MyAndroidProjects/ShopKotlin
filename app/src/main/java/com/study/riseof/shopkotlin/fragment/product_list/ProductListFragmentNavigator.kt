package com.study.riseof.shopkotlin.fragment.product_list

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager

object ProductListFragmentNavigator : ProductListFragmentContract.Navigator {
    private val manager = NavigationManager as NavigationContract.Manager


    override fun createFragment(fragment: Fragment) {
        manager.createFragment(fragment)
    }

    override fun showProductInfoButtons() {
        manager.showProductInfoButtons()
    }

    override fun selectedProduct(product: Product) {
        manager.selectedProduct(product)
    }
}