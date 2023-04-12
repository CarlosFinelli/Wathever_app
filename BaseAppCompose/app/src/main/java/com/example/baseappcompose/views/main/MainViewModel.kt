package com.example.baseappcompose.views.main

import androidx.lifecycle.ViewModel
import com.example.baseappcompose.classes.Modulo

class MainViewModel : ViewModel() {

    fun defaultLoad() : ArrayList<Modulo> {
        val arrayList = ArrayList<Modulo>()
        for(i in 0 until 30) {
            val modulo = Modulo()
            modulo.id = i
            modulo.numero = i
            modulo.modulo = "Modulo $i"
            arrayList.add(modulo)
        }
        return arrayList
    }
}