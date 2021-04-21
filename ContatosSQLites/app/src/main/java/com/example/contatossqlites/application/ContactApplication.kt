package com.example.contatossqlites.application

import android.app.Application
import com.example.contatossqlites.helpers.HelperDB

class ContactApplication:Application() {


    var helperDB: HelperDB? = null
        private set

    companion object{
        lateinit var instance:ContactApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helperDB = HelperDB(this)
    }
}