package com.study.riseof.shopkotlin.navigation

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter
import com.study.riseof.shopkotlin.activity.shopping_cart.ShoppingCartActivityPresenter
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct

object NavigationManager : NavigationContract.Manager, NavigationContract.SetActivities {

    private var mainActivity: NavigationContract.MainActivity? = null
    private var shoppingCartActivity: NavigationContract.ShoppingCartActivity? = null
    private val mainActivityPresenter: NavigationContract.MainActivityPresenter = MainActivityPresenter
    private val shoppingCartActivityPresenter: NavigationContract.ShoppingCartActivityPresenter =
        ShoppingCartActivityPresenter

    // Implements function in interface NavigationContract.SetActivities
    override fun setMainActivityToNavigationManager(mainActivity: NavigationContract.MainActivity?) {
        this.mainActivity = mainActivity
    }

    override fun setShoppingCartActivityToNavigationManager(shoppingCartActivity: NavigationContract.ShoppingCartActivity?) {
        this.shoppingCartActivity = shoppingCartActivity
    }

    // Implements function in interface NavigationContract.Manager
    override fun createFragment(fragment: Fragment) {
        mainActivity?.createFragment(fragment)
    }

    override fun callSuperOnBackPressed() {
        mainActivity?.callSuperOnBackPressed()
    }

    override fun cleanBackStack() {
        mainActivity?.cleanBackStack()
    }

    override fun cleanAllInBackStack() {
        mainActivity?.cleanAllInBackStack()
    }

    override fun closeDrawerLayout() {
        mainActivityPresenter.closeDrawerLayout()
    }

    override fun showProductInfoButtons() {
        mainActivityPresenter.showProductInfoButtons()
    }

    override fun selectedProduct(product: Product) {
        mainActivityPresenter.selectedProduct(product)
    }

    override fun hideProductInfoButtons() {
        mainActivityPresenter.hideProductInfoButtons()
    }

    override fun startShoppingCartActivity(list: ArrayList<ShoppingCartProduct>) {
        mainActivity?.startShoppingCartActivity(list)
    }

    override fun startMainActivity(fragmentType: Int, startSnackBarMessage: String?) {
        shoppingCartActivity?.startMainActivity(fragmentType, startSnackBarMessage)
    }

    override fun shoppingCartActivityCallSuperOnBackPressed() {
        shoppingCartActivityPresenter.callSuperOnBackPressed()
    }
}