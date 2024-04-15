package com.sopt.now.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sopt.now.R
import com.sopt.now.UserRepository
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userRepository: UserRepository
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRepository = UserRepository(this)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_fcv) as NavHostFragment
        navController = navHostFragment.navController

        if (userRepository.isUserLoggedIn()) {
            navController.navigate(R.id.mypageFragment)
        } else {
            navController.navigate(R.id.loginFragment)
        }
    }
}