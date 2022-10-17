package com.wdretzer.profile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.profile.databinding.ActivityProfileMainBinding

class ProfileMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}