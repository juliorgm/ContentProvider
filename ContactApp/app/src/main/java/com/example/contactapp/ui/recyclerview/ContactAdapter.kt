package com.example.contactapp.ui.recyclerview


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.R
import com.example.contactapp.model.Contact
import com.example.contactapp.ui.activities.DetailActivity
import com.example.contactapp.ui.recyclerview.listeners.ClickItemContactListener

class ContactAdapter(var listener: ClickItemContactListener) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    val list = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("ContactAdapter","ContactAdapter")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view,list, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("ContactAdapter","ContactAdapter")
        val contact = list.get(position)
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        Log.d("ContactAdapter","ContactAdapter")
        return list.size
    }

    fun updateList(list: List<Contact>){
        Log.d("ContactAdapter","updateList")
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, list:MutableList<Contact>, listener: ClickItemContactListener) : RecyclerView.ViewHolder(itemView) {

        private val itemName = itemView.findViewById<TextView>(R.id.txtContactItemName)
        private val itemPhone= itemView.findViewById<TextView>(R.id.txtContactItemPhone)
        private val itemPhoto = itemView.findViewById<ImageView>(R.id.imageViewContact)

        init {
            itemView.setOnClickListener{
                listener.clickItemContact(list.get(adapterPosition))
            }
        }

        fun bind(contact:Contact){
            Log.d("ContactAdapter","updateList")
            itemName.text = contact.name
            itemPhone.text = contact.phone
        }
    }


}