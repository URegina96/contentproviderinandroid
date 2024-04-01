package com.example.contentproviderinandroid

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class BooksContentProvider : ContentProvider() {

    private lateinit var dbHelper: SQLiteOpenHelper

    override fun onCreate(): Boolean {
        dbHelper = context?.let { YourDatabaseHelper(it) }!!
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val qb = SQLiteQueryBuilder()
        qb.tables = "books"
        val cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val rowId = db.insert("books", null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return Uri.withAppendedPath(uri, rowId.toString())
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val count = db.update("books", values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val count = db.delete("books", selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.com.example.bookscontentprovider.books"
    }
}