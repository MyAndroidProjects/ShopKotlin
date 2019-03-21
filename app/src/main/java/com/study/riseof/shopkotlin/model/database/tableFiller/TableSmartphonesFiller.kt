package com.study.riseof.shopkotlin.model.database.tableFiller

import com.study.riseof.shopkotlin.model.database.DatabaseInfo

class TableSmartphonesFiller: BaseTableFiller() {
    override val tableName: String
        get() = DatabaseInfo.TABLE_SMARTPHONES
    override val productQuantity: Int
        get() = 20
    override val specialColumnName: String
        get() = DatabaseInfo.COLUMN_SMARTPHONE_DIAGONAL
    override val productImageFolder: String
        get() = "smartphones/"
    override val brands: Array<String>
        get() = arrayOf(
            "Banana", "Cherry", "Plum", "Pear", "Lemon"
        )
    override val imageFileNames: Array<String>
        get() = arrayOf(
            "smartImage0.png", "smartImage1.png", "smartImage2.png",
            "smartImage3.png", "smartImage4.png", "smartImage5.png", "smartImage6.png"
        )

    override val price: Float
        get() =  (30..300).random() * 10f
    override val specialColumnValue: String
        get() = ((4..6).random() + (2..8).random() * 0.1f).toString()+'"'
}