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
    }

    interface Presenter {
        fun setViewToPresenter(view: View?)
        fun menuButtonHomeSelected()
        fun backButtonSelected()
        fun buttonBackSelected()
        fun buttonCleanSelected()
        fun buttonBuySelected()
        fun deleteItemButtonShoppingCartListSelected(context: Context, product: ShoppingCartProduct)
    }

    interface Navigator {

    }

    interface Model {
        fun deleteProductFromShoppingCartDatabaseById(context: Context, id: Int)
        fun getProductListFromShoppingCartDatabase(context: Context): ArrayList<ShoppingCartProduct>
    }
}