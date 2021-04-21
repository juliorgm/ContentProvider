package br.com.cuiadigital.ui

import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cuiadigital.R

class NotesAdapter(private val mCursor: Cursor) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        mCursor.moveToPosition(position)
        holder.bindData(mCursor)
    }

    override fun getItemCount(): Int = mCursor.count

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById<TextView>(R.id.text_title)
        val txtDescription = itemView.findViewById<TextView>(R.id.text_description)

        fun bindData(cursor: Cursor){
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val description =  cursor.getString(cursor.getColumnIndex("description"))

            txtTitle.text = title
            txtDescription.text = description
        }
    }
}