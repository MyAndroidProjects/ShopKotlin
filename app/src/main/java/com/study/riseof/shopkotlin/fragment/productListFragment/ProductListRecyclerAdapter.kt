package com.study.riseof.shopkotlin.fragment.productListFragment

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.study.riseof.shopkotlin.model.data.Product
import android.view.LayoutInflater
import com.study.riseof.shopkotlin.R
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_product_list.view.*

class ProductListRecyclerAdapter(
    private val list: ArrayList<Product>,
    private val clickListener: ProductListItemClickListener
) :
    RecyclerView.Adapter<ProductListRecyclerAdapter.ViewHolder>() {
    var imageSize: Int = 0
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(com.study.riseof.shopkotlin.R.layout.item_product_list, viewGroup, false)
        imageSize = viewGroup.context.resources.getInteger(R.integer.product_image_size)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(list.isEmpty()){
            Log.d("myLog", " onBindViewHolder list.isEmpty() ")
        }
        val item = list[position]
        holder.productView.productName.text = item.name
        holder.productView.productBrand.text = item.brand
        holder.productView.productPrice.text = item.price.toString()
        Picasso.get()
            .load(item.imagePath)
            .placeholder(com.study.riseof.shopkotlin.R.drawable.ic_baseline_add_shopping_cart_96px)
            .fit()
            .priority(Picasso.Priority.NORMAL)
            .into(holder.productView.productImage)

        holder.itemView.setOnClickListener {
            clickListener.onProductListItemClick(holder.adapterPosition)
        }
    }


    class ViewHolder(val productView: View) : RecyclerView.ViewHolder(productView) {}

    interface ProductListItemClickListener {
        fun onProductListItemClick(position: Int)
    }
}