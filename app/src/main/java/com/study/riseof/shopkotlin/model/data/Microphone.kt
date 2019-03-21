package com.study.riseof.shopkotlin.model.data

import com.study.riseof.shopkotlin.model.database.DatabaseInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Microphone(
    override val imagePath: String,
    override val brand: String,
    override val name: String,
    override val price: Float,
    val sensitivity: String,
    override val id: Int,
    override val tableName: String = DatabaseInfo.TABLE_MICROPHONES
) : Product(imagePath, brand, name, price, id, tableName) {
}