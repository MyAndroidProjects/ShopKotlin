package com.study.riseof.shopkotlin.model.database.simple_shop

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.*

class ShopDatabaseHelper(context: Context) :
    ManagedSQLiteOpenHelper(context,
        ShopDatabaseInfo.DATABASE_NAME, null,
        DB_VERSION
    ) {

    companion object {
        const val DB_VERSION = 1
        private var instance: ShopDatabaseHelper? = null
        @Synchronized
        fun getInstance(context: Context): ShopDatabaseHelper {
            if (instance == null) {
                instance =
                    ShopDatabaseHelper(context.applicationContext)
            }
            try {
                instance!!
            } catch (e: Exception) {
                Log.d("myLog", "ShopDatabaseHelper Exception: " + e.toString())
            }
            return instance as ShopDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createAllTables(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    private fun createAllTables(db: SQLiteDatabase?) {
        createTable(db,
            ShopDatabaseInfo.TABLE_SMARTPHONES,
            ShopDatabaseInfo.COLUMN_SMARTPHONE_DIAGONAL
        )
        createTable(db,
            ShopDatabaseInfo.TABLE_GRAPHIC_TABLETS,
            ShopDatabaseInfo.COLUMN_GRAPHIC_TABLET_FORMAT
        )
        createTable(db,
            ShopDatabaseInfo.TABLE_LAPTOPS,
            ShopDatabaseInfo.COLUMN_LAPTOP_COLOR
        )
        createTable(db,
            ShopDatabaseInfo.TABLE_CAMERAS,
            ShopDatabaseInfo.COLUMN_CAMERA_RESOLUTION
        )
        createTable(db,
            ShopDatabaseInfo.TABLE_SPEAKERS,
            ShopDatabaseInfo.COLUMN_SPEAKERS_POWER
        )
        createTable(db,
            ShopDatabaseInfo.TABLE_HEADPHONES,
            ShopDatabaseInfo.COLUMN_HEADPHONES_TYPE
        )
        createTable(db,
            ShopDatabaseInfo.TABLE_MICROPHONES,
            ShopDatabaseInfo.COLUMN_MICROPHONE_SENSITIVITY
        )
        createTable(db,
            ShopDatabaseInfo.TABLE_FLASH_DRIVES,
            ShopDatabaseInfo.COLUMN_FLASH_MEMORY_CAPACITY
        )
    }

    private fun createTable(
        db: SQLiteDatabase?,
        tableName: String,
        specialColumnName: String
    ) {
        try {
            db?.createTable(
                tableName, true,
                ShopDatabaseInfo.COLUMN_PRODUCT_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ShopDatabaseInfo.COLUMN_PRODUCT_BRAND to TEXT,
                ShopDatabaseInfo.COLUMN_PRODUCT_NAME to TEXT,
                ShopDatabaseInfo.COLUMN_PRODUCT_IMAGE_PATH to TEXT,
                ShopDatabaseInfo.COLUMN_PRODUCT_PRICE to REAL,
                specialColumnName to TEXT
            )
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
    }
}
