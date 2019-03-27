package com.study.riseof.shopkotlin.navigation

import android.support.v4.app.Fragment
import android.util.Log
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter
import com.study.riseof.shopkotlin.activity.shopping_cart.ShoppingCartActivityPresenter
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct

object NavigationManager : NavigationContract.Manager, NavigationContract.SetActivities {
    private var mainActivity: NavigationContract.MainActivity? = null
    private var shoppingCartActivity: NavigationContract.ShoppingCartActivity? = null
    private val mainActivityPresenter = MainActivityPresenter
            as NavigationContract.MainActivityPresenter
    private val shoppingCartActivityPresenter = ShoppingCartActivityPresenter
            as NavigationContract.ShoppingCartActivityPresenter

    override fun setMainActivityToNavigationManager(mainActivity: NavigationContract.MainActivity?) {
        this.mainActivity = mainActivity
    }

    override fun setShoppingCartActivityToNavigationManager(shoppingCartActivity: NavigationContract.ShoppingCartActivity?) {
        this.shoppingCartActivity = shoppingCartActivity
    }

    override fun createFragment(fragment: Fragment) {
        Log.d("myLog", " NavigationManager createFragment ")
        mainActivity?.createFragment(fragment)
    }


    override fun callSuperOnBackPressed() {
        mainActivity?.callSuperOnBackPressed()
    }

    override fun cleanBackStack() {
        mainActivity?.cleanBackStack()
    }

    // Main Activity Presenter
    override fun closeDrawerLayout() {
        mainActivityPresenter.closeDrawerLayout()
    }

    override fun showProductInfoButtons() {
        mainActivityPresenter.showProductInfoButtons()
    }

    override fun selectedProduct(product: Product) {
        mainActivityPresenter.selectedProduct(product)
    }

    override fun startShoppingCartActivity(list: ArrayList<ShoppingCartProduct>) {
        mainActivity?.startShoppingCartActivity(list)
    }

    override fun startMainActivity(fragmentType: Int, startSnackBarMessage: String?) {
        shoppingCartActivity?.startMainActivity(fragmentType,startSnackBarMessage)
    }

    override fun shoppingCartActivityCallSuperOnBackPressed() {
        shoppingCartActivityPresenter.callSuperOnBackPressed()
    }

}