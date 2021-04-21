package com.example.contatossqlites

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.contatossqlites.CONFIGs.Companion.EXTRA_SAVE_NEW_CONTACT
import com.example.contatossqlites.CONFIGs.Companion.EXTRA_UPDATE_CONTATCT
import com.example.contatossqlites.CONFIGs.Companion.getNewId
import com.example.contatossqlites.application.ContactApplication
import com.example.contatossqlites.model.Contact
import kotlinx.android.synthetic.main.activity_form_contact.*

private var isFormUpdate = false
private var contact: Contact? = null

class FormContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_contact)

        val intent = getIntent()
        if (intent.hasExtra(EXTRA_UPDATE_CONTATCT)){
            getContactFromIntent()
            loadDataContactOnForm()
            setFormConfigurations()
        }

        setButtons()
    }

    private fun setFormConfigurations() {
        btnDeleteContact.visibility = View.VISIBLE
        isFormUpdate = true
    }

    private fun getContactFromIntent() {
        contact =  intent.getParcelableExtra<Contact>(EXTRA_UPDATE_CONTATCT)
    }

    private fun loadDataContactOnForm() {
        edtContactName.setText(contact?.name)
        edtPhoneNumber.setText(contact?.phoneNumber)
    }

    private fun setButtons() {
        btnSaveContact.setOnClickListener {
            Log.d("btnSaveContact","enter on click button")
            if (isFormUpdate){
                //Do update contact
                Log.d("btnSaveContact","is form update")
                contact?.name = edtContactName.text.toString()
                contact?.phoneNumber = edtPhoneNumber.text.toString()
                goResultToMainActivity(EXTRA_UPDATE_CONTATCT)
            }else{
                //Do save contact
                Log.d("btnSaveContact","save form button")
                val name = edtContactName.text.toString()
                val phoneNumber = edtPhoneNumber.text.toString()
                contact = Contact(getNewId(),name,phoneNumber)
                ContactApplication.instance.helperDB?.insert(contact!!)
                goResultToMainActivity(EXTRA_SAVE_NEW_CONTACT)
            }
        }

        btnDeleteContact.setOnClickListener {
            //Do delete contact from list
        }
    }

    private fun goResultToMainActivity(EXTRA_CONTACT:String) {
        Log.d("btnSaveContact","I'm going to the main activity")
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    override fun onDestroy() {
        super.onDestroy()
        isFormUpdate = false
    }
}


