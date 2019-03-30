package com.study.riseof.shopkotlin.fragment.navigation_view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.study.riseof.shopkotlin.R
import com.study.riseof.shopkotlin.activity.main.MainActivity
import com.study.riseof.shopkotlin.activity.shopping_cart.ShoppingCartActivity
import kotlinx.android.synthetic.main.fragment_navigation_view.*

class NavigationViewFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private var presenter: NavigationViewFragmentContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_navigation_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter = NavigationViewFragmentPresenter
        try {
            when (activity!!::class) {
                MainActivity::class ->
                    presenter?.setCurrentActivity(NavigationViewFragmentPresenter.CurrentActivity.MainActivity)
                ShoppingCartActivity::class ->
                    presenter?.setCurrentActivity(NavigationViewFragmentPresenter.CurrentActivity.ShoppingCartActivity)
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: $e")
        }
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onStop() {
        presenter = null
        super.onStop()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navItemCatalog -> {
                presenter?.navItemCatalogSelected(context)
                return true
            }
            R.id.navItemSmartphones -> {
                presenter?.navItemSmartphonesSelected(context)
                Log.d("myLog", "navItemSmartphones")
            }
            R.id.navItemGraphicTablets -> {
                presenter?.navItemGraphicTabletsSelected(context)
                Log.d("myLog", "navItemGraphicTablets")
            }
            R.id.navItemLaptops -> {
                presenter?.navItemLaptopsSelected(context)
                Log.d("myLog", "navItemLaptopsSelected")
            }
            R.id.navItemCameras -> {
                presenter?.navItemCamerasSelected(context)
                Log.d("myLog", "navItemCamerasSelected")
            }
            R.id.navItemSpeakers -> {
                presenter?.navItemSpeakersSelected(context)
                Log.d("myLog", "navItemSpeakersSelected")
            }
            R.id.navItemHeadphones -> {
                presenter?.navItemHeadphonesSelected(context)
                Log.d("myLog", "navItemHeadphonesSelected")
            }
            R.id.navItemMicrophones -> {
                presenter?.navItemMicrophonesSelected(context)
                Log.d("myLog", "navItemSpeakersSelected")
            }
            R.id.navItemFlashDrives -> {
                presenter?.navItemFlashDrivesSelected(context)
                Log.d("myLog", "navItemHeadphonesSelected")
            }
            else -> {
                Log.d("myLog", "else")
            }
        }
        presenter?.anyCatalogSectionSelected()
        return true
    }
}