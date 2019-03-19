package com.study.riseof.shopkotlin.database

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteCursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.study.riseof.shopkotlin.model.Product
import com.study.riseof.shopkotlin.model.Smartphone
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
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createAllTables(db)
        val databaseManager = DatabaseManager()
        databaseManager.fillAllTables()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    private fun createAllTables(db: SQLiteDatabase?) {
        val sqlTypeText: SqlType = TEXT
        val sqlTypeReal: SqlType = REAL
        val sqlTypeInteger: SqlType = INTEGER
        createTable(db, DatabaseInfo.TABLE_SMARTPHONES, DatabaseInfo.COLUMN_SMARTPHONE_DIAGONAL, sqlTypeReal)
        createTable(db, DatabaseInfo.TABLE_GRAPHIC_TABLETS, DatabaseInfo.COLUMN_GRAPHIC_TABLET_FORMAT, sqlTypeText)
        createTable(db, DatabaseInfo.TABLE_LAPTOPS, DatabaseInfo.COLUMN_LAPTOP_COLOR, sqlTypeText)
        createTable(db, DatabaseInfo.TABLE_CAMERAS, DatabaseInfo.COLUMN_CAMERA_RESOLUTION, sqlTypeInteger)
        createTable(db, DatabaseInfo.TABLE_SPEAKERS, DatabaseInfo.COLUMN_SPEAKERS_POWER, sqlTypeInteger)
        createTable(db, DatabaseInfo.TABLE_HEADPHONES, DatabaseInfo.COLUMN_HEADPHONES_TYPE, sqlTypeText)
    }

    private fun createTable(
        db: SQLiteDatabase?,
        tableName: String,
        specialColumnName: String,
        specialColumnStorageClass: SqlType
    ) {
        db?.createTable(
            tableName, true,
            DatabaseInfo.COLUMN_PRODUCT_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DatabaseInfo.COLUMN_PRODUCT_BRAND to TEXT,
            DatabaseInfo.COLUMN_PRODUCT_NAME to INTEGER,
            DatabaseInfo.COLUMN_PRODUCT_IMAGE_PATH to TEXT,
            DatabaseInfo.COLUMN_PRODUCT_PRICE to INTEGER,
            specialColumnName to specialColumnStorageClass
        )
    }
}
