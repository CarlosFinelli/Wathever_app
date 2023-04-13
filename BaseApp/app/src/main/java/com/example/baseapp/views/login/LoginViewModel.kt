package com.example.baseapp.views.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.baseapp.databinding.ActivityLoginBinding
import com.example.baseapp.views.main.MainActivity
import java.math.BigInteger
import java.security.MessageDigest

class LoginViewModel : ViewModel() {

    fun makeLogin(context: Context, binding: ActivityLoginBinding) {
        context.startActivity(Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
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