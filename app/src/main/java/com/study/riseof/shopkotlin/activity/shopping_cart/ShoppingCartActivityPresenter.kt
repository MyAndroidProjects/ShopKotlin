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

    // private val databasesManager: ShoppingCartActivityContract.Model = DatabasesManager()


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
        view?.showDeleteProductDialog(message, product.id)


    }

    override fun yesButtonDeleteProductDialogSelected(context: Context, id: Int) {
        val databasesManager: ShoppingCartActivityContract.Model = DatabasesManager()
        databasesManager.deleteProductFromShoppingCartDatabaseById(context, id)
        view?.showSnackBar(context.resources.getString(R.string.message_delete_product_snack_bar))
        val list: ArrayList<ShoppingCartProduct> = databasesManager.getProductListFromShoppingCartDatabase(context)
        view?.setShoppingCartProductList(list)
        view?.setRecyclerAdapter(list)
    }

    override fun menuButtonHomeSelected() {
        view?.openDrawer()
    }

    override fun callSuperOnBackPressed() {
        view?.callSuperOnBackPressed()
        view?.closeDrawer()
    }

}