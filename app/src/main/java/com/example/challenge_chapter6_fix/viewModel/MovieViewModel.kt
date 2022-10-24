package com.example.challenge_chapter6_fix.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.challenge_chapter6_fix.model.Movie
import com.example.challenge_chapter6_fix.repository.MainRepository
import com.example.challenge_chapter6_fix.service.ApiClient
import com.example.challenge_chapter6_fix.utils.Resource
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewScoped
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val liveDataMovieDatabase : MutableLiveData<Movie?> = MutableLiveData()

    fun getLiveDataMovie() : MutableLiveData<Movie?> = liveDataMovieDatabase

    fun getDataMovie() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun showItemListData(){
        ApiClient.instance.getDetailFilm()
            .enqueue(object : Callback<Movie> {
                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie>
                ) {
                    if (response.isSuccessful){
                        getDataMovie()
                        liveDataMovieDatabase.postValue(response.body())
                    }else{
                        liveDataMovieDatabase.postValue(null)
                        Log.d("notSuccess", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    liveDataMovieDatabase.postValue(null)
                    Log.d("Failed",t.message.toString())
                }

            })
    }

}
