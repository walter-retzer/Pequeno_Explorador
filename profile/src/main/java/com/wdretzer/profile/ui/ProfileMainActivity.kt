package com.wdretzer.profile.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.wdretzer.profile.R
import com.wdretzer.profile.databinding.ActivityProfileMainBinding

class ProfileMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileMainBinding
    private lateinit var navigationControl: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = savedInstanceState?.getBundle(BUNDLE) ?: intent.extras
        setupNavigationController(bundle)
    }

    private fun setupNavigationController(bundle: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_profile_fragment) as NavHostFragment
        navigationControl = navHostFragment.navController
        navigationControl.setGraph(R.navigation.profile_navigation_graph, bundle)
    }

    companion object {
        private const val BUNDLE = "ProfileBundle"

        fun start (context: Context){
            val intent = Intent(context, ProfileMainActivity::class.java)
            context.startActivity(intent)
        }
    }

}