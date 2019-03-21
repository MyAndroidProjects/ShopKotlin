package com.study.riseof.shopkotlin.model.database.tableFiller

import com.study.riseof.shopkotlin.model.database.DatabaseInfo

class TableGraphicTabletsFiller : BaseTableFiller() {
    override val tableName: String
        get() = DatabaseInfo.TABLE_GRAPHIC_TABLETS
    override val productQuantity: Int
        get() = 15
    override val specialColumnName: String
        get() = DatabaseInfo.COLUMN_GRAPHIC_TABLET_FORMAT
    override val productImageFolder: String
        get() = "graphicTablets/"
    override val brands: Array<String>
        get() = arrayOf(
            "Vacuum", "Junior"
        )
    override val imageFileNames: Array<String>
        get() = arrayOf(
            "tablet0.png", "tablet1.png", "tablet2.png",
            "tablet3.png", "tablet4.png"
        )

    override val price: Float
        get() = (10..30).random() * 10f
    override val specialColumnValue: String
        get() = "A" + (4..6).random().toString()
}