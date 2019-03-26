package com.study.riseof.shopkotlin.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingCartProduct (
     val id: Int,
     val type: String,
     val brand: String,
     val name: String,
     val feature: String,
     val price: Float
) : Parcelable {
}