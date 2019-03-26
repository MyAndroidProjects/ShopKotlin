package com.study.riseof.shopkotlin.model.database.simple_shop.fillers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseHelper
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo
import org.jetbrains.anko.db.*

abstract class BaseTableFiller {
    protected val basePath: String = "file:///android_asset/pictures/"

    protected abstract val tableName: String
    protected abstract val productQuantity: Int
    protected abstract val specialColumnName: String

    protected abstract val brands: Array<String>
    protected abstract val imageFileNames: Array<String>
    protected abstract val productImageFolder: String
    protected open val brand: String
        get() = brands[(0 until brands.size).random()]
    protected open val name: String
        get() = getRandomLetterUppercase() + ((10..900).random() * 10).toString()
    protected open val imagePath: String
        get() = basePath + productImageFolder + imageFileNames[(0 until imageFileNames.size).random()]
    protected abstract val price: Float
    protected abstract val specialColumnValue: String

    open fun fillTable(context: Context): Boolean {
        try {
            val dataBaseHelper = ShopDatabaseHelper.getInstance(context)
            dataBaseHelper.use {
                var i = 0
                while (i < productQuantity) {
                    insert(
                        tableName,
                        ShopDatabaseInfo.COLUMN_PRODUCT_BRAND to brand,
                        ShopDatabaseInfo.COLUMN_PRODUCT_NAME to name,
                        ShopDatabaseInfo.COLUMN_PRODUCT_IMAGE_PATH to imagePath,
                        ShopDatabaseInfo.COLUMN_PRODUCT_PRICE to price,
                        specialColumnName to specialColumnValue
                    )
                    i++
                }
                //  showBaseInLog(this)
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
            return false
        }
        return true
    }

    protected open fun getRandomLetterUppercase(): String {
        return (65..90).random().toChar().toString()
    }

    @Suppress("unused")
    private fun showBaseInLog(database: SQLiteDatabase?) {
        try {
            database?.select(tableName, "*")?.exec {
                if (this.moveToFirst()) {
                    do {
                        Log.d(
                            "myLog", "id= " + this.getInt(ShopDatabaseInfo.COLUMN_INDEX_ID) + " "
                                    + this.getString(ShopDatabaseInfo.COLUMN_INDEX_BRAND) + " " +
                                    this.getString(ShopDatabaseInfo.COLUMN_INDEX_NAME) + " " +
                                    this.getFloat(ShopDatabaseInfo.COLUMN_INDEX_PRICE) + " " +
                                    getString(ShopDatabaseInfo.COLUMN_INDEX_FEATURE) + " " +
                                    getString(ShopDatabaseInfo.COLUMN_INDEX_IMAGE_PATH)
                        )
                    } while (this.moveToNext())
                } else {
                    Log.d("myLog", "TableSmartphonesFiller !this.moveToFirst() ")
                }
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
    }
}