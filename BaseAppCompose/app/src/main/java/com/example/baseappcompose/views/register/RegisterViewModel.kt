package com.example.baseappcompose.views.register

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RegisterViewModel : ViewModel() {
    var showSnackError by mutableStateOf(false)
    private set

    private fun showSnackbarError() {
        showSnackError = true
    }

    fun hideSnackBarError() {
        showSnackError = false
    }

    fun registerUser(context: Context, usuario: String, senha: String) {
        val url = ""
        val json = JSONObject()
        json.put("users_login", usuario)
        json.put("users_password", senha)
        val stringRequest = JsonObjectRequest(Request.Method.POST, url, json, { response ->

            hideSnackBarError()
        }, { error ->
            Log.e("Register_user_error: ", error.toString())
            showSnackbarError()
        })
        Volley.newRequestQueue(context).add(stringRequest)
    }
}