package com.example.baseapp.views.register

import androidx.lifecycle.ViewModel
import java.math.BigInteger
import java.security.MessageDigest

class RegisterViewModel : ViewModel() {



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