package com.example.app.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.app.MainActivity
import com.example.app.R
import com.example.app.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseOptions




class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel = LoginViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val data:  Uri? = intent.data
        if(data != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.loginViewModel = loginViewModel
        loginViewModel.startActivity.observe(this , Observer {
            if (it){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }

}