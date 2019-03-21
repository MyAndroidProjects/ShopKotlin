package com.study.riseof.shopkotlin.model.database

object DatabaseInfo {
    const val DATABASE_NAME = "simpleShopDatabase"

    const val TABLE_SMARTPHONES = "smartphones"
    const val TABLE_GRAPHIC_TABLETS = "graphicTablets"
    const val TABLE_LAPTOPS = "laptops"
    const val TABLE_CAMERAS = "cameras"
    const val TABLE_SPEAKERS = "speakers"
    const val TABLE_HEADPHONES = "headphones"
    const val TABLE_MICROPHONES = "microphones"
    const val TABLE_FLASH_DRIVES = "flashDrives"

    const val COLUMN_PRODUCT_ID = "id"
    const val COLUMN_PRODUCT_BRAND = "brand"
    const val COLUMN_PRODUCT_NAME = "name"
    const val COLUMN_PRODUCT_IMAGE_PATH = "path"
    const val COLUMN_PRODUCT_PRICE = "price"

    const val COLUMN_SMARTPHONE_DIAGONAL = "diagonal"
    const val COLUMN_GRAPHIC_TABLET_FORMAT = "format"
    const val COLUMN_LAPTOP_COLOR = "color"
    const val COLUMN_CAMERA_RESOLUTION = "resolution"
    const val COLUMN_SPEAKERS_POWER = "power"
    const val COLUMN_HEADPHONES_TYPE = "type"
    const val COLUMN_MICROPHONE_SENSITIVITY = "sensitivity"
    const val COLUMN_FLASH_MEMORY_CAPACITY = "memory"

    const val COLUMN_INDEX_ID = 0
    const val COLUMN_INDEX_BRAND = 1
    const val COLUMN_INDEX_NAME = 2
    const val COLUMN_INDEX_IMAGE_PATH = 3
    const val COLUMN_INDEX_PRICE = 4
    const val COLUMN_INDEX_SPECIAL_CHARACTERISTIC = 5
}