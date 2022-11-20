package com.binar.challenge_chapter6_fix

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binar.challenge_chapter6_fix.data.DataUserManager
import com.binar.challenge_chapter6_fix.viewModel.UserViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val pref: DataUserManager) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }
}