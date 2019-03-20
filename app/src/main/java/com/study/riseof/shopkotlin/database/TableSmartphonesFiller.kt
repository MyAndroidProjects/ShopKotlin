package com.study.riseof.shopkotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.*
import kotlin.random.Random

class TableSmartphonesFiller {
    private val smartphonesQuantity = 20
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
                while (i < smartphonesQuantity) {
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
                showBaseInLog(this)
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
        return true
    }

    fun showBaseInLog(database: SQLiteDatabase?) {
        try {
            database?.select(DatabaseInfo.TABLE_SMARTPHONES, "*")?.exec {
                if (this.moveToFirst()) {
                    do {
                        Log.d("myLog", "TableSmartphonesFiller STEP ")
                        Log.d(
                            "myLog", "id= " + this.getInt(0) + " " + this.getString(1) + " " +
                                    this.getInt(2) + " " + this.getString(3) + " " +
                                    getInt(4) + " " + getFloat(5)
                        )
                    } while (this.moveToNext())
                } else {
                    Log.d("myLog", "TableSmartphonesFiller !!!!! this.moveToFirst() ")
                }
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
    }
}