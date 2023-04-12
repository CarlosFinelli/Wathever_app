package com.example.baseapp.views.splash

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.example.baseapp.R
import com.example.baseapp.views.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.systemBars())
        } else {
            window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        }

        Handler().postDelayed({
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }, 3000)
    }
}