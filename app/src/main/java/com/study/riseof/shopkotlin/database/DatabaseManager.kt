package com.study.riseof.shopkotlin.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteCursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.study.riseof.shopkotlin.model.Product
import org.jetbrains.anko.db.*

class DatabaseManager() {

    fun getProductListFromTable(context: Context, tableName: String): ArrayList<Product> {
        val list = ArrayList<Product>()
        try {
            val databaseHelper = DatabaseHelper.getInstance(context)
            val database = databaseHelper.readableDatabase
            database.select(tableName, "*").exec {
                if (this.moveToFirst()) {
                    do {
                        val product: Product = getProductFromCursor(this, tableName)
                        list.add(product)
                    } while (this.moveToNext())
                } else {
                    Log.d("myLog", "!!!!! this.moveToFirst() ")
                }
            }
        }catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
        return list
    }

    private fun getProductFromCursor(cursor: Cursor, tableName: String): Product {
        return Product(
            cursor.getString(DatabaseInfo.COLUMN_INDEX_IMAGE_PATH),
            cursor.getString(DatabaseInfo.COLUMN_INDEX_BRAND),
            cursor.getString(DatabaseInfo.COLUMN_INDEX_NAME),
            cursor.getFloat(DatabaseInfo.COLUMN_INDEX_PRICE),
            cursor.getInt(DatabaseInfo.COLUMN_INDEX_ID),
            tableName
        )
    }

    fun fillAllTables(context: Context) {
        val tableSmartphonesFiller = TableSmartphonesFiller()
        tableSmartphonesFiller.fillTable(context)
    }
}