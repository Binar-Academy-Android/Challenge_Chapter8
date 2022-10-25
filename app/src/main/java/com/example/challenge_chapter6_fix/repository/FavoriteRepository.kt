package com.example.challenge_chapter6_fix.repository

import androidx.lifecycle.LiveData
import com.example.challenge_chapter6_fix.data.dao.FavoriteDao
import com.example.challenge_chapter6_fix.data.dao.FavoriteData

class FavoriteRepository(private val data: FavoriteDao) {

    fun getAllDataFavorite() : List<FavoriteData> {
        return data.getAllFavorite()
    }

    suspend fun addFavorite(favorit: FavoriteData) = data.addFavorite(favorit)

    suspend fun cekFavorite(id: Int) = data.cekFavorite(id)

    suspend fun deleteFavorite(favorit: FavoriteData) = data.deleteFavorite(favorit)

}