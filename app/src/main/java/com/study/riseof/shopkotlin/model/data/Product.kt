package com.study.riseof.shopkotlin.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Product(
    open val imagePath: String,
    open val brand: String,
    open val name: String,
    open val price: Float,
    open val id: Int,
    open val tableName: String
) : Parcelable {
}