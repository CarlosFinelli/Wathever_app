package com.example.baseapp.views.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseapp.adapters.MainAdapter
import com.example.baseapp.classes.Modulo
import com.example.baseapp.databinding.ActivityMainBinding

class MainViewModel : ViewModel() {


    fun defaultLoad(context: Context, binding: ActivityMainBinding) {
        val arrayList = ArrayList<Modulo>()
        for(i in 0 until 10) {
            val modulo = Modulo()
            modulo.id = i
            modulo.numero = i
            modulo.modulo = "Módulo $i"
            arrayList.add(modulo)
        }
        val listModulos = binding.listModulos
        listModulos.layoutManager = GridLayoutManager(context, 2)
        listModulos.adapter = MainAdapter(context, arrayList)
    }
}