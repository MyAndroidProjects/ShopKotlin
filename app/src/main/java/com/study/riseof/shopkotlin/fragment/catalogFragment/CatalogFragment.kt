package com.study.riseof.shopkotlin.fragment.catalogFragment


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
        @Synchronized
        fun getInstance(): CatalogFragment {
            return  CatalogFragment()
        }
    }

    private var presenter: CatalogFragmentContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }


    private val clickListener = View.OnClickListener { v: View ->
        when (v) {
            imageSmartphone -> presenter?.imageSmartphoneSelected(context)
            imageGraphicTablet -> presenter?.imageGraphicTabletSelected(context)
            imageLaptop -> presenter?.imageLaptopSelected(context)
            imageCamera -> presenter?.imageCameraSelected(context)
            imageSpeakers -> presenter?.imageSpeakersSelected(context)
            imageHeadphones -> presenter?.imageHeadphonesSelected(context)
            imageMicrophone ->presenter?.imageMicrophoneSelected(context)
            imageFlashDrive->presenter?.imageFlashDriveSelected(context)
            else -> Log.d("myLog", "else")
        }
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

    override fun onStop() {
        Log.d("myLog", " onStop " + this.toString())
        presenter = null
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
}

