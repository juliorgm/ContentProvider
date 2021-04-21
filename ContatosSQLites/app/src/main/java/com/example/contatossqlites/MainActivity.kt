package com.example.contatossqlites

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.example.contatossqlites.CONFIGs.Companion.EXTRA_SAVE_NEW_CONTACT
import com.example.contatossqlites.CONFIGs.Companion.EXTRA_UPDATE_CONTATCT
import com.example.contatossqlites.CONFIGs.Companion.getNewId
import com.example.contatossqlites.adapters.ContactsAdapter
import com.example.contatossqlites.application.ContactApplication
import com.example.contatossqlites.model.Contact

class MainActivity() : AppCompatActivity() , ContactsAdapter.OnItemClickListener{
    private var list = mutableListOf<Contact>()
    private var searchedlist = mutableListOf<Contact>()
    private lateinit var adapter: ContactsAdapter
    private val REQUEST_CODE_SAVE = 1
    private val REQUEST_CODE_UPDATE = 2
    private val NUMBER_LETTERS_TO_SEARCH = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        setRecyclerview()
        setListeners()
    }

    private fun getData() {
        list = (ContactApplication.instance.helperDB?.getContacts() ?: mutableListOf()) as MutableList<Contact>
        setSeachList()
    }

    private fun setRecyclerview() {
        recycler_contacts.adapter = adapter
        recycler_contacts.layoutManager = LinearLayoutManager(this)
        recycler_contacts.setHasFixedSize(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data==null) return

        if (requestCode==REQUEST_CODE_SAVE && resultCode == Activity.RESULT_OK){
            val newContact = data.getParcelableExtra<Contact>(EXTRA_SAVE_NEW_CONTACT)
            if (newContact != null) {
                list.add(newContact)
            }
        }

        if (requestCode==REQUEST_CODE_UPDATE && resultCode == Activity.RESULT_OK){
            val updatedContact = data.getParcelableExtra<Contact>(EXTRA_UPDATE_CONTATCT)
            if (updatedContact != null) {
                list.forEach {
                    if(it.id==updatedContact.id){
                        it.name = updatedContact.name
                        it.phoneNumber = updatedContact.phoneNumber
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        recycler_contacts.adapter?.notifyDataSetChanged()
    }

    private fun setListeners() {
        setButtonListerner()
        setItemRecyclerListener()
    }

    private fun setSeachList() {
        searchedlist = list
        adapter = ContactsAdapter(searchedlist, listener = this)
        Log.d("porra","passei na porra")
        edt_search.doAfterTextChanged { text ->

            val textTyped = text.toString().toUpperCase()
            Log.d("porra","passei na porra 2 $textTyped")
            val sizeTextTyped = textTyped.length
            if (sizeTextTyped>= NUMBER_LETTERS_TO_SEARCH){
                searchedlist = list.filter{
                    val subTextContact = it.name.substring(0..sizeTextTyped-1).toUpperCase()
                    subTextContact==textTyped
                }.toMutableList()
                adapter = ContactsAdapter(searchedlist, listener = this)
            }else{
                adapter = ContactsAdapter(list , listener = this)
            }
            recycler_contacts.adapter = adapter
            recycler_contacts.adapter?.notifyDataSetChanged()
        }
    }

    private fun setItemRecyclerListener() {
        Log.d("click","asdfasdf")
    }

    private fun setButtonListerner() {
        fab_add_contact.setOnClickListener(){
            gotToSaveForm()
        }
    }

    private fun gotToSaveForm() {
        val intent = Intent(this, FormContactActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_SAVE)
    }

    private fun gotToUpdateForm(contact:Contact) {
        val intent = Intent(this, FormContactActivity::class.java)
        intent.putExtra(EXTRA_UPDATE_CONTATCT, contact)
        startActivityForResult(intent, REQUEST_CODE_UPDATE)
    }

    override fun onItemClick(position: Int) {
        val currentContact = list.get(position)
        gotToUpdateForm(currentContact)
    }
}