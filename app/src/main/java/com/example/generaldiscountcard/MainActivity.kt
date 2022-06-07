package com.example.generaldiscountcard

//import com.example.generaldiscountcard.utilits.initFireBase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.generaldiscountcard.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.setupWithNavController(navController)

    }

    fun signOut() {
        Firebase.auth.signOut()
        SplashScreenActivity().signIn()
    }

    fun updateProfileDisplayName(newName: String) {
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = newName
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                }
            }
    }

    fun updatePassword(newPassword: String) {
        val user = Firebase.auth.currentUser
        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User password updated.")
                }
            }
    }
}
