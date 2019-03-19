package com.study.riseof.shopkotlin.fragment.navigationViewFragment

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.study.riseof.shopkotlin.R
import kotlinx.android.synthetic.main.fragment_navigation_view.*

class NavigationViewFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    var presenter: NavigationViewFragmentContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d("myLog", "onCreateView")
        return inflater.inflate(R.layout.fragment_navigation_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter = NavigationViewFragmentPresenter
        navigationView.setNavigationItemSelectedListener(this)
        Log.d("myLog", "onStart")
    }

    override fun onStop() {
        super.onStop()
        presenter = null
    }

    override fun onResume() {
        super.onResume()
        Log.d("myLog", "onResume")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navItemSmartphones -> {
                presenter?.navItemSmartphonesSelected()
                Log.d("myLog", "navItemSmartphones")
            }
            R.id.navItemGraphicTablets -> {
                presenter?.navItemGraphicTabletsSelected()
                Log.d("myLog", "navItemGraphicTablets")
            }
            R.id.navItemLaptops -> {
                presenter?.navItemLaptopsSelected()
                Log.d("myLog", "navItemGraphicTablets")
            }
            R.id.navItemCameras -> {
                presenter?.navItemCamerasSelected()
                Log.d("myLog", "navItemGraphicTablets")
            }
            R.id.navItemSpeakers -> {
                presenter?.navItemSpeakersSelected()
                Log.d("myLog", "navItemGraphicTablets")
            }
            R.id.navItemHeadphones -> {
                presenter?.navItemHeadphonesSelected()
                Log.d("myLog", "navItemGraphicTablets")
            }
            else -> {
                Log.d("myLog", "else")
            }
        }
        presenter?.anyNavigationItemSelected()
        return true
    }
}