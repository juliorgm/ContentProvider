package br.com.contentprovider.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentProvider
import android.content.ContentValues
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import br.com.contentprovider.R
import br.com.contentprovider.database.NotesDatabaseHelper.Companion.DESCRIPTION_NOTES
import br.com.contentprovider.database.NotesDatabaseHelper.Companion.TITLE_NOTES
import br.com.contentprovider.database.NotesProvider.Companion.URI_NOTES


class NotesDetailFragment : DialogFragment(), DialogInterface.OnClickListener {
    // TODO: Rename and change types of parameters
    private lateinit var noteEditTitle:EditText
    private lateinit var noteEditDescription:EditText
    private var id:Long = 0

    companion object{
        private const val EXTRA_ID = "id"
        fun newInstance(id: Long): NotesDetailFragment{
            val bundle = Bundle()
            bundle.putLong(EXTRA_ID, id)

            val notesFragment = NotesDetailFragment()
            notesFragment.arguments = bundle
            return notesFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View? = activity?.layoutInflater?.inflate(R.layout.fragment_notes_detail, null)
        noteEditTitle = view!!.findViewById(R.id.note_edt_title)
        noteEditDescription = view!!.findViewById(R.id.note_edt_description)
        var newNote = true
        if (arguments != null && arguments?.getLong(EXTRA_ID) != 0L){
            id = arguments?.getLong(EXTRA_ID) as Long
            val uri: Uri = Uri.withAppendedPath(URI_NOTES, id.toString())
            val cursor = activity?.contentResolver?.query(uri, null, null, null, null)
            if (cursor?.moveToNext() as Boolean){
                newNote = false
                noteEditTitle.setText(cursor.getString(cursor.getColumnIndex(TITLE_NOTES)))
                noteEditDescription.setText(cursor.getString(cursor.getColumnIndex(DESCRIPTION_NOTES)))
            }
            cursor.close()
        }

        return AlertDialog.Builder(activity as Activity)
                .setTitle(if (newNote) "Nova mensagem" else "Editar Mensagem")
                .setView(view)
                .setPositiveButton("Salvar", this)
                .setNegativeButton("Calcelar", this)
                .create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        if(which.equals(-2)) return

        val values = ContentValues()
        values.put(TITLE_NOTES, noteEditTitle.text.toString())
        values.put(DESCRIPTION_NOTES, noteEditDescription.text.toString())

        if (id != 0L){
            val uri:Uri = Uri.withAppendedPath(URI_NOTES, id.toString())
            context?.contentResolver?.update(uri, values, null, null)
        }else{
            context?.contentResolver?.insert(URI_NOTES, values)
        }
    }
}