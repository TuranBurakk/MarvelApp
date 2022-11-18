package com.example.marvelapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.MainActivity
import com.example.marvelapp.ui.LoginFragment.Companion.EXTRA_NAME
import com.example.marvelapp.databinding.ActivityGoogleSingInBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GoogleSingInActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGoogleSingInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleSingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textDisplayName.text = intent.getStringExtra(EXTRA_NAME)
        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}