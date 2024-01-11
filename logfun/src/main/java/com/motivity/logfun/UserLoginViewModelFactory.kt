package com.motivity.logfun

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class UserLoginViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserLoginViewModel(userRepository) as T
    }
}