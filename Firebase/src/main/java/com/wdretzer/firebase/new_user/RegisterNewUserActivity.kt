package com.wdretzer.firebase.new_user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.wdretzer.firebase.R


class RegisterNewUserActivity : AppCompatActivity() {

    private lateinit var navigator: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_user)

        supportActionBar?.hide()
        initNavigation()
    }

    private fun initNavigation(){
        val navHostNavigation = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigator = navHostNavigation.navController
        navigator.setGraph(R.navigation.register_user_graph)
    }

}
