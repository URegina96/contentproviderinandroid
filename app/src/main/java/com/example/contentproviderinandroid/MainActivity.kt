package com.example.contentproviderinandroid

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Получение данных с помощью ContentResolver
        val contentResolver: ContentResolver = contentResolver
        val uri: Uri = Uri.parse("content://com.example.bookscontentprovider/books")
        val cursor = contentResolver.query(uri, null, null, null, null)

        // Обработка полученных данных
        cursor?.let {
            while (it.moveToNext()) {
                val titleIndex = it.getColumnIndex("title")
                val authorIndex = it.getColumnIndex("author")

                if (titleIndex >= 0 && authorIndex >= 0) {
                    val bookTitle = it.getString(titleIndex)
                    val bookAuthor = it.getString(authorIndex)
                    Log.d("Book Data", "Title: $bookTitle, Author: $bookAuthor")
                } else {
                    Log.d("Book Data", "Invalid column name")
                }
            }
            it.close()
        }
    }
}
