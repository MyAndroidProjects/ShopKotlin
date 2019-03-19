package com.study.riseof.shopkotlin.model

import android.os.Parcelable
import com.study.riseof.shopkotlin.database.DatabaseInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
class Smartphone(
    override val imagePath: String,
    override val brand: String,
    override val name: String,
    override val price: Float,
    val diagonal: Float,
    override val id: Int,
    override val tableName: String = DatabaseInfo.TABLE_SMARTPHONES
) : Product(imagePath, brand, name, price, id, tableName){
}

