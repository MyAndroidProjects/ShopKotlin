package com.study.riseof.shopkotlin.model.database.simple_shop.fillers

import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo

class TableCamerasFiller : BaseTableFiller() {
    override val tableName: String
        get() = ShopDatabaseInfo.TABLE_CAMERAS
    override val productQuantity: Int
        get() = 12
    override val specialColumnName: String
        get() = ShopDatabaseInfo.COLUMN_CAMERA_RESOLUTION
    override val productImageFolder: String
        get() = "cameras/"
    override val brands: Array<String>
        get() = arrayOf(
            "Kikon", "Monak", "Malanoit"
        )
    override val imageFileNames: Array<String>
        get() = arrayOf(
            "camera0.png", "camera1.png", "camera2.png", "camera3.png"
        )
    override val price: Float
        get() = (30..300).random() * 10f


    override val specialColumnValue: String
        get()  = (8..18).random().toString() + "k"
}