package com.study.riseof.shopkotlin.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, DatabaseInfo.DATABASE_NAME, null, DB_VERSION) {

    companion object {
        const val DB_VERSION = 1
        private var instance: DatabaseHelper? = null
        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createAllTables(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    private fun createAllTables(db: SQLiteDatabase?) {
        createTable(db, DatabaseInfo.TABLE_SMARTPHONES, DatabaseInfo.COLUMN_SMARTPHONE_DIAGONAL)
        createTable(db, DatabaseInfo.TABLE_GRAPHIC_TABLETS, DatabaseInfo.COLUMN_GRAPHIC_TABLET_FORMAT)
        createTable(db, DatabaseInfo.TABLE_LAPTOPS, DatabaseInfo.COLUMN_LAPTOP_COLOR)
        createTable(db, DatabaseInfo.TABLE_CAMERAS, DatabaseInfo.COLUMN_CAMERA_RESOLUTION)
        createTable(db, DatabaseInfo.TABLE_SPEAKERS, DatabaseInfo.COLUMN_SPEAKERS_POWER)
        createTable(db, DatabaseInfo.TABLE_HEADPHONES, DatabaseInfo.COLUMN_HEADPHONES_TYPE)
        createTable(db, DatabaseInfo.TABLE_MICROPHONES, DatabaseInfo.COLUMN_MICROPHONE_SENSITIVITY)
        createTable(db, DatabaseInfo.TABLE_FLASH_DRIVES, DatabaseInfo.COLUMN_FLASH_MEMORY_CAPACITY)
    }

    private fun createTable(
        db: SQLiteDatabase?,
        tableName: String,
        specialColumnName: String
    ) {
        try {
            db?.createTable(
                tableName, true,
                DatabaseInfo.COLUMN_PRODUCT_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DatabaseInfo.COLUMN_PRODUCT_BRAND to TEXT,
                DatabaseInfo.COLUMN_PRODUCT_NAME to TEXT,
                DatabaseInfo.COLUMN_PRODUCT_IMAGE_PATH to TEXT,
                DatabaseInfo.COLUMN_PRODUCT_PRICE to REAL,
                specialColumnName to TEXT
            )
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
    }
}
