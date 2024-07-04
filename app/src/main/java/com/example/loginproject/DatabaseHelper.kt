package com.example.loginproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.Data

class DatabaseHelper(context: Context):SQLiteOpenHelper(
    context,
    DatabaseConstants.DATABASE_NAME,
    null,
    DatabaseConstants.DATABASE_VERSION
) {
    override fun onCreate(database: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE ${DatabaseConstants.TABLE_NAME}(
            ${DatabaseConstants.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${DatabaseConstants.COLUMN_PRICE} TEXT,
            ${DatabaseConstants.COLUMN_QUANTITY} INTEGER,
            ${DatabaseConstants.COLUMN_TOTAL_PRICE} INTEGER
            )
        """.trimIndent()
        database!!.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(product:DatabaseProduct):Long{
        val values  = ContentValues().apply {
            put(DatabaseConstants.COLUMN_PRICE, product.productPrice)
            put(DatabaseConstants.COLUMN_QUANTITY, product.productQuantity)
            //put(DatabaseConstants.COLUMN_TOTAL_PRICE,product.totalPrice)

        }
        return writableDatabase.insert(DatabaseConstants.TABLE_NAME, null, values)

    }

    fun updateData( product: DatabaseProduct):Int{
        val values = ContentValues().apply {
            put(DatabaseConstants.COLUMN_PRICE,product.productPrice)
            put(DatabaseConstants.COLUMN_QUANTITY, product.productQuantity)
            //put(DatabaseConstants.COLUMN_TOTAL_PRICE,product.totalPrice)
        }

        val selection= "${DatabaseConstants.COLUMN_ID} = ?"
        val selectionArg=arrayOf(product.productId.toString())
        return writableDatabase.update(DatabaseConstants.TABLE_NAME, values,selection,selectionArg)
    }

    fun readData():List<DatabaseProduct>{
        val productList = mutableListOf<DatabaseProduct>()
        val cursor:Cursor = readableDatabase.query(DatabaseConstants.TABLE_NAME,null,null,null,null,null,null)
        with(cursor){
            while (moveToNext()){
                val id = getLong(getColumnIndexOrThrow(DatabaseConstants.COLUMN_ID)).toInt()
                val price = getInt(getColumnIndexOrThrow(DatabaseConstants.COLUMN_PRICE)).toInt()
                val quantity = getInt(getColumnIndexOrThrow(DatabaseConstants.COLUMN_QUANTITY)).toInt()
                val totalPrice = getInt(getColumnIndexOrThrow(DatabaseConstants.COLUMN_TOTAL_PRICE))

                productList.add(DatabaseProduct(productId = id, productPrice = price, productQuantity = quantity ))
            }
        }
        cursor.close()
        return productList
    }

    fun isDatabaseEmpty(): Boolean {
        val cursor: Cursor = readableDatabase.rawQuery("SELECT COUNT(*) FROM ${DatabaseConstants.TABLE_NAME}", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count == 0
    }


}