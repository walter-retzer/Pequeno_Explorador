package com.wdretzer.pequeno_explorador

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.firebase.new_user.RegisterNewUserActivity
import com.wdretzer.pequeno_explorador.splashscreen.SplashScreenActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, RegisterNewUserActivity ::class.java)
            startActivity(intent)
        }, 5000)

        val intent = Intent(this, SplashScreenActivity ::class.java)
        startActivity(intent)

    }
}