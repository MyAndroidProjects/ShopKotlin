package com.study.riseof.shopkotlin.fragment.productListFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.riseof.shopkotlin.model.Product
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.study.riseof.shopkotlin.model.Smartphone
import kotlinx.android.synthetic.main.fragment_product_list.*


class ProductListFragment : Fragment(), ProductListRecyclerAdapter.ProductListItemClickListener {

    private var productList: ArrayList<Product> = ArrayList()

/*    companion object {
        const val productListVarName: String = "productList"
        private var instance: ProductListFragment? = null
        @Synchronized
        fun getInstance(productList: ArrayList<Product>): ProductListFragment {
            if (instance == null) {
                instance = ProductListFragment()
            }
            val args = Bundle()
            args.putParcelableArrayList(productListVarName, productList)
            instance?.arguments = args
            return instance!!
        }
    }*/

/*    newInstance(int someInt) {
        MyFragment myFragment = new MyFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    fun newInstance(someInt: Int): ??? {
        val myFragment = MyFragment()

        val args = Bundle()
        args.putInt("someInt", someInt)
        myFragment.setArguments(args)

        return myFragment
    }*/
/*    private fun fillList() {
        productList.add(
            Smartphone("file:///android_asset/pictures/smartphones/smartImage0.png", "brand1", "name1", 1100f, 4.4f)
        )
        productList.add(
            Smartphone("file:///android_asset/pictures/smartphones/smartImage1.png", "brand2", "name2", 220f, 5.2f)
        )
        productList.add(
            Smartphone("file:///android_asset/pictures/smartphones/smartImage2.png", "brand3", "name3", 33300f, 3.8f)
        )
        productList.add(
            Smartphone("file:///android_asset/pictures/smartphones/smartImage3.png", "brand4", "name4", 4500.48f, 6.8f)
        )
        productList.add(
            Smartphone("file:///android_asset/pictures/smartphones/smartImage4.png", "brand5", "name5", 5250f, 5f)
        )
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d("myLog", "ProductListFragment onCreateView ")

        val tII= arguments?.getInt("testInt",741)!!
        productList = arguments?.getParcelableArrayList<Product>("productList")!!
        Log.d("myLog", " tII =  "+tII)
        Log.d("myLog", "arguments?.getParcelableArrayList productList  " + productList.toString())
/*        val tI = savedInstanceState?.getInt("testInt",123)!!
        Log.d("myLog", " tI =  "+tI)*/
/*        try {
            if (savedInstanceState == null) {
                Log.d("myLog", " savedInstanceState == null ")
            } else {
                val t=savedInstanceState.getParcelableArrayList<Product>(productListVarName)

                if(t==null){
                    Log.d("myLog", "t==null")
                }else{
                    productList = t
                }

            }
          //  productList = savedInstanceState?.getParcelableArrayList<Product>(productListVarName)!!
            Log.d("myLog", "getParcelableArrayList productList  " + productList.toString())
        } catch (e: Exception) {
            Log.d("myLog", "productList == null  Exception: " + e.toString())
        }*/
        return inflater.inflate(com.study.riseof.shopkotlin.R.layout.fragment_product_list, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
      //  outState.putParcelableArrayList(productListVarName, productList);
    }


    override fun onStart() {
        super.onStart()
        setRecyclerAdapter()
    }

    private fun setRecyclerAdapter() {
        Log.d("myLog", "setRecyclerAdapter ")
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