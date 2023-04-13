package com.example.baseappcompose.views.login

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
import com.example.baseappcompose.classes.Usuario
import org.json.JSONObject
import java.math.BigInteger
import java.security.MessageDigest

class LoginViewModel : ViewModel() {
    var isSnackBarShowing by mutableStateOf(false)
    private set
    var showNextScreen: Boolean? by mutableStateOf(null)
    private set

    fun showSnackBar() {
        isSnackBarShowing = true
    }

    fun hideSnackBar() {
        isSnackBarShowing = false
    }

    fun makeLogin(context: Context, login: String, senha: String) {
        val url = ""
        val json = JSONObject()
        json.put("users_login", login)
        json.put("users_password", senha)
        val stringRequest = JsonObjectRequest(Request.Method.POST, url, json, { response ->

        }, { error ->
            Log.e("Login_error: ", error.toString())
            showSnackLogin()
        })
        Volley.newRequestQueue(context).add(stringRequest)
    }

    fun registerLog(context: Context, user: Usuario) {
        val url = ""
        val json = JSONObject()
        json.put("logs_message", "O usuário ${user.users_nome} realizou login")
        json.put("logs_user_id", user.users_id)
        val stringRequest = JsonObjectRequest(Request.Method.POST, url, json, { response ->

        }, { error ->
            Log.e("Register_error: ", error.toString())

        })
        Volley.newRequestQueue(context).add(stringRequest)
    }

    fun showSnackLogin() {
        showNextScreen = false
        showNextScreen = null
    }

    fun showNextScreen() {
        showNextScreen = true
        showNextScreen = null
    }

    fun generateSHA(text: String) : String {
        val md = MessageDigest.getInstance("SHA-256")
        val messageDigest = md.digest(text.toByteArray())
        val no = BigInteger(1, messageDigest)
        var hashText = no.toString(16)
        while (hashText.length < 32) {
            hashText = "0$hashText"
        }
        return hashText
    }
}