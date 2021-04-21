package com.example.contactapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

import com.example.contactapp.R
import com.example.contactapp.model.Contact

class DetailActivity : AppCompatActivity() {
    private var contact:Contact? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        initToolbar()
        getExtras()
        bindViews()

    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun getExtras(){
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }

    fun bindViews(){
        if (contact==null) return

        findViewById<TextView>(R.id.TextViewName).text = contact?.name
        findViewById<TextView>(R.id.TextViewPhone).text = contact?.phone
    }

    companion object{
        val EXTRA_CONTACT = "EXTRA_CONTACT"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}