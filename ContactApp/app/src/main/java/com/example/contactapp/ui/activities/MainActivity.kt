package com.example.contactapp.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.R
import com.example.contactapp.model.Contact
import com.example.contactapp.ui.activities.DetailActivity.Companion.EXTRA_CONTACT
import com.example.contactapp.ui.recyclerview.ContactAdapter
import com.example.contactapp.ui.recyclerview.listeners.ClickItemContactListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(),ClickItemContactListener {

    private val adapter = ContactAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        bindViews()
        updateList()
        initDrawer()
    }

    private fun initDrawer() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar =  findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toogle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.open_drawer,R.string.close_drawer)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
    }

    private fun updateList() {
        adapter.updateList(getListContact())
    }

    fun getListContact():List<Contact>{
        val list = getInstanceSharedPreferences().getString("contacts","[]")
        val turnsType = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list,turnsType)
    }

    fun featchListContact(){
        val list=  arrayListOf(
            Contact(name="João", phone = "(91) 98098-6651", photograph = ""),
            Contact(name="Maria", phone = "(11) 91321-6651", photograph = ""),
            Contact(name="Fernando", phone = "(91) 99874-6651", photograph = ""),
        )
        getInstanceSharedPreferences().edit().putString("contacts",Gson().toJson(list))
    }

    fun getInstanceSharedPreferences(): SharedPreferences {
        return getSharedPreferences("com.exemplo.PREFERENCE",Context.MODE_PRIVATE)
    }

    private fun bindViews() {
        val rvContacts = findViewById<RecyclerView>(R.id.rvContacts)
        rvContacts.layoutManager = LinearLayoutManager(this)
        rvContacts.adapter = adapter

        updateList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item_1 ->{Toast.makeText(this,"Menu 1",Toast.LENGTH_SHORT).show()}
            R.id.menu_item_2 ->{Toast.makeText(this,"Menu 2",Toast.LENGTH_SHORT).show()}
            R.id.menu_item_3 ->{Toast.makeText(this,"Menu 3",Toast.LENGTH_SHORT).show()}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT,contact)
        startActivity(intent)
    }
}