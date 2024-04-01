package com.example.contentproviderinandroid
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class YourDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "BooksDatabase"
        private const val TABLE_NAME = "books"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_AUTHOR = "author"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_AUTHOR TEXT)"
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}