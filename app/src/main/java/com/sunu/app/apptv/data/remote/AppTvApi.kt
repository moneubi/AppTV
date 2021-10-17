package com.sunu.app.apptv.data.remote

import com.sunu.app.apptv.common.Constatnts
import com.sunu.app.apptv.domain.model.ContentList
import com.sunu.app.apptv.domain.model.MovieLists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface AppTvApi {
    /**
     * Method to get list of search
     * **/
    @GET("/apps/v2/contents")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getMovieBySearch(@Query("${Constatnts.REF_SEARCH}") title: String): Call<MovieLists>

    /**
     * Method to get pitch
     * */
    @GET("/apps/v2/details/programme/{detaillink}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getPitchProgram(@Path("detaillink") pitchId: String): Call<ContentList>
}