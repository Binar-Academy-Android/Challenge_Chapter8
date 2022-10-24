package com.example.challenge_chapter6_fix.repository

import com.example.challenge_chapter6_fix.service.ApiHelper
import javax.inject.Inject


class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getAllFilmData()
}