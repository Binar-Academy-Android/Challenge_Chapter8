package com.example.challenge_chapter6_fix.service

class ApiHelper(private val apiService: ApiService) {

    suspend fun getAllFilmData() = apiService.getDetailFilm()
}