package com.study.riseof.shopkotlin.activity.shopping_cart

import android.content.Context
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct

interface ShoppingCartActivityContract {
    interface View {
        fun setRecyclerAdapter(list: ArrayList<ShoppingCartProduct>)
        fun setShoppingCartProductList(list: ArrayList<ShoppingCartProduct>)
        fun openDrawer()
        fun closeDrawer()
        fun callSuperOnBackPressed()
        fun showDeleteProductDialog(message: String, id: Int, price: Float)
        fun showDeleteAllInShoppingCartDialog()
        fun showBuyProductsDialog()
        fun showEmptyCardDialog()
        fun showSnackBar(message: String)
        fun setToolbarText(text: String)
        fun setTotalProductText(text: String)
        fun setTotalCostText(text: String)
    }

    interface Presenter {
        fun setViewToPresenter(view: View?)
        fun menuButtonHomeSelected()
        fun backButtonSelected()
        fun buttonBackSelected()
        fun buttonCleanSelected()
        fun buttonBuySelected()
        fun deleteItemButtonShoppingCartListSelected(context: Context, product: ShoppingCartProduct)
        fun yesButtonDeleteAllDialogSelected(context: Context)
        fun yesButtonDeleteProductDialogSelected(context: Context, id: Int, price: Float)
        fun yesButtonBuyDialogSelected(context: Context)
        fun okButtonEmptyCardDialogSelected()
        fun activityIsOnStart(context: Context)
    }

    interface Navigator {
        fun startMainActivity(fragmentType: Int, startSnackBarMessage: String?)

    }

    interface Model {
        fun deleteProductFromShoppingCartDatabaseById(context: Context, id: Int)
        fun getProductListFromShoppingCartDatabase(context: Context): ArrayList<ShoppingCartProduct>
        fun deleteAllInShoppingCartDatabase(context: Context)
        fun getProductQuantityFromShoppingCartDatabase(context: Context): Int
        fun getTotalCostFromShoppingCartDatabase(context: Context): Float
    }
}