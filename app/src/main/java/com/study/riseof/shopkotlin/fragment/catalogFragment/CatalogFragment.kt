package com.study.riseof.shopkotlin.fragment.catalogFragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.riseof.shopkotlin.R
import kotlinx.android.synthetic.main.fragment_catalog.*


class CatalogFragment : Fragment() {

    companion object {
        private var instance: CatalogFragment? = null
        @Synchronized
        fun getInstance(): CatalogFragment {
            if (instance == null) {
                instance = CatalogFragment()
            }
            return instance!!
        }
    }

    var presenter: CatalogFragmentContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }


    private val clickListener = View.OnClickListener { v: View ->
    //    try {
            if(presenter==null){
                Log.d("myLog", "presenter==null")
            }

            val con = context
            if(con==null){
                Log.d("myLog", " if(con==null) ")
            }
            when (v) {
                imageSmartphone -> presenter?.imageSmartphoneSelected(con!!)
                imageGraphicTablet -> presenter?.imageGraphicTabletSelected(con!!)
                imageLaptop -> presenter?.imageLaptopSelected(con!!)
                imageCamera -> presenter?.imageCameraSelected(context!!)
                imageSpeakers -> presenter?.imageSpeakersSelected(context!!)
                imageHeadphones -> presenter?.imageHeadphonesSelected(context!!)
                else -> Log.d("myLog", "else")
            }
    //    } catch (e: Exception) {
       //     Log.d("myLog", "context==null Exception: " + e.toString())
      //  }
    }

    override fun onStart() {
        super.onStart()
        presenter = CatalogFragmentPresenter
        setCatalogClickListeners()
    }

    private fun setCatalogClickListeners() {
        imageSmartphone.setOnClickListener(clickListener)
        imageGraphicTablet.setOnClickListener(clickListener)
        imageLaptop.setOnClickListener(clickListener)
        imageCamera.setOnClickListener(clickListener)
        imageSpeakers.setOnClickListener(clickListener)
        imageHeadphones.setOnClickListener(clickListener)
    }

}

