package com.study.riseof.shopkotlin.activity.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager
import kotlinx.android.synthetic.main.coordinator_main.*
import android.support.v4.view.GravityCompat
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import com.study.riseof.shopkotlin.activity.BaseActivity
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter.KEY_PRODUCT_LIST_FRAGMENT_TYPE
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter.KEY_START_SNACK_BAR_MESSAGE
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter.KEY_TO_CREATE_CATALOG_FRAGMENT
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter.KEY_TO_DELETE_SHOPPING_CART_DATABASE
import com.study.riseof.shopkotlin.activity.shopping_cart.ShoppingCartActivity
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor

class MainActivity : BaseActivity(), MainActivityContract.View, NavigationContract.MainActivity {

    private var toDeleteShoppingCartDatabase: Boolean = true
    private var toCreateCatalogFragment: Boolean = true
    private var productListFragmentType: Int = MainActivityPresenter.ProductListFragmentType.NON.ordinal
    private var startSnackBarMessage: String? = null

    private var presenter: MainActivityContract.Presenter? = null
    private var navigationManager: NavigationContract.SetActivities? = null

    private val clickListener = View.OnClickListener { v: View ->
        when (v) {
            fabBack -> presenter?.fabBackSelected()
            fabAddToShoppingCart -> presenter?.fabAddToShoppingCartSelected(this)
            fabShoppingCart -> presenter?.fabShoppingCartSelected(this)
            productsQuantity -> presenter?.productsQuantityViewSelected(this)
            else -> Log.d("myLog", "else")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_TO_DELETE_SHOPPING_CART_DATABASE, toDeleteShoppingCartDatabase)
        outState.putBoolean(KEY_TO_CREATE_CATALOG_FRAGMENT, toCreateCatalogFragment)
        outState.putInt(KEY_PRODUCT_LIST_FRAGMENT_TYPE, MainActivityPresenter.ProductListFragmentType.NON.ordinal)
    }

    override fun onStart() {
        super.onStart()
        presenter?.activityIsOnStart(
            this,
            toDeleteShoppingCartDatabase,
            toCreateCatalogFragment,
            productListFragmentType,
            startSnackBarMessage
        )
    }

    override fun onBackPressed() {
        presenter?.backButtonSelected(supportFragmentManager)
    }

    // Implements function in class BaseActivity
    override fun getActivityLayout(): Int {
        return com.study.riseof.shopkotlin.R.layout.activity_main
    }

    override fun getToolbarView(): Toolbar {
        return toolbar
    }

    override fun menuButtonHomeSelected() {
        presenter?.menuButtonHomeSelected()
    }

    override fun setPresenterAndNavigationManager() {
        presenter = MainActivityPresenter
        presenter?.setViewToPresenter(this)
        navigationManager = NavigationManager
        navigationManager?.setMainActivityToNavigationManager(this)
    }

    override fun nullifyPresenterAndNavigationManager() {
        navigationManager?.setMainActivityToNavigationManager(null)
        presenter?.setViewToPresenter(null)
        presenter = null
        navigationManager = null
    }

    override fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState ?: return
        toDeleteShoppingCartDatabase = savedInstanceState.getBoolean(KEY_TO_DELETE_SHOPPING_CART_DATABASE)
        toCreateCatalogFragment = savedInstanceState.getBoolean(KEY_TO_CREATE_CATALOG_FRAGMENT)
        productListFragmentType = savedInstanceState.getInt(KEY_PRODUCT_LIST_FRAGMENT_TYPE)
    }

    override fun getValuesFromIntent() {
/*        Log.d("myLog", " startSnackBarMessage LJ intent " + startSnackBarMessage)
        startSnackBarMessage = intent?.getStringExtra(KEY_START_SNACK_BAR_MESSAGE)
        Log.d("myLog", " startSnackBarMessage после intent " + startSnackBarMessage)*/
        intent ?: return
        startSnackBarMessage = intent.getStringExtra(KEY_START_SNACK_BAR_MESSAGE)
        toDeleteShoppingCartDatabase = intent.getBooleanExtra(KEY_TO_DELETE_SHOPPING_CART_DATABASE, true)
        toCreateCatalogFragment = intent.getBooleanExtra(KEY_TO_CREATE_CATALOG_FRAGMENT, true)
        productListFragmentType = intent.getIntExtra(
            KEY_PRODUCT_LIST_FRAGMENT_TYPE,
            MainActivityPresenter.ProductListFragmentType.NON.ordinal
        )
        val nullString: String? = null
        intent.putExtra(KEY_START_SNACK_BAR_MESSAGE, nullString)
    }

    override fun setClickListeners() {
        fabBack.setOnClickListener(clickListener)
        fabAddToShoppingCart.setOnClickListener(clickListener)
        fabShoppingCart.setOnClickListener(clickListener)
        productsQuantity.setOnClickListener(clickListener)
    }

    // Implements function in interface MainActivityContract.View
    override fun setMainActivityFlagsToFalse() {
        toDeleteShoppingCartDatabase = false
        toCreateCatalogFragment = false
        productListFragmentType = MainActivityPresenter.ProductListFragmentType.NON.ordinal
    }

    override fun setStartSnackBarMessage(startSnackBarMessage: String?) {
        this.startSnackBarMessage = startSnackBarMessage
    }

    override fun setToolbarText(text: String) {
        productsQuantity.text = text
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

    override fun showSnackBar(message: String) {
        drawerLayout.snackbar(message)
    }


    // Implements function in interface NavigationContract.MainActivity
    override fun createFragment(fragment: Fragment) {
        Log.d("myLog", " createFragment fragment:$fragment")
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        Log.d("myLog", " transaction.replace ")
        transaction.replace(com.study.riseof.shopkotlin.R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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

    override fun cleanAllInBackStack() {
        val fragmentManager = supportFragmentManager
        val count = fragmentManager.backStackEntryCount
        var i = 0
        while (i < count) {
            fragmentManager.popBackStack()
            i++
        }
    }

    override fun startShoppingCartActivity(list: ArrayList<ShoppingCartProduct>) {
        val keyProductList = "productList"
        startActivity(intentFor<ShoppingCartActivity>(keyProductList to list))
    }

    override fun callSuperOnBackPressed() {
        super.onBackPressed()
    }
}
