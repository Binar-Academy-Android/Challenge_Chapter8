package com.binar.challenge_chapter6_fix.repository

import android.annotation.SuppressLint
import com.binar.challenge_chapter6_fix.service.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    fun getUsers() = apiHelper.getAllFilmData()
}