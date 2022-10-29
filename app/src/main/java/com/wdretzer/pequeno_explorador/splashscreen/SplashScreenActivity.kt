package com.wdretzer.pequeno_explorador.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wdretzer.pequeno_explorador.R


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
    }
}
