package com.wdretzer.pequeno_explorador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.wdretzer.pequeno_explorador.splashscreen.SplashScreenActivity
import com.wdretzer.profile.ui.ProfileMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, ProfileMainActivity ::class.java)
            startActivity(intent)
        }, 5000)


        val intent = Intent(this, SplashScreenActivity ::class.java)
        startActivity(intent)

    }
}