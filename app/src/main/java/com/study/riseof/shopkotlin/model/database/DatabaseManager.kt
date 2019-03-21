package com.study.riseof.shopkotlin.model.database

import android.content.Context
import android.database.Cursor
import android.util.Log
import com.study.riseof.shopkotlin.activity.MainActivityContract
import com.study.riseof.shopkotlin.fragment.catalogFragment.CatalogFragmentContract
import com.study.riseof.shopkotlin.fragment.navigationViewFragment.NavigationViewFragmentContract
import com.study.riseof.shopkotlin.model.database.tableFiller.*
import com.study.riseof.shopkotlin.model.data.Product
import org.jetbrains.anko.db.*

class DatabaseManager :
    CatalogFragmentContract.Model,
    NavigationViewFragmentContract.Model,
    MainActivityContract.Model {


    override fun getProductListFromTable(context: Context, tableName: String): ArrayList<Product> {
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
        } catch (e: Exception) {
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

    override fun fillAllTables(context: Context) {
        val tableCamerasFiller = TableCamerasFiller()
        tableCamerasFiller.fillTable(context)
        val tableFlashDrivesFiller = TableFlashDrivesFiller()
        tableFlashDrivesFiller.fillTable(context)
        val tableGraphicTabletsFiller = TableGraphicTabletsFiller()
        tableGraphicTabletsFiller.fillTable(context)
        val tableHeadphonesFiller = TableHeadphonesFiller()
        tableHeadphonesFiller.fillTable(context)
        val tableLaptopsFiller = TableLaptopsFiller()
        tableLaptopsFiller.fillTable(context)
        val tableMicrophonesFiller = TableMicrophonesFiller()
        tableMicrophonesFiller.fillTable(context)
        val tableSmartphonesFiller = TableSmartphonesFiller()
        tableSmartphonesFiller.fillTable(context)
        val tableSpeakersFiller = TableSpeakersFiller()
        tableSpeakersFiller.fillTable(context)
    }
}