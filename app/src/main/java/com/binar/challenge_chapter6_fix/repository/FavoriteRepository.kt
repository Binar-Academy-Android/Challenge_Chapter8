package com.binar.challenge_chapter6_fix.repository

import com.binar.challenge_chapter6_fix.data.dao.FavoriteDao
import com.binar.challenge_chapter6_fix.data.dao.FavoriteData

class FavoriteRepository(private val data: FavoriteDao) {

    fun getAllDataFavorite() : List<FavoriteData> {
        return data.getAllFavorite()
    }

    fun addFavorite(favorit: FavoriteData) = data.addFavorite(favorit)

    fun cekFavorite(id: Int) = data.cekFavorite(id)

    fun deleteFavorite(favorit: FavoriteData) = data.deleteFavorite(favorit)

}