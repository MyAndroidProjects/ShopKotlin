package com.study.riseof.shopkotlin.database

import android.content.Context
import android.util.Log
import org.jetbrains.anko.db.insert

class TableSpeakersFiller {
    private val productQuantity = 20
    private val imgBasePath = "file:///android_asset/pictures/smartphones/"
    private val brands: Array<String> = arrayOf(
        "Banana", "Cherry", "Plum", "Pear", "Lemon"
    )
    private val imageNames: Array<String> = arrayOf(
        "smartImage0.png", "smartImage1.png", "smartImage2.png",
        "smartImage3.png", "smartImage4.png", "smartImage5.png", "smartImage6.png"
    )

    fun fillTable(context: Context): Boolean {
        try {
            val dataBaseHelper = DatabaseHelper.getInstance(context)
            dataBaseHelper.use {
                var i = 0
                while (i < productQuantity) {
                    val brand = brands[(0..4).random()]
                    val name = (10..900).random() * 10
                    val path = imgBasePath + imageNames[(0..6).random()]
                    val price = (30..300).random() * 10
                    val diagonal = (4..6).random() + (2..8).random() * 0.1f

                    insert(
                        DatabaseInfo.TABLE_SMARTPHONES,
                        DatabaseInfo.COLUMN_PRODUCT_BRAND to brand,
                        DatabaseInfo.COLUMN_PRODUCT_NAME to name,
                        DatabaseInfo.COLUMN_PRODUCT_IMAGE_PATH to path,
                        DatabaseInfo.COLUMN_PRODUCT_PRICE to price,
                        DatabaseInfo.COLUMN_SMARTPHONE_DIAGONAL to diagonal
                    )
                    Log.d("myLog", brand + " " + name + " " + path + " " + price + " " + diagonal)
                    i++
                }
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
        return true
    }

}