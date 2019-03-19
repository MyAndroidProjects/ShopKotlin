package com.study.riseof.shopkotlin.activity

import android.content.Context

interface MainActivityContract {
    interface View {
    }

    interface Presenter {
        fun setViewToPresenter(view: MainActivityContract.View?)
        fun activityIsOnCreate(context: Context)
    }

    interface Navigator {

    }

    interface Model {

    }
}