package com.example.baseapp.views.main.ui.gallery

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.baseapp.adapters.AdapterImages
import com.example.baseapp.classes.Imagem
import com.example.baseapp.databinding.FragmentGalleryBinding
import com.example.baseapp.helpers.CommonHelpers.Companion.BASE_URL
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GalleryViewModel : ViewModel() {

    fun loadData(context: Context, binding: FragmentGalleryBinding) {
        val url = "$BASE_URL/logs"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val type = object: TypeToken<ArrayList<Imagem>>(){}.type
            val arrayList: ArrayList<Imagem> = Gson().fromJson(response, type)
            val listGallery = binding.listImages
            listGallery.layoutManager = GridLayoutManager(context, 3)
            listGallery.adapter = AdapterImages(context, arrayList)
        }, { error ->
            Log.e("Request_images_error: ", error.toString())
            Snackbar.make(binding.root, "Houve um erro com a sua requisição!!", Snackbar.LENGTH_LONG).show()
        })
        Volley.newRequestQueue(context).add(stringRequest)
    }
}