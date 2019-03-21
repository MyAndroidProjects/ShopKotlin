package com.study.riseof.shopkotlin.model.database.tableFiller

import com.study.riseof.shopkotlin.model.database.DatabaseInfo

class TableFlashDrivesFiller : BaseTableFiller() {
    override val tableName: String
        get() = DatabaseInfo.TABLE_FLASH_DRIVES
    override val productQuantity: Int
        get() = 40
    override val specialColumnName: String
        get() = DatabaseInfo.COLUMN_FLASH_MEMORY_CAPACITY
    override val productImageFolder: String
        get() = "flashDrives/"
    override val brands: Array<String>
        get() = arrayOf(
            "Lightning", "Flash", "Case", "Safe", "Stock", "Garage"
        )
    override val imageFileNames: Array<String>
        get() = arrayOf(
            "flash0.png", "flash1.png", "flash2.png",
            "flash3.png", "flash4.png", "flash5.png", "flash6.png"
        )

    override val price: Float
        get() = (10..200).random() / 10f

    private val memoryCapacities: Array<String>
        get() = arrayOf(
            "8 Gb",
            "16 Gb",
            "32 Gb",
            "64 Gb"
        )

    override val specialColumnValue: String
        get() = memoryCapacities[(0 until memoryCapacities.size).random()]
}