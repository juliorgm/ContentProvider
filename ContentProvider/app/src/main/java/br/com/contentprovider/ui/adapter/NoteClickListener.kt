package br.com.contentprovider.ui.adapter

import android.database.Cursor

interface NoteClickListener {
    fun noteClickedItem(cursor: Cursor?)
    fun noteRemoveItem(cursor: Cursor?)
}