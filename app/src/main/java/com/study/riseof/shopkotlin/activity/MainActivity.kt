package com.study.riseof.shopkotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.study.riseof.shopkotlin.R
import com.study.riseof.shopkotlin.fragment.catalogFragment.CatalogFragment
import com.study.riseof.shopkotlin.fragment.productListFragment.ProductListFragment
import com.study.riseof.shopkotlin.model.Product
import com.study.riseof.shopkotlin.navigation.NavigationContract
import com.study.riseof.shopkotlin.navigation.NavigationManager
import kotlinx.android.synthetic.main.coordinator_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(), MainActivityContract.View, NavigationContract.MainActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainActivityPresenter.activityIsOnCreate(this)
        setSupportActionBar(toolbar)
        createCatalogFragment()
        toast("Hi there!")
    }

    override fun onStart() {
        super.onStart()
        NavigationManager.setMainActivityToNavigationManager(this)
        MainActivityPresenter.setViewToPresenter(this)
    }

    override fun onStop() {
        super.onStop()
        MainActivityPresenter.setViewToPresenter(null)
        NavigationManager.setMainActivityToNavigationManager(null)
    }

    private fun createCatalogFragment() {
        val catalogFragment = CatalogFragment.getInstance() as Fragment
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, catalogFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun createProductListFragment(productList: ArrayList<Product>) {
        val productListFragment = ProductListFragment() as ProductListFragment
        val testInt: Int = 55
        val args: Bundle? = Bundle()
        args?.putInt("testInt", testInt)
        args?.putParcelableArrayList("productList", productList)
        productListFragment.arguments = args

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(com.study.riseof.shopkotlin.R.id.fragmentContainer, productListFragment)
        transaction.addToBackStack(null)
        transaction.commit()
/*
        WebViewFragment fragment = new WebViewFragment();
        String argName = "newsUrl";
        Bundle fragmentArgs = new Bundle();
        fragmentArgs.putString(argName, newsUrl);
        fragment.setArguments(fragmentArgs);
        replaceFragment(R.id.news_fragment_container, fragment);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(fragmentView, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/

    }

}
