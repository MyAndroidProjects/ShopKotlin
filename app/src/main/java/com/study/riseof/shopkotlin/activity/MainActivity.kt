package com.study.riseof.shopkotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.study.riseof.shopkotlin.R
import kotlinx.android.synthetic.main.coordinator_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }
}
