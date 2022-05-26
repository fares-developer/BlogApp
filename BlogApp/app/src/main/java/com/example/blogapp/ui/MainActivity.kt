package com.example.blogapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.blogapp.R
import com.example.blogapp.core.hide
import com.example.blogapp.core.show
import com.example.blogapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creamos un navHostFragment y los establecemos
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNavigationView
            .setupWithNavController(navController)

        observeDestinationChange(navController)

    }

    //Controlamos el destino del navControler, utilizamos esto para ocultar el bottom en el login y registro
    private fun observeDestinationChange(navController: NavController) {
        navController.addOnDestinationChangedListener { controler, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment -> binding.bottomNavigationView.hide()
                R.id.registerFragment -> binding.bottomNavigationView.hide()
                else -> binding.bottomNavigationView.show()
            }
        }
    }
}