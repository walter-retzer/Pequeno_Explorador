package com.wdretzer.pequeno_explorador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wdretzer.profile.ui.ProfileMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent = Intent(this, ProfileMainActivity ::class.java)
        startActivity(intent)
    }
}