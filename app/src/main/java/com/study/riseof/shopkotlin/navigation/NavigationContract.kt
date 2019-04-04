package com.study.riseof.shopkotlin.navigation

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct

interface NavigationContract {
    // Если несколько activity работают с менеджером, то подключать каждую,
    // и для каждой свой интерфейс, чтобы было меньше путаницы, например
    // fun setSecondActivityToNavigationManager(secondActivity: NavigationContract.SecondActivity)
    interface SetActivities {
        fun setMainActivityToNavigationManager(mainActivity: NavigationContract.MainActivity?)
        fun setShoppingCartActivityToNavigationManager(shoppingCartActivity: NavigationContract.ShoppingCartActivity?)
    }

    interface Manager {
        fun createFragment(fragment: Fragment)
        fun closeDrawerLayout()
        fun callSuperOnBackPressed()
        fun cleanBackStack()
        fun cleanAllInBackStack()
        fun showProductInfoButtons()
        fun selectedProduct(product: Product)
        fun startShoppingCartActivity(list: ArrayList<ShoppingCartProduct>)
        fun startMainActivity(fragmentType: Int, startSnackBarMessage: String?)
        fun shoppingCartActivityCallSuperOnBackPressed()
        fun hideProductInfoButtons()
    }

    // Для работы с context, при работе с другими activity будут использоваться
// соответствующие интерфейсы, например interface SecondActivity {...}
    interface MainActivity {
        fun createFragment(fragment: Fragment)
        fun callSuperOnBackPressed()
        fun cleanBackStack()
        fun cleanAllInBackStack()
        fun startShoppingCartActivity(list: ArrayList<ShoppingCartProduct>)

    }

    interface ShoppingCartActivity {
        fun startMainActivity(fragmentType: Int, startSnackBarMessage: String?)
    }

    // При необходимости передать команды фрагментам (для работы с View через презентер) будут использоваться
    // соответствующие интерфейсы, например interface CatalogFragmentPresenter {...}
    interface MainActivityPresenter {
        fun closeDrawerLayout()
        fun showProductInfoButtons()
        fun selectedProduct(product: Product)
        fun hideProductInfoButtons()
    }

    interface ShoppingCartActivityPresenter {
        fun callSuperOnBackPressed()
    }
}