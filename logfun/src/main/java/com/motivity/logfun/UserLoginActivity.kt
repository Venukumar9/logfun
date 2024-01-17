package com.motivity.logfun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class UserLoginActivity : AppCompatActivity() {

     lateinit var viewModel: UserLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val userRepository = UserRepository(RetrofitClient.instance)
        val viewModelFactory = UserLoginViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserLoginViewModel::class.java)

        val buttonLogin = findViewById<Button>(R.id.login)
        val editTextEmail = findViewById<TextView>(R.id.email)
        val editTextPassword = findViewById<TextView>(R.id.password)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginUser(email, password)
            } else {
                Toast.makeText(applicationContext, "Email and password are required.", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.loginResult.observe(this) { response ->
            when (response) {
                is ApiResponse.Success -> {
                    val loginResponse = response.data
                    if (loginResponse?.accessToken != null) {
                        // Successful login
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(applicationContext, "Login success", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext, "Response body is null", Toast.LENGTH_LONG).show()
                    }
                }
                is ApiResponse.Error -> {
                    // Handle HTTP error cases
                    val errorMessage = when (response.code) {
                        401 -> "Unauthorized"
                        else -> "HTTP Error: ${response.code}: ${response.message}"
                    }
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
                }

                else -> {}
            }
        }
    }
}