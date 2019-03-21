package com.study.riseof.shopkotlin.model.database.tableFiller

import com.study.riseof.shopkotlin.model.database.DatabaseInfo

class TableHeadphonesFiller : BaseTableFiller() {
    private var tempIndex: Int = 0

    override val tableName: String
        get() = DatabaseInfo.TABLE_HEADPHONES
    override val productQuantity: Int
        get() = 20
    override val specialColumnName: String
        get() = DatabaseInfo.COLUMN_HEADPHONES_TYPE
    override val productImageFolder: String
        get() = "headphones/"
    override val brands: Array<String>
        get() = arrayOf(
            "Ear", "Sound", "Voice", "Head", "Melody"
        )
    override val imageFileNames: Array<String>
        get() = arrayOf(
            "headphones0.png", "headphones1.png", "headphones2.png",
            "headphones3.png", "headphones4.png"
        )

    override val price: Float
        get() =  (3..50).random() * 1f

    private val headphonesTypes: Array<String>
        get() = arrayOf(
            "Full size",
            "Ear bud",
            "Full size",
            "Full size",
            "Ear bud"
        )

    override val specialColumnValue: String
        get() = headphonesTypes[tempIndex]

    override val imagePath: String
        get() {
            tempIndex = (0 until imageFileNames.size).random()
            return basePath + productImageFolder + imageFileNames[tempIndex]
        }
}