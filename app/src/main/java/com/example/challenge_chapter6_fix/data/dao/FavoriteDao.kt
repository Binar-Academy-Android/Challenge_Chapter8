package com.example.challenge_chapter6_fix.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM FavoriteData")
    fun getAllFavorite() : List<FavoriteData>

    @Query("SELECT EXISTS(SELECT id_fav FROM FavoriteData WHERE id_fav = :id)")
    fun cekFavorite(id : Int) : Boolean

    @Insert
    fun addFavorite(favoriteMovie: FavoriteData)

    @Delete
    fun deleteFavorite(favoriteMovie: FavoriteData)
}