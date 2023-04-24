package com.example.baseapp.views.main.ui.gallery

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.baseapp.R
import com.example.baseapp.adapters.AdapterImages
import com.example.baseapp.classes.Imagem
import com.example.baseapp.databinding.FragmentGalleryBinding
import com.example.baseapp.helpers.CommonHelpers.Companion.BASE_URL
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class GalleryViewModel : ViewModel() {

    fun loadData(context: Context, binding: FragmentGalleryBinding) {
        val url = "$BASE_URL/images"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val type = object: TypeToken<ArrayList<Imagem>>(){}.type
            val arrayList: ArrayList<Imagem> = Gson().fromJson(response, type)
            val listGallery = binding.listImages
            listGallery.layoutManager = GridLayoutManager(context, 3)
            listGallery.adapter = AdapterImages(context, arrayList)
            binding.cardPhoto.visibility = View.GONE
        }, { error ->
            Log.e("Request_images_error: ", error.toString())
            Snackbar.make(binding.root, "Houve um erro com a sua requisição!!", Snackbar.LENGTH_LONG).show()
        })
        Volley.newRequestQueue(context).add(stringRequest)
    }

    fun saveImage(context: Context, binding: FragmentGalleryBinding, navController: NavController, base64: String) {
        val url = "$BASE_URL/images"
        val description = binding.editDescription.text.toString()
        val json = JSONObject()
        var image = base64.replace("\n", "")
        json.put("base64_imagem", base64)
        json.put("desc_imagem", description)
        val stringRequest = JsonObjectRequest(Request.Method.POST, url, json, { response ->
            Snackbar.make(binding.root, "Sucesso ao salvar a sua imagem!!", Snackbar.LENGTH_LONG).show()
            navController.popBackStack(R.id.nav_galeria, false)
        }, { error ->
            Log.e("Post_image_error: ", error.toString())
            Snackbar.make(binding.root, "Houve um erro ao realizar o envio da sua imagem, por favor, tente novamente!!", Snackbar.LENGTH_LONG).show()
        })
        Volley.newRequestQueue(context).add(stringRequest)
    }
}