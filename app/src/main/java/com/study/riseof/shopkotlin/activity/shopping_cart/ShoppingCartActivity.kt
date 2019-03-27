package com.study.riseof.shopkotlin.activity.shopping_cart

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.study.riseof.shopkotlin.R
import com.study.riseof.shopkotlin.activity.main.MainActivity
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter.KEY_PRODUCT_LIST_FRAGMENT_TYPE
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter.KEY_START_SNACK_BAR_MESSAGE
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter.KEY_TO_CREATE_CATALOG_FRAGMENT
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter.KEY_TO_DELETE_SHOPPING_CART_DATABASE
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar

class ShoppingCartActivity : AppCompatActivity(),
    ShoppingCartActivityContract.View,
    ShoppingCartActivityRecyclerAdapter.ProductListButtonDeleteClickListener,
    NavigationContract.ShoppingCartActivity {

    private var presenter: ShoppingCartActivityContract.Presenter? = null
    private var navigationManager: NavigationContract.SetActivities? = null

    private val keyProductList = "productList"
    private lateinit var productList: ArrayList<ShoppingCartProduct>

    private val clickListener = View.OnClickListener { v: View ->
        when (v) {
            buttonBack -> presenter?.buttonBackSelected()
            buttonClean -> presenter?.buttonCleanSelected()
            buttonBuy -> presenter?.buttonBuySelected()
            else -> Log.d("myLog", "else")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("myLog", " ShoppingCartActivity onCreate ")
        setContentView(com.study.riseof.shopkotlin.R.layout.activity_shopping_cart)
        setActionBar()
        setStatusBarColor()
        getValuesFromIntent()
        getValuesFromSaveInstanceState(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        presenter = ShoppingCartActivityPresenter
        presenter?.setViewToPresenter(this)
        navigationManager = NavigationManager
        navigationManager?.setShoppingCartActivityToNavigationManager(this)
        setRecyclerAdapter(productList)
        Log.d("myLog", " ShoppingCartActivity onStart productList " + productList.toString())
        setClickListeners()
        Log.d("myLog", " ShoppingCartActivity onStart ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(keyProductList, productList)
    }


    private fun getValuesFromIntent() {
        productList = intent?.getParcelableArrayListExtra<ShoppingCartProduct>(keyProductList) ?: return
    }


    private fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?) {
        productList = savedInstanceState?.getParcelableArrayList(keyProductList) ?: return
    }

    override fun startMainActivity(fragmentType: Int, startSnackBarMessage: String?) {
        startActivity(
            intentFor<MainActivity>(
                KEY_TO_DELETE_SHOPPING_CART_DATABASE to false,
                KEY_TO_CREATE_CATALOG_FRAGMENT to true,
                KEY_PRODUCT_LIST_FRAGMENT_TYPE to fragmentType,
                KEY_START_SNACK_BAR_MESSAGE to startSnackBarMessage
            ).clearTop()
        )
    }

    override fun setRecyclerAdapter(list: ArrayList<ShoppingCartProduct>) {
        val adapter = ShoppingCartActivityRecyclerAdapter(list, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewShoppingCart.layoutManager = layoutManager
        recyclerViewShoppingCart.adapter = adapter
    }

    override fun setShoppingCartProductList(list: ArrayList<ShoppingCartProduct>) {
        productList = list
    }


    override fun onStop() {
        Log.d("myLog", " ShoppingCartActivity onStop ")
        navigationManager?.setShoppingCartActivityToNavigationManager(null)
        presenter?.setViewToPresenter(null)
        presenter = null
        navigationManager = null
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("myLog", " ShoppingCartActivity onDestroy ")
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                presenter?.menuButtonHomeSelected()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        presenter?.backButtonSelected()
    }

    override fun callSuperOnBackPressed() {

        super.onBackPressed()
    }

    override fun closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onDeleteFromCartButtonClick(product: ShoppingCartProduct) {
        presenter?.deleteItemButtonShoppingCartListSelected(this, product)
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


    private fun setClickListeners() {
        buttonBack.setOnClickListener(clickListener)
        buttonClean.setOnClickListener(clickListener)
        buttonBuy.setOnClickListener(clickListener)
    }


    override fun showDeleteAllInShoppingCartDialog() {
        val message = resources.getString(R.string.message_delete_all_dialog)
        val title = resources.getString(R.string.title_delete_all_dialog)
        alert(message, title) {
            yesButton { presenter?.yesButtonDeleteAllDialogSelected(this.ctx) }
            noButton {}
        }.show()
    }

    override fun showDeleteProductDialog(message: String, id: Int) {
        val title = resources.getString(R.string.title_delete_product_dialog)
        alert(message, title) {
            yesButton { presenter?.yesButtonDeleteProductDialogSelected(this.ctx, id) }
            noButton {}
        }.show()
    }

    override fun showBuyProductsDialog() {
        val message = resources.getString(R.string.message_buy_dialog)
        val title = resources.getString(R.string.title_buy_dialog)
        alert(message, title) {
            yesButton { presenter?.yesButtonBuyDialogSelected(ctx) }
            noButton {}
        }.show()
    }

    override fun showSnackBar(message: String) {
        drawerLayout.snackbar(message)
    }


}