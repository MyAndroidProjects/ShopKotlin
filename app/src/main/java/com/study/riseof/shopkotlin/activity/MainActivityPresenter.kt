package com.study.riseof.shopkotlin.activity

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.study.riseof.shopkotlin.SharedPreferencesInfo
import com.study.riseof.shopkotlin.database.DatabaseManager

object MainActivityPresenter : MainActivityContract.Presenter {
    var view: MainActivityContract.View? = null



    override fun setViewToPresenter(view: MainActivityContract.View?) {
        this.view = view
    }

    override fun activityIsOnCreate(context: Context) {
       val prefs = context.getSharedPreferences(SharedPreferencesInfo.FILE_NAME, MODE_PRIVATE)
        if (prefs.getBoolean(SharedPreferencesInfo.KEY_FIRST_LAUNCH, true)) {
            Log.d("myLog", " FIRST_LAUNCH ")
            val databaseManager = DatabaseManager()
            databaseManager.fillAllTables(context)
            prefs.edit().putBoolean(SharedPreferencesInfo.KEY_FIRST_LAUNCH, false).apply();
        }else{
            Log.d("myLog", " NOOOOOOOOOOOT FIRST_LAUNCH ")
        }
    }
}