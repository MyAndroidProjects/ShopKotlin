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
        Log.d("myLog", " DatabaseManager START tableName = "+tableName)
        val list = ArrayList<Product>()
        val databaseHelper = DatabaseHelper.getInstance(context)
        val database = databaseHelper.readableDatabase
        database.select(tableName, "*").exec {
            if (this.moveToFirst()) {
                do {
                    Log.d("myLog", " Cursor STEP")
                    val product :Product =getProductFromCursor(this, tableName)
                    list.add(product)
                    val i = list.size-1
                  //  Log.d("myLog", "DatabaseManager getProductListFromTable "+list[i].brand+" "+list[i].price.toString() )
                } while (this.moveToNext())
            } else {
                Log.d("myLog", "!!!!! this.moveToFirst() ")
            }
        }
        return list
    }

    private fun getProductFromCursor(cursor: Cursor, tableName: String): Product {
/*        return Product(
            cursor.getString(DatabaseInfo.COLUMN_INDEX_IMAGE_PATH),
            cursor.getString(DatabaseInfo.COLUMN_INDEX_BRAND),
            cursor.getString(DatabaseInfo.COLUMN_INDEX_NAME),
            cursor.getFloat(DatabaseInfo.COLUMN_INDEX_PRICE),
            cursor.getInt(DatabaseInfo.COLUMN_INDEX_ID),
            tableName
        )*/
        Log.d("myLog", "getProductFromCursor START")
       val path= cursor.getString(DatabaseInfo.COLUMN_INDEX_IMAGE_PATH)
        val brand=cursor.getString(DatabaseInfo.COLUMN_INDEX_BRAND)
        val name= cursor.getString(DatabaseInfo.COLUMN_INDEX_NAME)
        val price= cursor.getFloat(DatabaseInfo.COLUMN_INDEX_PRICE)
        val id= cursor.getInt(DatabaseInfo.COLUMN_INDEX_ID)
        Log.d("myLog", "getProductFromCursor "+path+" "+brand+" "+ name+" "+ price+" "+id)
        return Product(path,brand,name, price,id,tableName)
    }

    fun fillAllTables(context: Context) {
        val tableSmartphonesFiller = TableSmartphonesFiller()
        tableSmartphonesFiller.fillTable(context)
    }
}