package com.study.riseof.shopkotlin.database

import android.content.Context
import android.util.Log
import org.jetbrains.anko.db.insert

abstract class TableFiller {
    abstract val productQuantity: Int
    abstract val imgBasePath: String
    abstract val brands: Array<String>
    abstract val imageNames: Array<String>


    abstract var brand :String
    abstract var name :String
    abstract var path :String
    abstract var price :Float
    abstract var specialColumnValue:String

    fun fillTable(context: Context): Boolean {
        try {
            val dataBaseHelper = DatabaseHelper.getInstance(context)
            dataBaseHelper.use {
                var i = 0
                while (i < productQuantity) {


                    insert(
                        DatabaseInfo.TABLE_SMARTPHONES,
                        DatabaseInfo.COLUMN_PRODUCT_BRAND to brand,
                        DatabaseInfo.COLUMN_PRODUCT_NAME to name,
                        DatabaseInfo.COLUMN_PRODUCT_IMAGE_PATH to path,
                        DatabaseInfo.COLUMN_PRODUCT_PRICE to price,
                        DatabaseInfo.COLUMN_SMARTPHONE_DIAGONAL to specialColumnValue
                    )
                    Log.d("myLog", brand + " " + name + " " + path + " " + price + " " + specialColumnValue)
                    i++
                }
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
        return true
    }

}