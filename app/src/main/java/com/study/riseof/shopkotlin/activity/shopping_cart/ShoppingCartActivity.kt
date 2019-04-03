package com.study.riseof.shopkotlin.activity.shopping_cart

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.study.riseof.shopkotlin.R
import com.study.riseof.shopkotlin.activity.BaseActivity
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

class ShoppingCartActivity : BaseActivity(),
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

    override fun onStart() {
        super.onStart()
        setRecyclerAdapter(productList)
        presenter?.activityIsOnStart(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(keyProductList, productList)
    }

    override fun onBackPressed() {
        presenter?.backButtonSelected()
    }

    // Implements function in class BaseActivity
    override fun getActivityLayout(): Int {
        return com.study.riseof.shopkotlin.R.layout.activity_shopping_cart
    }

    override fun setPresenterAndNavigationManager() {
        presenter = ShoppingCartActivityPresenter
        presenter?.setViewToPresenter(this)
        navigationManager = NavigationManager
        navigationManager?.setShoppingCartActivityToNavigationManager(this)
    }

    override fun nullifyPresenterAndNavigationManager() {
        navigationManager?.setShoppingCartActivityToNavigationManager(null)
        presenter?.setViewToPresenter(null)
        presenter = null
        navigationManager = null
    }

    override fun getToolbarView(): Toolbar {
        return toolbar
    }

    override fun getValuesFromIntent() {
        productList = intent?.getParcelableArrayListExtra<ShoppingCartProduct>(keyProductList) ?: return
    }

    override fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?) {
        productList = savedInstanceState?.getParcelableArrayList(keyProductList) ?: return
    }

    override fun menuButtonHomeSelected() {
        presenter?.menuButtonHomeSelected()
    }

    override fun setClickListeners() {
        buttonBack.setOnClickListener(clickListener)
        buttonClean.setOnClickListener(clickListener)
        buttonBuy.setOnClickListener(clickListener)
    }

    // Implements function in interface ShoppingCartActivityContract.View
    override fun setToolbarText(text: String) {
        productsQuantity.text = text
    }

    override fun setTotalProductText(text: String) {
        textTotalProduct.text = text
    }

    override fun setTotalCostText(text: String) {
        textAmount.text = text
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

    override fun closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun callSuperOnBackPressed() {
        super.onBackPressed()
    }

    override fun showDeleteAllInShoppingCartDialog() {
        val message = resources.getString(R.string.message_delete_all_dialog)
        val title = resources.getString(R.string.title_delete_all_dialog)
        val dialog = alert(message, title) {
            yesButton { presenter?.yesButtonDeleteAllDialogSelected(this.ctx) }
            noButton {}
        }.show()
        dialog.setCanceledOnTouchOutside(false)
    }

    override fun showDeleteProductDialog(message: String, id: Int, price: Float) {
        val title = resources.getString(R.string.title_delete_product_dialog)
        val dialog = alert(message, title) {
            yesButton { presenter?.yesButtonDeleteProductDialogSelected(this.ctx, id, price) }
            noButton {}
        }.show()
        dialog.setCanceledOnTouchOutside(false)
    }

    override fun showBuyProductsDialog() {
        val message = resources.getString(R.string.message_buy_dialog)
        val title = resources.getString(R.string.title_buy_dialog)
        val dialog = alert(message, title) {
            yesButton { presenter?.yesButtonBuyDialogSelected(ctx) }
            noButton {}
        }.show()
        dialog.setCanceledOnTouchOutside(false)
    }

    override fun showEmptyCardDialog() {
        val message = resources.getString(R.string.message_empty_dialog)
        val title = resources.getString(R.string.title_empty_dialog)
        val dialog = alert(message, title) {
            okButton {
                presenter?.okButtonEmptyCardDialogSelected()
            }
        }.show()
        dialog.setCanceledOnTouchOutside(false)
    }

    override fun showSnackBar(message: String) {
        drawerLayout.snackbar(message)
    }

    // Implements function in interface NavigationContract.ShoppingCartActivity
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

    // Implements function in interface ProductListButtonDeleteClickListener
    override fun onDeleteFromCartButtonClick(product: ShoppingCartProduct) {
        presenter?.deleteItemButtonShoppingCartListSelected(this, product)
    }
}