package com.study.riseof.shopkotlin.model.database.simple_shop.fillers

import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo

class TableLaptopsFiller : BaseTableFiller() {
    private var tempIndex: Int = 0

    override val tableName: String
        get() = ShopDatabaseInfo.TABLE_LAPTOPS
    override val productQuantity: Int
        get() = 30
    override val specialColumnName: String
        get() = ShopDatabaseInfo.COLUMN_LAPTOP_COLOR
    override val productImageFolder: String
        get() = "laptops/"
    override val brands: Array<String>
        get() = arrayOf(
            "Banana", "Cherry", "Plum", "Pear", "Lemon"
        )
    override val imageFileNames: Array<String>
        get() = arrayOf(
            "laptopBlack0.png",
            "laptopBlack1.png",
            "laptopBrown0.png",
            "laptopGrey0.png",
            "laptopGrey1.png",
            "laptopRed0.png",
            "laptopRed1.png",
            "laptopWhite0.png",
            "laptopWhite1.png"
        )

    private val laptopColors: Array<String>
        get() = arrayOf(
            "black",
            "black",
            "Brown",
            "Grey",
            "Grey",
            "Red",
            "Red",
            "White",
            "White"
        )

    override val name: String
        get() = getRandomLetterUppercase() +
                ((10..900).random() * 10).toString() +
                getRandomLetterUppercase() +
                getRandomLetterUppercase() +
                getRandomLetterUppercase()

    override val price: Float
        get() = (50..300).random() * 10f

    override val specialColumnValue: String
        get() = laptopColors[tempIndex]


    override val imagePath: String
        get() {
            tempIndex = (0 until imageFileNames.size).random()
            return basePath + productImageFolder + imageFileNames[tempIndex]
        }
}