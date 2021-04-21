package com.example.mytestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

var contadorCliqueBlue:Int = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val verboseButton = findViewById<Button>(R.id.verboseButton)
        val errorButton = findViewById<Button>(R.id.errorButton)
        val infoButton = findViewById<Button>(R.id.infoButton)

        verboseButton.setOnClickListener(View.OnClickListener {Log.v("teste","Cliquei no verbose") })
        errorButton.setOnClickListener(View.OnClickListener {Log.e("teste","Cliquei no error") })
        infoButton.setOnClickListener(View.OnClickListener {Log.i("teste","Cliquei no info") })

        val currentThread = Thread.currentThread()
        currentThread.setUncaughtExceptionHandler{ _, throwable ->
            val messagem = throwable.message
            val cause = throwable.cause
        }
    }
}