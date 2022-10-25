package com.example.challenge_chapter6_fix

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_chapter6_fix.data.dao.FavoriteDao
import com.example.challenge_chapter6_fix.repository.FavoriteRepository
import com.example.challenge_chapter6_fix.viewModel.FavoriteViewModel
import java.lang.IllegalArgumentException

class FavoriteModelFactory() : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
//            return FavoriteViewModel(application = Application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }
}