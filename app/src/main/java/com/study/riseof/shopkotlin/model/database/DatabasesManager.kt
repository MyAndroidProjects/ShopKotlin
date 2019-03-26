package com.study.riseof.shopkotlin.model.database

import android.content.Context
import android.database.Cursor
import android.util.Log
import com.study.riseof.shopkotlin.activity.main.MainActivityContract
import com.study.riseof.shopkotlin.activity.shopping_cart.ShoppingCartActivityContract
import com.study.riseof.shopkotlin.fragment.catalog.CatalogFragmentContract
import com.study.riseof.shopkotlin.fragment.navigation_view.NavigationViewFragmentContract
import com.study.riseof.shopkotlin.model.database.simple_shop.fillers.*
import com.study.riseof.shopkotlin.model.data.Product
import com.study.riseof.shopkotlin.model.data.ShoppingCartProduct
import com.study.riseof.shopkotlin.model.database.shopping_cart.ShoppingCartDatabaseHelper
import com.study.riseof.shopkotlin.model.database.shopping_cart.ShoppingCartDatabaseInfo
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseHelper
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo
import com.study.riseof.shopkotlin.model.database.simple_shop.ShopDatabaseInfo.COLUMN_PRODUCT_ID
import org.jetbrains.anko.db.*

class DatabasesManager :
    CatalogFragmentContract.Model,
    NavigationViewFragmentContract.Model,
    MainActivityContract.Model,
    ShoppingCartActivityContract.Model {

    // ------------ Shop Database
    override fun getProductListFromShopDatabase(context: Context, tableName: String): ArrayList<Product> {
        val list = ArrayList<Product>()
        try {
            val databaseHelper =
                ShopDatabaseHelper.getInstance(context)
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

    //todo посмотреть необходимость этого метода, если да, то переименовать
    fun getProductFromTable(context: Context, id: Int, tableName: String): Product {
        lateinit var product: Product
        try {
            val databaseHelper =
                ShopDatabaseHelper.getInstance(context)
            val database = databaseHelper.readableDatabase
            database.select(tableName, "*")
                .whereArgs(
                    "($COLUMN_PRODUCT_ID = $id)"
                )
                .exec {
                    this.moveToFirst()
                    product = getProductFromCursor(this, tableName)
                }
        } catch (e: Exception) {
            Log.d("myLog", "getProductFromTable Exception: " + e.toString())
        }
        Log.d("myLog", "product: " + product.toString())
        Log.d("myLog", "product id: " + product.id)
        Log.d("myLog", "product name: " + product.name)
        Log.d("myLog", "product price: " + product.price.toString())
        return product
    }


    override fun fillShopDatabaseTables(context: Context) {
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

    // ------------ Shopping Cart Database

    override fun deleteShoppingCartDatabase(context: Context) {
        Log.d("myLog", "DELETE  deleteShoppingCartDatabase ")
        context.deleteDatabase(ShoppingCartDatabaseInfo.DATABASE_NAME)
    }

    override fun putProductToShoppingCartDatabase(context: Context, product: Product, type: String): Boolean {
        try {
            val dataBaseHelper = ShoppingCartDatabaseHelper.getInstance(context)
            Log.d(
                "myLog", "putProductToShoppingCart: " + "\n" +
                        ShoppingCartDatabaseInfo.COLUMN_PRODUCT_BRAND + "\n" + product.brand + "\n" +
                        ShoppingCartDatabaseInfo.COLUMN_PRODUCT_NAME + "\n" + product.name + "\n" +
                        ShoppingCartDatabaseInfo.COLUMN_PRODUCT_PRICE + "\n" + product.price + "\n" +
                        ShoppingCartDatabaseInfo.COLUMN_PRODUCT_FEATURE + "\n" + product.feature + "\n" +
                        ShoppingCartDatabaseInfo.COLUMN_PRODUCT_TYPE + "\n" + type
            )
            dataBaseHelper.use {
                insert(
                    ShoppingCartDatabaseInfo.TABLE_NAME,
                    ShoppingCartDatabaseInfo.COLUMN_PRODUCT_BRAND to product.brand,
                    ShoppingCartDatabaseInfo.COLUMN_PRODUCT_NAME to product.name,
                    ShoppingCartDatabaseInfo.COLUMN_PRODUCT_PRICE to product.price,
                    ShoppingCartDatabaseInfo.COLUMN_PRODUCT_FEATURE to product.feature,
                    ShoppingCartDatabaseInfo.COLUMN_PRODUCT_TYPE to type
/*
                            const val COLUMN_INDEX_ID = 0
                            const val COLUMN_INDEX_BRAND = 1
                            const val COLUMN_INDEX_NAME = 2
                            const val COLUMN_INDEX_PRICE = 3
                            const val COLUMN_INDEX_FEATURE = 4
                            const val COLUMN_INDEX_TYPE = 5*/

                )
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
            return false
        }
        return true
    }


    private fun getProductFromCursor(cursor: Cursor, tableName: String): Product {
        return Product(
            cursor.getInt(ShopDatabaseInfo.COLUMN_INDEX_ID),
            cursor.getString(ShopDatabaseInfo.COLUMN_INDEX_IMAGE_PATH),
            cursor.getString(ShopDatabaseInfo.COLUMN_INDEX_BRAND),
            cursor.getString(ShopDatabaseInfo.COLUMN_INDEX_NAME),
            cursor.getString(ShopDatabaseInfo.COLUMN_INDEX_FEATURE),
            cursor.getFloat(ShopDatabaseInfo.COLUMN_INDEX_PRICE),
            tableName
        )
    }

    override fun deleteProductFromShoppingCartDatabaseById(context: Context, id: Int) {
        TODO("not implemented")
    }

    override fun getProductListFromShoppingCartDatabase(context: Context): ArrayList<ShoppingCartProduct> {
        val list = ArrayList<ShoppingCartProduct>()
        try {
            val databaseHelper =
                ShoppingCartDatabaseHelper.getInstance(context)
            val database = databaseHelper.readableDatabase
            database.select(ShoppingCartDatabaseInfo.TABLE_NAME, "*").exec {
                if (this.moveToFirst()) {
                    do {
                        val product: ShoppingCartProduct = getShoppingCartProductFromCursor(this)
                        list.add(product)
                    } while (this.moveToNext())
                } else {
                    Log.d("myLog", "!this.moveToFirst() ")
                }
            }
        } catch (e: Exception) {
            Log.d("myLog", "Exception: " + e.toString())
        }
        return list
    }

    private fun getShoppingCartProductFromCursor(cursor: Cursor): ShoppingCartProduct {
        Log.d(
            "myLog", "getShopProduct " + cursor.getInt(ShoppingCartDatabaseInfo.COLUMN_INDEX_ID) + "\n" +
                    cursor.getString(ShoppingCartDatabaseInfo.COLUMN_INDEX_TYPE) + "\n" +
                    cursor.getString(ShoppingCartDatabaseInfo.COLUMN_INDEX_BRAND) + "\n" +
                    cursor.getString(ShoppingCartDatabaseInfo.COLUMN_INDEX_NAME) + "\n" +
                    cursor.getString(ShoppingCartDatabaseInfo.COLUMN_INDEX_FEATURE) + "\n" +
                    cursor.getFloat(ShoppingCartDatabaseInfo.COLUMN_INDEX_PRICE)
        )
        return ShoppingCartProduct(
            cursor.getInt(ShoppingCartDatabaseInfo.COLUMN_INDEX_ID),
            cursor.getString(ShoppingCartDatabaseInfo.COLUMN_INDEX_TYPE),
            cursor.getString(ShoppingCartDatabaseInfo.COLUMN_INDEX_BRAND),
            cursor.getString(ShoppingCartDatabaseInfo.COLUMN_INDEX_NAME),
            cursor.getString(ShoppingCartDatabaseInfo.COLUMN_INDEX_FEATURE),
            cursor.getFloat(ShoppingCartDatabaseInfo.COLUMN_INDEX_PRICE)

        )
    }
}