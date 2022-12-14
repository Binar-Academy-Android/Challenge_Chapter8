@file:Suppress("PrivatePropertyName, PropertyName")

package com.binar.challenge_chapter6_fix.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.binar.challenge_chapter6_fix.data.dao.FavoriteData
import com.binar.challenge_chapter6_fix.data.dao.FavoriteDatabase
import com.binar.challenge_chapter6_fix.model.Item
import com.binar.challenge_chapter6_fix.repository.FavoriteRepository
import com.binar.challenge_chapter6_fix.service.ApiClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val repository : FavoriteRepository

    init {
        val favoriteDao = FavoriteDatabase.getInstance(application)?.favoriteDao()
        repository = FavoriteRepository(favoriteDao!!)
    }

    private val listFavorite: MutableLiveData<List<FavoriteData>> = MutableLiveData()
    val list_favorite: LiveData<List<FavoriteData>> get() = listFavorite


    @OptIn(DelicateCoroutinesApi::class)
    fun getAllFavoriteMovie() {
        GlobalScope.launch {
            listFavorite.postValue(repository.getAllDataFavorite())
        }
    }

    private val getMovie: MutableLiveData<Item?> = MutableLiveData()
    val movieDetail: LiveData<Item?> get() = getMovie

    fun getMovie(id: Int) {
        ApiClient.instance.getMovieDetail(id)
            .enqueue(object : Callback<Item> {
                override fun onResponse(
                    call: Call<Item>,
                    response: Response<Item>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            getMovie.postValue(responseBody)
                        }
                    }
                }
                override fun onFailure(call: Call<Item>, t: Throwable) {}
            })
    }

    private val isFavorit: MutableLiveData<Boolean> = MutableLiveData()
    val favorit: LiveData<Boolean> get() = isFavorit

    @OptIn(DelicateCoroutinesApi::class)
    fun isFav(id: Int) {
        GlobalScope.launch {
            isFavorit.postValue(repository.cekFavorite(id))
        }
    }

    private val fav_movie: MutableLiveData<FavoriteData> = MutableLiveData()
    val favoritMovie: LiveData<FavoriteData> get() = fav_movie

    @OptIn(DelicateCoroutinesApi::class)
    fun addFavorit(favorit: FavoriteData) {
        GlobalScope.launch {
            repository.addFavorite(favorit)
            fav_movie.postValue(favorit)
        }
    }

    private val delete_favorit: MutableLiveData<FavoriteData> = MutableLiveData()
    val deleteFavorit: LiveData<FavoriteData> get() = delete_favorit

    @OptIn(DelicateCoroutinesApi::class)
    fun removeFavorit(favorit: FavoriteData) {
        GlobalScope.launch {
            repository.deleteFavorite(favorit)
            delete_favorit.postValue(favorit)
        }
    }
}
