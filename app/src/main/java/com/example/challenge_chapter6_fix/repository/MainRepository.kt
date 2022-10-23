package com.example.challenge_chapter6_fix.repository

import com.example.challenge_chapter6_fix.service.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getAllFilmData()
}