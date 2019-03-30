package com.study.riseof.shopkotlin.model.database.simple_shop.fillers

import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo

class TableMicrophonesFiller : BaseTableFiller() {
    override val tableName: String
        get() = ShopDatabaseInfo.TABLE_MICROPHONES
    override val productQuantity: Int
        get() = 15
    override val specialColumnName: String
        get() = ShopDatabaseInfo.COLUMN_MICROPHONE_SENSITIVITY
    override val productImageFolder: String
        get() = "microphones/"
    override val brands: Array<String>
        get() = arrayOf(
            "Voice", "Speaker", "Sound"
        )
    override val imageFileNames: Array<String>
        get() = arrayOf(
            "microphone0.png", "microphone1.png", "microphone2.png",
            "microphone3.png", "microphone4.png"
        )

    override val price: Float
        get() = (2..15).random().toFloat()
    override val specialColumnValue: String
        get() = (23..56).random().toString() + "dB"
}