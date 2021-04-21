package com.example.vmodel

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vmodel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    var contador:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initClicks()
        initData()
    }

    private fun initData() {
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mViewModel.mContador.observe(this, Observer { value ->
            binding.txtContador.setText(value)
        })
    }

    private fun initClicks() {
        binding.btnDados.setOnClickListener {
            mViewModel.contador()
        }

        binding.btnMostrar.setOnClickListener {
            val contador = mViewModel.mContador.value
            Toast.makeText(applicationContext, "Valor Contador: $contador",Toast.LENGTH_SHORT).show()
        }
    }
}