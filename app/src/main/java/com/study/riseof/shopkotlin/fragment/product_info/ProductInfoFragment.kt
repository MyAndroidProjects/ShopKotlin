package com.study.riseof.shopkotlin.fragment.product_info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.study.riseof.shopkotlin.R
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo
import kotlinx.android.synthetic.main.fragment_product_info.*

class ProductInfoFragment : Fragment() {

    companion object {
        const val keyProduct: String = "product"
        @Synchronized
        fun getInstance(product: Product): ProductInfoFragment {
            val fragment = ProductInfoFragment()
            val args = Bundle()
            args.putParcelable(keyProduct, product)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var product: Product

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getValuesFromSaveInstanceState(savedInstanceState)
        getValuesFromArguments()
        return inflater.inflate(R.layout.fragment_product_info, container, false)
    }

    override fun onStart() {
        super.onStart()
        fillViewsContents(product)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(keyProduct, product)
    }

    private fun getValuesFromArguments() {
        product = arguments?.getParcelable(keyProduct) ?: return
    }

    private fun getValuesFromSaveInstanceState(savedInstanceState: Bundle?) {
        product = savedInstanceState?.getParcelable(keyProduct) ?: return
    }

    private fun fillViewsContents(product: Product) {
        setFeatureHeader(product.tableName)
        productInfoBrand.text = product.brand
        productInfoName.text = product.name
        productInfoFeature.text = product.feature
        productInfoPrice.text = product.price.toString()
        Picasso.get()
            .load(product.imagePath)
            .placeholder(com.study.riseof.shopkotlin.R.drawable.ic_baseline_add_shopping_cart_96px)
            .fit()
            .priority(Picasso.Priority.NORMAL)
            .into(productInfoImage)
    }

    private fun setFeatureHeader(tableName: String) {
        when (tableName) {
            ShopDatabaseInfo.TABLE_CAMERAS -> featureHeader.text = resources.getString(R.string.feature_cameras)
            ShopDatabaseInfo.TABLE_FLASH_DRIVES -> featureHeader.text =
                resources.getString(R.string.feature_flash_drives)
            ShopDatabaseInfo.TABLE_GRAPHIC_TABLETS -> featureHeader.text =
                resources.getString(R.string.feature_graphic_tablets)
            ShopDatabaseInfo.TABLE_HEADPHONES -> featureHeader.text = resources.getString(R.string.feature_headphones)
            ShopDatabaseInfo.TABLE_LAPTOPS -> featureHeader.text = resources.getString(R.string.feature_laptops)
            ShopDatabaseInfo.TABLE_MICROPHONES -> featureHeader.text = resources.getString(R.string.feature_microphone)
            ShopDatabaseInfo.TABLE_SMARTPHONES -> featureHeader.text = resources.getString(R.string.feature_smartphones)
            ShopDatabaseInfo.TABLE_SPEAKERS -> featureHeader.text = resources.getString(R.string.feature_speakers)
        }
    }
}