package com.example.week2_0706012110027

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        Handler().postDelayed({

            val splash = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(splash)
            finish()
        },3000)
    }
}