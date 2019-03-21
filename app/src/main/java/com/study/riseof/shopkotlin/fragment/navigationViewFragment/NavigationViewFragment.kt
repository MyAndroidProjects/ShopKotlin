package com.study.riseof.shopkotlin.fragment.navigationViewFragment

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.study.riseof.shopkotlin.R
import kotlinx.android.synthetic.main.fragment_navigation_view.*

class NavigationViewFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private var presenter: NavigationViewFragmentContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d("myLog", "onCreateView")
        return inflater.inflate(R.layout.fragment_navigation_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter = NavigationViewFragmentPresenter
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onStop() {
        Log.d("myLog", " onStop "+this.toString())
        presenter = null
        super.onStop()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navItemCatalog-> {
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
            R.id.navItemFlashDrives-> {
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

/*    override fun onDestroyView() {
        Log.d("myLog", " onDestroyView "+this.toString())
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("myLog", " onDestroy "+this.toString())
        super.onDestroy()
    }*/
}