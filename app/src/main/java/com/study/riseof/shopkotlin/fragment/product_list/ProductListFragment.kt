package com.study.riseof.shopkotlin.fragment.product_list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.riseof.shopkotlin.model.data.Product
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_product_list.*


class ProductListFragment : Fragment(), ProductListRecyclerAdapter.ProductListItemClickListener {

    companion object {
        const val keyProductList: String = "productList"
        @Synchronized
        fun getInstance(productList: ArrayList<Product>): ProductListFragment {
            val fragment = ProductListFragment()
            val args = Bundle()
            args.putParcelableArrayList(keyProductList, productList)
            fragment.arguments = args
            return fragment
        }
    }

    private var productList: ArrayList<Product> = ArrayList()
    private var presenter: ProductListFragmentContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getValuesFromSaveInstanceState(savedInstanceState)
        getValuesFromArguments()
        return inflater.inflate(com.study.riseof.shopkotlin.R.layout.fragment_product_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter = ProductListFragmentPresenter
        setRecyclerAdapter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(keyProductList, productList)
    }

    override fun onStop() {
        presenter = null
        super.onStop()
    }

    override fun onProductListItemClick(product: Product) {
        presenter?.selectProduct(product)
    }

    private fun getValuesFromArguments() {
        productList = arguments?.getParcelableArrayList<Product>(keyProductList) ?: return
    }

    private fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?) {
        productList = savedInstanceState?.getParcelableArrayList<Product>(keyProductList) ?: return
    }

    private fun setRecyclerAdapter() {
        val adapter = ProductListRecyclerAdapter(productList, this)
        val layoutManager = LinearLayoutManager(context)
        recyclerViewProductList.layoutManager = layoutManager
        recyclerViewProductList.adapter = adapter

    }
}