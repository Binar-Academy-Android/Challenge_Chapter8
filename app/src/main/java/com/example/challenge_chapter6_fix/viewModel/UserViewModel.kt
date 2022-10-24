package com.example.challenge_chapter6_fix.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.challenge_chapter6_fix.data.DataUserManager
import dagger.Module
import kotlinx.coroutines.launch

class UserViewModel(private val pref: DataUserManager): ViewModel() {
    fun saveUser(username: String,
                 name: String, email: String,
                 birthday: String, nomor: String,
                 password: String){
        viewModelScope.launch {
            pref.setUsername(username)
            pref.setPassword(password)
            pref.setName(name)
            pref.setEmail(email)
            pref.setBirthday(birthday)
            pref.setNomor(nomor)
        }
    }

    fun saveImage(uri: String) {
        viewModelScope.launch {
            pref.saveImage(uri)
            pref.setIsProfile(true)
        }
    }

    fun removeImage(){
        viewModelScope.launch {
            pref.removeImage()
            pref.setIsProfile(false)
        }
    }

    fun getDataUsername(): LiveData<String> {
        return pref.getUsername().asLiveData()
    }

    fun getDataPassword(): LiveData<String>{
        return pref.getPassword().asLiveData()
    }

    fun getName(): LiveData<String> {
        return pref.getName().asLiveData()
    }

    fun getEmail(): LiveData<String>{
        return pref.getEmail().asLiveData()
    }

    fun getBirthday(): LiveData<String> {
        return pref.getBirthday().asLiveData()
    }

    fun getNomor(): LiveData<String>{
        return pref.getNomor().asLiveData()
    }

    fun setIsLogin(isLogin:Boolean){
        viewModelScope.launch {
            pref.setIsLogin(isLogin)
        }
    }

    fun getIsLogin(): LiveData<Boolean> {
        return pref.getIsLogin().asLiveData()
    }

    fun getIsProfile(): LiveData<Boolean> {
        return pref.getIsProfile().asLiveData()
    }

}