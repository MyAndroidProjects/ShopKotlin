package com.study.riseof.shopkotlin.model.database.tableFiller

import com.study.riseof.shopkotlin.model.database.DatabaseInfo


class TableSpeakersFiller : BaseTableFiller() {
    override val tableName: String
        get() = DatabaseInfo.TABLE_SPEAKERS
    override val productQuantity: Int
        get() = 15
    override val specialColumnName: String
        get() = DatabaseInfo.COLUMN_SPEAKERS_POWER
    override val productImageFolder: String
        get() = "speakers/"
    override
    val brands: Array<String>
        get() = arrayOf(
            "Sound", "Pirate", "Bell", "Ring", "Rumble"
        )
    override
    val imageFileNames: Array<String>
        get() = arrayOf(
            "speakers0.png", "speakers1.png", "speakers2.png",
            "speakers3.png", "speakers4.png"
        )

    override val price: Float
        get() = ((100..3000).random()/10).toFloat()
    override val specialColumnValue: String
        get() = (15..40).random().toString()+"W"

}