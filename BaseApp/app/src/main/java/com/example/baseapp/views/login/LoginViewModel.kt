package com.example.baseapp.views.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.baseapp.databinding.ActivityLoginBinding
import com.example.baseapp.views.main.MainActivity

class LoginViewModel : ViewModel() {

    fun makeLogin(context: Context, binding: ActivityLoginBinding) {
        context.startActivity(Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}