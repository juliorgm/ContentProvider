package com.example.contatossqlites.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.contatossqlites.model.Contact

class HelperDB(context: Context): SQLiteOpenHelper(context, DB_NAME, null, CURRENT_VERSION) {
    companion object{
        private val DB_NAME = "contato.db"
        private val CURRENT_VERSION = 1
    }

    val TABLE_NAME = "contact"
    val COLUMNS_ID = "id"
    val COLUMNS_NAME = "name"
    val COLUMNS_PHONENUMBER = "phone_number"

    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMNS_ID INTEGER NOT NULL," +
            "$COLUMNS_NAME TEXT NOT NULL," +
            "$COLUMNS_PHONENUMBER TEXT NOT NULL," +
            "PRIMARY KEY($COLUMNS_ID AUTOINCREMENT))"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion){
            db?.execSQL(DROP_TABLE)
        }

        onCreate(db)
    }

    fun getContacts():List<Contact>{
        val db = readableDatabase ?: return mutableListOf()
        var list = mutableListOf<Contact>()
        val sql = "SELECT * FROM $TABLE_NAME"
        var cursor = db.rawQuery(sql,null)
        while (cursor.moveToNext()){
            var contact = Contact(
                    cursor.getInt(cursor.getColumnIndex(COLUMNS_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_PHONENUMBER))
            )

            list.add(contact)
        }

        return list
    }

    fun insert(contact:Contact){
        var db = writableDatabase
        val content = ContentValues()
        content.put(COLUMNS_NAME,contact.name)
        content.put(COLUMNS_PHONENUMBER,contact.phoneNumber)
        db.insert(TABLE_NAME, null, content)
        db.close()
    }
}