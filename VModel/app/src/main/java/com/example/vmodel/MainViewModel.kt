package com.example.vmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    var mContador = MutableLiveData<String>().apply { value = contador.toString()}

    private var contador:Int = 0

    private fun setContador(){
        mContador.value = contador.toString()
    }

    private fun validaContador(){
        if (contador > 5) contador = 0

        setContador()
    }

    fun contador(){
        contador++;
        validaContador()
    }
}