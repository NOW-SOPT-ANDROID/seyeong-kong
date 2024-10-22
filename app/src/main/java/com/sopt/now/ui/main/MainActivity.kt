package com.sopt.now.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.SoptApp
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
        setScrollToTop()
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fcv) as? NavHostFragment
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            setupNavigation(navController)
        } else {
            Snackbar.make(binding.root, "NavigationError", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupNavigation(navController: NavController) {
        binding.mainBnv.setupWithNavController(navController)
        hideBottomNavigationView(navController)

        val userRepository = (application as SoptApp).appContainer.userRepository
        if (userRepository.isUserLoggedIn()) {
            navController.navigate(R.id.homeFragment)
        } else {
            navController.navigate(R.id.loginFragment)
        }
    }

    private fun hideBottomNavigationView(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.mainBnv.visibility = when (destination.id) {
                R.id.loginFragment -> View.GONE
                R.id.signupFragment -> View.GONE
                R.id.changePasswordFragment -> View.GONE
                R.id.followerFragment -> View.GONE
                else -> View.VISIBLE
            }
        }
    }

    private fun setScrollToTop() {
        binding.mainBnv.setOnItemReselectedListener { item ->
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.main_fcv) as NavHostFragment
            val currentFragment = navHostFragment.childFragmentManager.fragments.first()
            when (item.itemId) {
                R.id.homeFragment -> (currentFragment as? HomeFragment)?.scrollToTop()
            }
        }
    }
}