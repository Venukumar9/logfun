package com.motivity.logfun

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserLoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResult: LiveData<ApiResponse<LoginResponse>> get() = _loginResult

    fun loginUser(email: String, password: String) {
        userRepository.userLogin(email, password) { response ->
            _loginResult.value = response
        }
    }
}
