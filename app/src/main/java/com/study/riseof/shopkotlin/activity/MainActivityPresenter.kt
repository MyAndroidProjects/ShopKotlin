package com.study.riseof.shopkotlin.activity

import android.content.Context

object MainActivityPresenter : MainActivityContract.Presenter {
    var view: MainActivityContract.View? = null



    override fun setViewToPresenter(view: MainActivityContract.View?) {
        this.view = view
    }

    override fun activityIsOnCreate(context: Context) {

    }
}