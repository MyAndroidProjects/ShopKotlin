package com.study.riseof.shopkotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager
import kotlinx.android.synthetic.main.coordinator_main.*
import org.jetbrains.anko.toast
import android.view.WindowManager
import android.os.Build
import android.support.v4.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem


class MainActivity : AppCompatActivity(), MainActivityContract.View, NavigationContract.MainActivity {

    private var applicationIsStarting: Boolean = true
    private val keyApplicationIsStarting = "applicationIsStarting"

    private var presenter: MainActivityContract.Presenter? = null
    private var navigationManager: NavigationContract.SetActivities? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.study.riseof.shopkotlin.R.layout.activity_main)
        setActionBar()
        setStatusBarColor()
        getValuesFromSaveInstanceState(savedInstanceState)
        presenter = MainActivityPresenter
        presenter?.setViewToPresenter(this)
        presenter?.activityIsOnCreate(this)
        navigationManager = NavigationManager
        navigationManager?.setMainActivityToNavigationManager(this)
        toast("Hi there!")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(keyApplicationIsStarting, applicationIsStarting)
    }

    override fun onStart() {
        super.onStart()
        if (applicationIsStarting) {
            presenter?.applicationIsStarting()
            applicationIsStarting = false
            Log.d("myLog", " applicationIsStarting ")
        } else {
            Log.d("myLog", " !!!!!applicationIsStarting ")
        }
    }

    override fun onStop() {
        navigationManager?.setMainActivityToNavigationManager(null)
        presenter?.setViewToPresenter(null)
        presenter = null
        navigationManager = null
        super.onStop()
    }

    private fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?) {
        applicationIsStarting = savedInstanceState?.getBoolean(keyApplicationIsStarting) ?: return
    }

    private fun setActionBar() {
        setSupportActionBar(toolbar)
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

    override fun closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                presenter?.menuButtonHomeSelected()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun createFragment(fragment: Fragment) {
        Log.d("myLog", " createFragment fragment:"+ fragment.toString())
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        Log.d("myLog", " transaction.replace ")
        transaction.replace(com.study.riseof.shopkotlin.R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onBackPressed() {
        presenter?.backButtonSelected(supportFragmentManager)
    }

    override fun callSuperOnBackPressed() {
        super.onBackPressed()
    }

    // оставляет один фрагмент - CatalogFragment
    override fun cleanBackStack() {
        Log.d("myLog", " cleanBackStack ")
        val fragmentManager = supportFragmentManager
        val count = fragmentManager.backStackEntryCount
        var i=1
        while (i < count) {
          //  fragmentManager.popBackStackImmediate()
           fragmentManager.popBackStack()
            i++
        }
    }
}
