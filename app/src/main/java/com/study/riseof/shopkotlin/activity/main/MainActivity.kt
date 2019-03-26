package com.study.riseof.shopkotlin.activity.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager
import kotlinx.android.synthetic.main.coordinator_main.*
import android.view.WindowManager
import android.os.Build
import android.support.v4.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import com.study.riseof.shopkotlin.activity.shopping_cart.ShoppingCartActivity
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class MainActivity : AppCompatActivity(), MainActivityContract.View, NavigationContract.MainActivity {

    private var applicationIsStarting: Boolean = true
    private val keyApplicationIsStarting = "applicationIsStarting"

    private var presenter: MainActivityContract.Presenter? = null
    private var navigationManager: NavigationContract.SetActivities? = null

    private val clickListener = View.OnClickListener { v: View ->
        when (v) {
            fabBack -> presenter?.fabBackSelected()
            fabAddToShoppingCart ->presenter?.fabAddToShoppingCartSelected(this)
            fabShoppingCart -> presenter?.fabShoppingCartSelected(this)
            productsQuantity -> presenter?.productsQuantityViewSelected(this)
            else -> Log.d("myLog", "else")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("myLog", " MainActivity onCreate ")
        setContentView(com.study.riseof.shopkotlin.R.layout.activity_main)
        setActionBar()
        setStatusBarColor()
        getValuesFromIntent()
        getValuesFromSaveInstanceState(savedInstanceState)

        //   toast("onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(keyApplicationIsStarting, applicationIsStarting)
    }

    override fun onStart() {
        super.onStart()
        presenter = MainActivityPresenter
        presenter?.setViewToPresenter(this)
        presenter?.activityIsOnStart(this)
        navigationManager = NavigationManager
        navigationManager?.setMainActivityToNavigationManager(this)
        Log.d("myLog", " MainActivity onStart ")
        if (applicationIsStarting) {
            presenter?.applicationIsStarting(this)
            applicationIsStarting = false
            Log.d("myLog", " applicationIsStarting ")
        } else {
            Log.d("myLog", " !!!!!applicationIsStarting ")
        }
        setClickListeners()
    }

    override fun onStop() {
        navigationManager?.setMainActivityToNavigationManager(null)
        presenter?.setViewToPresenter(null)
        presenter = null
        navigationManager = null
        Log.d("myLog", " MainActivity onStop ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("myLog", " MainActivity onDestroy ")
        super.onDestroy()
    }
    private fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?) {
        applicationIsStarting = savedInstanceState?.getBoolean(keyApplicationIsStarting) ?: return
    }
    private fun getValuesFromIntent() {
        applicationIsStarting = intent?.getBooleanExtra(keyApplicationIsStarting,true) ?: return
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

    override fun showProductInfoButtons() {
        fabBack.show()
        fabAddToShoppingCart.show()
    }

    override fun hideProductInfoButtons() {
        fabBack.hide()
        fabAddToShoppingCart.hide()
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

    override fun showSnackBar(message: String){
        fragmentContainer.snackbar(message)
    }

    override fun createFragment(fragment: Fragment) {
        Log.d("myLog", " createFragment fragment:" + fragment.toString())
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
        var i = 1
        while (i < count) {
            // с Immediate на долю секунды появяется каталог перед новым фрагментом, - некрасиво
            //  fragmentManager.popBackStackImmediate()
            fragmentManager.popBackStack()
            i++
        }
    }

    override fun startShoppingCartActivity(list: ArrayList<ShoppingCartProduct>) {
        val keyProductList = "productList"
        startActivity(intentFor<ShoppingCartActivity>(keyProductList to list))
    }

    private fun setClickListeners(){
        fabBack.setOnClickListener(clickListener)
        fabAddToShoppingCart.setOnClickListener(clickListener)
        fabShoppingCart.setOnClickListener(clickListener)
        productsQuantity.setOnClickListener(clickListener)
    }
}
