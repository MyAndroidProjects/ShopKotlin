package com.study.riseof.shopkotlin.activity.shopping_cart

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct
import kotlinx.android.synthetic.main.item_shopping_cart_list.view.*

class ShoppingCartActivityRecyclerAdapter(
    private val list: ArrayList<ShoppingCartProduct>,
    private val clickListener: ProductListButtonDeleteClickListener
) :
    RecyclerView.Adapter<ShoppingCartActivityRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(com.study.riseof.shopkotlin.R.layout.item_shopping_cart_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isEmpty()) {
            Log.d("myLog", " onBindViewHolder list.isEmpty() ")
        }
        val item = list[position]
        val brandAndName = "${item.brand} ${item.name},"
        holder.productView.productShoppingCartType.text = item.type
        holder.productView.productShoppingCartBrandAndName.text = brandAndName
        holder.productView.productShoppingCartFeature.text = item.feature
        holder.productView.productShoppingCartPrice.text = item.price.toString()

        holder.productView.buttonDelete.setOnClickListener {
            clickListener.onDeleteFromCartButtonClick(item)
        }
    }


    class ViewHolder(val productView: View) : RecyclerView.ViewHolder(productView)

    interface ProductListButtonDeleteClickListener {
        fun onDeleteFromCartButtonClick(product: ShoppingCartProduct)
    }
}