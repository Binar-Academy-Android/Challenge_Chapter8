package com.example.challenge_chapter6_fix.service

import com.example.challenge_chapter6_fix.model.Item
import com.example.challenge_chapter6_fix.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // GET digunakan untuk memanggil semua data yang terdapat pada server
    @GET("3/list/1?api_key=c1bc659015ac6e62beb57434d9793ed9&language=en-US")
    fun getDetailFilm() : Call<Movie>

    @GET("/3/movie/{id}?api_key=9428967aca5607f7a2bbcb7a46f0ecfe")
    fun getMovieDetail(@Path("id") id: Int): Call<Item>
}