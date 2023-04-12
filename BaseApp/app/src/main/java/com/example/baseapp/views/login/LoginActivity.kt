package com.example.baseapp.views.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityLoginBinding
import com.example.baseapp.views.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener { viewModel.makeLogin(baseContext, binding) }
        binding.btnRegister.setOnClickListener { startActivity(Intent(baseContext, RegisterActivity::class.java)) }
    }
}