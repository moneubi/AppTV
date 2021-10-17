package com.sunu.app.apptv.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunu.app.apptv.common.Shared
import com.sunu.app.apptv.data.remote.AppTvApi
import com.sunu.app.apptv.di.RetrofitInstance
import com.sunu.app.apptv.domain.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private lateinit var movieShared: Movies
    var contentData: MutableLiveData<ContentList?> = MutableLiveData()

    fun getContentListobservable(): MutableLiveData<ContentList?>{
        return contentData
    }

    fun getContents(detaillink: String){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(AppTvApi::class.java)
        val call = retrofitInstance.getPitchProgram(detaillink)
        call.enqueue(object : Callback<ContentList>{
            override fun onResponse(call: Call<ContentList>, response: Response<ContentList>) {
                if (response.isSuccessful){
                    contentData.postValue(response.body())
                }else{

                }
            }

            override fun onFailure(call: Call<ContentList>, t: Throwable) {
                contentData.postValue(null)
                Log.d("KKKKKK","Error : ${t.cause}")
            }

        })
    }

    fun setMovieShared(){
        if (Shared.movie != null){
            movieShared = Shared.movie!!
        }
    }

    fun getMovieShared(): Movies{
        return movieShared
    }
}