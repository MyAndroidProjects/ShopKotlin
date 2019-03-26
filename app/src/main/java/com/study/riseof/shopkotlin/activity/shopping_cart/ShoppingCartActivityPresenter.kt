package com.study.riseof.shopkotlin.activity.shopping_cart

import android.content.Context
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct
import com.study.riseof.shopkotlin.model.database.DatabasesManager
import com.study.riseof.shopkotlin.navigation.NavigationContract

object ShoppingCartActivityPresenter : ShoppingCartActivityContract.Presenter,
    NavigationContract.ShoppingCartActivityPresenter {

    private var view: ShoppingCartActivityContract.View? = null
    private val databasesManager: ShoppingCartActivityContract.Model = DatabasesManager()


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
//        TODO("not implemented")
        // диалог подтверждения
    }

    override fun buttonBuySelected() {
        //      TODO("not implemented")
    }

    override fun deleteItemButtonShoppingCartListSelected(context: Context, product: ShoppingCartProduct) {
        // диалог подтверждения
        databasesManager.deleteProductFromShoppingCartDatabaseById(context, product.id)
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