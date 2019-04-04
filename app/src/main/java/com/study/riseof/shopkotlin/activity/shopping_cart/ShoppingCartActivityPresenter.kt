package com.study.riseof.shopkotlin.activity.shopping_cart

import android.content.Context
import com.study.riseof.shopkotlin.R
import com.study.riseof.shopkotlin.activity.main.MainActivityPresenter
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct
import com.study.riseof.shopkotlin.model.database.DatabasesManager
import com.study.riseof.shopkotlin.navigation.NavigationContract

object ShoppingCartActivityPresenter : ShoppingCartActivityContract.Presenter,
    NavigationContract.ShoppingCartActivityPresenter {

    private var view: ShoppingCartActivityContract.View? = null
    private val navigator: ShoppingCartActivityContract.Navigator = ShoppingCartActivityNavigator
    private var productsQuantity: Int = 0
    private var totalCost: Float = 0f



    // Implements function in interface ShoppingCartActivityContract.Presenter

    override fun setViewToPresenter(view: ShoppingCartActivityContract.View?) {
        this.view = view
    }

    override fun backButtonSelected() {
        view?.callSuperOnBackPressed()
        view?.closeDrawer()
    }

    override fun buttonBackSelected() {
        view?.callSuperOnBackPressed()
    }

    override fun buttonCleanSelected() {
        view?.showDeleteAllInShoppingCartDialog()
    }

    override fun yesButtonDeleteAllDialogSelected(context: Context) {
        val databasesManager: ShoppingCartActivityContract.Model = DatabasesManager()
        databasesManager.deleteAllInShoppingCartDatabase(context)
        navigator.startMainActivity(
            MainActivityPresenter.ProductListFragmentType.NON.ordinal,
            context.resources.getString(R.string.message_delete_all_snack_bar)
        )
    }

    override fun buttonBuySelected() {
        view?.showBuyProductsDialog()
    }

    override fun yesButtonBuyDialogSelected(context: Context) {
        val databasesManager: ShoppingCartActivityContract.Model = DatabasesManager()
        databasesManager.deleteAllInShoppingCartDatabase(context)
        navigator.startMainActivity(
            MainActivityPresenter.ProductListFragmentType.NON.ordinal,
            context.resources.getString(R.string.message_buy_snack_bar)
        )
    }

    override fun deleteItemButtonShoppingCartListSelected(context: Context, product: ShoppingCartProduct) {
        val message = "${product.type} ${product.brand} ${product.name}"
        view?.showDeleteProductDialog(message, product.id, product.price)


    }

    override fun yesButtonDeleteProductDialogSelected(context: Context, id: Int, price: Float) {
        val databasesManager: ShoppingCartActivityContract.Model = DatabasesManager()
        databasesManager.deleteProductFromShoppingCartDatabaseById(context, id)
        view?.showSnackBar(context.resources.getString(R.string.message_delete_product_snack_bar))
        val list: ArrayList<ShoppingCartProduct> = databasesManager.getProductListFromShoppingCartDatabase(context)
        view?.setShoppingCartProductList(list)
        view?.setRecyclerAdapter(list)
        productsQuantity--
        setProductQuantityToView()
        totalCost-=price
        view?.setTotalCostText(totalCost.toString())
        if(productsQuantity == 0){
            view?.showEmptyCardDialog()
        }
    }

    override fun okButtonEmptyCardDialogSelected() {
        navigator.startMainActivity(
            MainActivityPresenter.ProductListFragmentType.NON.ordinal,
            null
        )
    }

    override fun menuButtonHomeSelected() {
        view?.openDrawer()
    }

    override fun activityIsOnStart(context: Context) {
        productsQuantity = getProductQuantityFromShoppingCartDatabase(context)
        setProductQuantityToView()
        totalCost = getTotalCostFromShoppingCartDatabase(context)
        view?.setTotalCostText(totalCost.toString())
        if(productsQuantity == 0){
            view?.showEmptyCardDialog()
        }
    }


// Implements function in interface NavigationContract.ShoppingCartActivityPresenter

    override fun callSuperOnBackPressed() {
        view?.callSuperOnBackPressed()
        view?.closeDrawer()
    }


    private fun setProductQuantityToView(){
        view?.setToolbarText(productsQuantity.toString())
        view?.setTotalProductText(productsQuantity.toString())
    }

    private fun getProductQuantityFromShoppingCartDatabase(context: Context): Int {
        val databasesManager: ShoppingCartActivityContract.Model = DatabasesManager()
        return databasesManager.getProductQuantityFromShoppingCartDatabase(context)
    }

    private fun getTotalCostFromShoppingCartDatabase(context: Context): Float {
        val databasesManager: ShoppingCartActivityContract.Model = DatabasesManager()
        return databasesManager.getTotalCostFromShoppingCartDatabase(context)
    }



}