package br.com.contentprovider.ui.activities

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns._ID
import android.widget.Adapter
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.contentprovider.database.NotesDatabaseHelper.Companion.TITLE_NOTES
import br.com.contentprovider.database.NotesProvider.Companion.URI_NOTES
import br.com.contentprovider.databinding.ActivityMainBinding
import br.com.contentprovider.ui.adapter.NoteClickListener
import br.com.contentprovider.ui.adapter.NotesAdapter
import br.com.contentprovider.ui.fragments.NotesDetailFragment

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)

        binding.floatingActionButton.setOnClickListener{
            NotesDetailFragment().show(supportFragmentManager,"dialog")
        }

        adapter = NotesAdapter(object:NoteClickListener{
            override fun noteClickedItem(cursor: Cursor?) {
                val id:Long = cursor!!.getLong(cursor.getColumnIndex(_ID))
                val fragment = NotesDetailFragment.newInstance(id)
                fragment.show(supportFragmentManager, "dialog")
            }

            override fun noteRemoveItem(cursor: Cursor?) {
                val id: Long? = cursor?.getLong(cursor.getColumnIndex(_ID))
                contentResolver.delete(Uri.withAppendedPath(URI_NOTES, id.toString()), null, null)
            }

        })
        adapter.setHasStableIds(true)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        LoaderManager.getInstance(this).initLoader(0,null, this)
    }

    fun setCursor(id:Int ,cursor: Cursor): Loader<Cursor>{
        return  CursorLoader(this, URI_NOTES, null, null, null, TITLE_NOTES)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> = CursorLoader(this, URI_NOTES, null, null, null, TITLE_NOTES)


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if (data != null) { adapter.setCursor(data)}
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapter.setCursor(null)
    }
}