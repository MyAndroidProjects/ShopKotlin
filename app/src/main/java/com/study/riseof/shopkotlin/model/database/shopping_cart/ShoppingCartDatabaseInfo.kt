package com.study.riseof.shopkotlin.model.database.shopping_cart

object ShoppingCartDatabaseInfo {
    const val DATABASE_NAME = "shoppingCartDatabase"

    const val TABLE_NAME = "products"

    const val COLUMN_PRODUCT_ID = "_id"
    const val COLUMN_PRODUCT_BRAND = "brand"
    const val COLUMN_PRODUCT_NAME = "name"
    const val COLUMN_PRODUCT_PRICE = "price"
    const val COLUMN_PRODUCT_FEATURE = "feature"
    const val COLUMN_PRODUCT_TYPE = "type"

    const val COLUMN_INDEX_ID = 0
    const val COLUMN_INDEX_TYPE = 1
    const val COLUMN_INDEX_BRAND = 2
    const val COLUMN_INDEX_NAME = 3
    const val COLUMN_INDEX_FEATURE = 4
    const val COLUMN_INDEX_PRICE = 5
}