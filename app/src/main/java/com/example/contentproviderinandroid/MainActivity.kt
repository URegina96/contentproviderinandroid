package com.example.contentproviderinandroid

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contentResolver: ContentResolver = contentResolver
        val uri: Uri = Uri.parse("content://com.example.bookscontentprovider/books")
        val cursor = contentResolver.query(uri, null, null, null, null)

        cursor?.let {
            if (it.moveToFirst()) {
                val titleIndex = it.getColumnIndex("title")
                val authorIndex = it.getColumnIndex("author")

                val bookTitle = it.getString(titleIndex)
                val bookAuthor = it.getString(authorIndex)

                val bookTitleTextView = findViewById<TextView>(R.id.bookTitleTextView)
                val bookAuthorTextView = findViewById<TextView>(R.id.bookAuthorTextView)

                bookTitleTextView.text = bookTitle
                bookAuthorTextView.text = bookAuthor
            }
            it.close()
        }
    }
}