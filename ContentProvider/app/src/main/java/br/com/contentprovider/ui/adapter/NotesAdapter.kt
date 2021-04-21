package br.com.contentprovider.ui.adapter

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.RecyclerView
import br.com.contentprovider.R
import br.com.contentprovider.database.NotesDatabaseHelper
import br.com.contentprovider.database.NotesDatabaseHelper.Companion.DESCRIPTION_NOTES
import br.com.contentprovider.database.NotesDatabaseHelper.Companion.TITLE_NOTES
import br.com.contentprovider.database.NotesProvider.Companion.URI_NOTES

class NotesAdapter(private val listner:NoteClickListener): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var mCursor: Cursor? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        mCursor?.moveToPosition(position)

        holder.noteTitle.text = mCursor?.getString(mCursor?.getColumnIndex(TITLE_NOTES) as Int)
        holder.noteDescription.text = mCursor?.getString(mCursor?.getColumnIndex(DESCRIPTION_NOTES) as Int)
        holder.noteButtonRemove.setOnClickListener {
            mCursor?.moveToPosition(position)
            listner.noteRemoveItem(mCursor)
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener{
            listner.noteClickedItem(mCursor)
        }
    }

    override fun getItemCount(): Int {
        if (mCursor != null) return mCursor?.count as Int
        else return 0
    }

    fun setCursor(cursor: Cursor?){
        mCursor = cursor
        notifyDataSetChanged()
    }

    class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noteTitle = itemView.findViewById<TextView>(R.id.note_title)
        val noteDescription = itemView.findViewById<TextView>(R.id.note_description)
        val noteButtonRemove= itemView.findViewById<Button>(R.id.note_button_remove)
    }
}


