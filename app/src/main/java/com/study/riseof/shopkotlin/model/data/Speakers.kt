package com.study.riseof.shopkotlin.model.data

import com.study.riseof.shopkotlin.model.database.DatabaseInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Speakers(
    override val imagePath: String,
    override val brand: String,
    override val name: String,
    override val price: Float,
    val power: String,
    override val id: Int,
    override val tableName: String = DatabaseInfo.TABLE_SPEAKERS
) : Product(imagePath, brand, name, price, id, tableName) {
}