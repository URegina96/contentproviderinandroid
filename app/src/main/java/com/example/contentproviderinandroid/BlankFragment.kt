package com.example.contentproviderinandroid

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BlankFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        val bookTitleTextView = view.findViewById<TextView>(R.id.bookTitleTextView)
        val bookAuthorTextView = view.findViewById<TextView>(R.id.bookAuthorTextView)

        val contentResolver: ContentResolver = requireContext().contentResolver
        val uri: Uri = Uri.parse("content://com.example.bookscontentprovider/books")
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        cursor?.use { cursor ->
            if (cursor.moveToFirst()) {
                val titleIndex = cursor.getColumnIndex("title")
                val authorIndex = cursor.getColumnIndex("author")

                val bookTitle = cursor.getString(titleIndex)
                val bookAuthor = cursor.getString(authorIndex)

                bookTitleTextView.text = bookTitle
                bookAuthorTextView.text = bookAuthor
            }
        }

        return view
    }
}