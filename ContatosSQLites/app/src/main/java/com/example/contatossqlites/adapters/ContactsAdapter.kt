package com.example.contatossqlites.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contatossqlites.R
import com.example.contatossqlites.model.Contact

class ContactsAdapter(
        private val listContacts: MutableList<Contact>,
        private val listener: OnItemClickListener
        )
    : RecyclerView.Adapter<ContactsAdapter.ViewHolder>(){

    private var clickListener: OnItemClickListener? = null

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val txtName: TextView
        val txtPhoneNumber: TextView
        init {
            txtName = view.findViewById(R.id.txtContactItemName)
            txtPhoneNumber = view.findViewById(R.id.txtContactItemPhoneNumber)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!= RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = listContacts.get(position)
        holder.txtName.text = contact.name
        holder.txtPhoneNumber.text = contact.phoneNumber
    }

    override fun getItemCount() = listContacts.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}


