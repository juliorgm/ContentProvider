package br.com.exemplenotifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.exemplenotifications.databinding.ActivityMainBinding
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            this.ShowNotification("1234","Bootcampo Everys Dio", "Falta pouco para acabar o curso.. Keep swiming...")
        }

        Log.i("newTokein", FirebaseInstanceId.getInstance().token.toString())
    }
}