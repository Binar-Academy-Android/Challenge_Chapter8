package com.example.challenge_chapter6_fix.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [
    FavoriteData::class],
    version = 1 )
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE : FavoriteDatabase? = null

        fun getInstance(context : Context): FavoriteDatabase? {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}
//@Database(entities = [FavoriteData::class], version = 1)
//abstract class FavoriteDatabase : RoomDatabase() {
//    abstract fun favoriteDao(): FavoriteDao
//}