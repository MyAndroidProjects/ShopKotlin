package com.study.riseof.shopkotlin.navigation

import android.support.v4.app.Fragment
import com.study.riseof.shopkotlin.model.Product

interface NavigationContract {
    // Если несколько activity работают с менеджером, то подключать каждую,
    // и для каждой свой интерфейс, чтобы было меньше путаницы, например
    // fun setSecondActivityToNavigationManager(secondActivity: NavigationContract.SecondActivity);
    interface SetActivities {
        fun setMainActivityToNavigationManager(mainActivity: NavigationContract.MainActivity?);
    }

    interface Manager {
        fun createFragment(fragment: Fragment)
        fun closeDrawerLayout()
        fun callSuperOnBackPressed()
        fun cleanBackStack()
    }

    // Для работы с context, при работе с другими activity будут использоваться
// соответствующие интерфейсы, например interface SecondActivity {...}
    interface MainActivity {
        fun createFragment(fragment: Fragment)
        fun callSuperOnBackPressed()
        fun cleanBackStack()
    }

    // При необходимости передать команды фрагментам (для работы с View через презентер) будут использоваться
    // соответствующие интерфейсы, например interface CatalogFragmentPresenter {...}
    interface MainActivityPresenter {
        fun closeDrawerLayout()
    }

}