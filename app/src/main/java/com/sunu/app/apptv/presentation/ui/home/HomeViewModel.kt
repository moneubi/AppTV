package com.sunu.app.apptv.presentation.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunu.app.apptv.data.remote.AppTvApi
import com.sunu.app.apptv.di.RetrofitInstance
import com.sunu.app.apptv.domain.model.MovieLists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel(){

    var movieListData: MutableLiveData<MovieLists> = MutableLiveData()

    fun getMovieListObserverable(): MutableLiveData<MovieLists>{
        return movieListData
    }

    fun getMovies(title: String){
        //init my retroinstance
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(AppTvApi::class.java)
        val call = retrofitInstance.getMovieBySearch(title)
        call.enqueue(object : Callback<MovieLists>{
            override fun onFailure(call: Call<MovieLists>, t: Throwable) {
                movieListData.postValue(null)
            }

            override fun onResponse(call: Call<MovieLists>, response: Response<MovieLists>) {
                if (response.isSuccessful){
                    movieListData.postValue(response.body())
                }else{
                    //get error
                    Log.d("GGGG","error : ${response.body()}")
                }
            }
        })
    }
}