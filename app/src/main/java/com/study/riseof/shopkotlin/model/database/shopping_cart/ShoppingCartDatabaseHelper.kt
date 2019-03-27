package com.study.riseof.shopkotlin.model.database.shopping_cart

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.*

class ShoppingCartDatabaseHelper(context: Context) :
    ManagedSQLiteOpenHelper(
        context,
        ShoppingCartDatabaseInfo.DATABASE_NAME, null,
        DB_VERSION
    ) {

    companion object {
        const val DB_VERSION = 1
        private var instance: ShoppingCartDatabaseHelper? = null
        @Synchronized
        fun getInstance(context: Context): ShoppingCartDatabaseHelper {
            if (instance == null) {
                instance =
                    ShoppingCartDatabaseHelper(context.applicationContext)
            }
            try {
                instance!!
            } catch (e: Exception) {
                Log.d("myLog", "ShoppingCartDatabaseHelper Exception: " + e.toString())
            }
            return instance as ShoppingCartDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("myLog", " CREATE onCreate ShoppingCartDatabase ")
        try {
            db?.createTable(
                ShoppingCartDatabaseInfo.TABLE_NAME, true,
                ShoppingCartDatabaseInfo.COLUMN_PRODUCT_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ShoppingCartDatabaseInfo.COLUMN_PRODUCT_TYPE to TEXT,
                ShoppingCartDatabaseInfo.COLUMN_PRODUCT_BRAND to TEXT,
                ShoppingCartDatabaseInfo.COLUMN_PRODUCT_NAME to TEXT,
                ShoppingCartDatabaseInfo.COLUMN_PRODUCT_FEATURE to TEXT,
                ShoppingCartDatabaseInfo.COLUMN_PRODUCT_PRICE to REAL
            )
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
