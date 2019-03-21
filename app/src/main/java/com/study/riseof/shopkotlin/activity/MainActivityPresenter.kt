package com.study.riseof.shopkotlin.activity

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.support.v4.app.FragmentManager
import android.util.Log
import com.study.riseof.shopkotlin.SharedPreferencesInfo
import com.study.riseof.shopkotlin.model.database.DatabaseManager
import com.study.riseof.shopkotlin.fragment.catalogFragment.CatalogFragment
import com.study.riseof.shopkotlin.navigation.NavigationContract

object MainActivityPresenter : MainActivityContract.Presenter, NavigationContract.MainActivityPresenter {
    private var view: MainActivityContract.View? = null
    private val navigator = MainActivityNavigator as MainActivityContract.Navigator

    override fun setViewToPresenter(view: MainActivityContract.View?) {
        this.view = view
    }

    override fun activityIsOnCreate(context: Context) {
        val prefs = context.getSharedPreferences(SharedPreferencesInfo.FILE_NAME, MODE_PRIVATE)
        if (prefs.getBoolean(SharedPreferencesInfo.KEY_APPLICATION_FIRST_LAUNCH, true)) {
            Log.d("myLog", " FIRST_LAUNCH ")
            val databaseManager = DatabaseManager() as MainActivityContract.Model
            databaseManager.fillAllTables(context)
            prefs.edit().putBoolean(SharedPreferencesInfo.KEY_APPLICATION_FIRST_LAUNCH, false).apply()
        } else {
            Log.d("myLog", " NOT FIRST_LAUNCH ")
        }
    }

    override fun applicationIsStarting() {
        navigator.createFragment(CatalogFragment.getInstance())
    }

    override fun menuButtonHomeSelected() {
        view?.openDrawer()
    }

    override fun backButtonSelected(supportFragmentManager: FragmentManager?) {
        view?.closeDrawer()
        val count = supportFragmentManager?.backStackEntryCount
        navigator.callSuperOnBackPressed()
        when (count) {
            2 -> navigator.cleanBackStack()
            1 -> navigator.callSuperOnBackPressed()
        }
        if (count == 2) {
            Log.d("myLog", " backButtonSelected cleanBackStack")
        }
        if (count == 1) {
            Log.d("myLog", " callSuperOnBackPressed ДВА РАЗА")
        } else {
            Log.d("myLog", " callSuperOnBackPressed ОДИН РАЗ")
        }
    }

    // реализация методов interface NavigationContract.MainActivityPresenter

    override fun closeDrawerLayout() {
        view?.closeDrawer()
    }


}