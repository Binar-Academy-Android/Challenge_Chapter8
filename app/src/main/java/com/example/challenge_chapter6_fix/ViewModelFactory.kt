package com.example.challenge_chapter6_fix

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_chapter6_fix.data.DataUserManager
import com.example.challenge_chapter6_fix.viewModel.UserViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val pref: DataUserManager) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    private lateinit var application: Application
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }
}