package com.study.riseof.shopkotlin.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())
        setActionBar()
        setStatusBarColor()
        getValuesFromIntent()
        getValuesFromSaveInstanceState(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        setPresenterAndNavigationManager()
        setClickListeners()
    }

    override fun onStop() {
        nullifyPresenterAndNavigationManager()
        super.onStop()
    }


    protected abstract fun getActivityLayout(): Int

    protected abstract fun nullifyPresenterAndNavigationManager()

    protected abstract fun setClickListeners()

    protected abstract fun setPresenterAndNavigationManager()

    protected abstract fun getValuesFromIntent()

    protected abstract fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?)

    protected abstract fun getToolbarView(): Toolbar

    protected abstract fun menuButtonHomeSelected()

    //protected abstract fun backButtonSelected(supportFragmentManager: FragmentManager?)

    private fun setActionBar() {
        setSupportActionBar(getToolbarView())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(com.study.riseof.shopkotlin.R.drawable.ic_baseline_menu_24px)
    }

    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            @Suppress("DEPRECATION")
            window.statusBarColor = resources.getColor(com.study.riseof.shopkotlin.R.color.status_bar)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                menuButtonHomeSelected()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}