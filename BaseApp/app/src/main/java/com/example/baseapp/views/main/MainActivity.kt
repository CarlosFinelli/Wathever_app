package com.example.baseapp.views.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityMainBinding
import com.example.baseapp.views.login.LoginActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.defaultLoad(baseContext, binding)

        binding.btnSair.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
    }
}