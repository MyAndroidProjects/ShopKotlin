package com.study.riseof.shopkotlin.fragment.productListFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.riseof.shopkotlin.model.Product
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.fragment_product_list.*


class ProductListFragment : Fragment(), ProductListRecyclerAdapter.ProductListItemClickListener {

    companion object {
        const val keyProductList: String = "productList"
       @Synchronized
        fun getInstance(productList: ArrayList<Product>): ProductListFragment {
     //       Log.d("myLog", "getInstance ProductListFragment ")
            val fragment = ProductListFragment()
            val args = Bundle()
            args.putParcelableArrayList(keyProductList, productList)
    //        Log.d("myLog", "productList1 " + productList.toString())
            fragment.arguments = args
            return fragment
        }
    }


    private var productList: ArrayList<Product> = ArrayList()
    private var presenter: ProductListFragmentContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
     //   Log.d("myLog", "ProductListFragment onCreateView ")
     //   Log.d("myLog", "productList2 " + productList.toString())
        getValuesFromSaveInstanceState(savedInstanceState)
     //   Log.d("myLog", "productList3 " + productList.toString())
        getValuesFromArguments()
     //   Log.d("myLog", "productList4 " + productList.toString())

        return inflater.inflate(com.study.riseof.shopkotlin.R.layout.fragment_product_list, container, false)
    }

    private fun getValuesFromArguments() {
        productList = arguments?.getParcelableArrayList<Product>(keyProductList) ?: return
    }

    private fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?) {
        productList = savedInstanceState?.getParcelableArrayList<Product>(keyProductList) ?: return
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(keyProductList, productList);
    }


    override fun onStart() {
        super.onStart()
        Log.d("myLog", " onStart " + this.toString())
        presenter = ProductListFragmentPresenter
    //    Log.d("myLog", "productList5 " + productList.toString())
        setRecyclerAdapter()
    }

    override fun onStop() {
        presenter = null
        Log.d("myLog", " onStop " + this.toString())
        super.onStop()
    }

/*    override fun onDestroyView() {
        Log.d("myLog", " onDestroyView " + this.toString())
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("myLog", " onDestroy " + this.toString())
        super.onDestroy()
    }*/

    private fun setRecyclerAdapter() {
    //    Log.d("myLog", "setRecyclerAdapter " + productList.toString())
        val adapter: ProductListRecyclerAdapter
        val layoutManager: LinearLayoutManager
        adapter = ProductListRecyclerAdapter(productList, this)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }


    override fun onProductListItemClick(position: Int) {
        Log.d("myLog", "onProductListItemClick position = " + position)
    }

}