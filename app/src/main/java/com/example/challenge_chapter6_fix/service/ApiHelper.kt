package com.example.challenge_chapter6_fix.service

class ApiHelper(private val apiService: ApiService) {

    fun getAllFilmData() = apiService.getDetailFilm()
}