package com.example.challenge_chapter6_fix.data.dao

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class FavoriteData(
    @PrimaryKey(autoGenerate = true)
    var id_fav: Int,
    var title: String,
    var originaltitle: String,
    var overview: String,
    var posterPath: String,
) : Parcelable