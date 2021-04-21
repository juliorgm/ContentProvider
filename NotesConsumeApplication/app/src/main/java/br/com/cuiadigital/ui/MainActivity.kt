package br.com.cuiadigital.ui

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cuiadigital.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getContentProvider()
        initClicks()
    }

    private fun initClicks() {
        binding.floatingActionButton.setOnClickListener{
            Log.d("MainActivity","I were passed here")
            getContentProvider()
        }
    }

    private fun getContentProvider(){
        try {
            val url= "content://br.com.contentprovider.provider/notes"
            val data = Uri.parse(url)
            val cursor: Cursor? = contentResolver.query(data, null, null, null, "title")
            binding.recyclerviewNotes.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = NotesAdapter(cursor as Cursor)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}