package com.example.talha.dynamictabs.Api

import com.example.talha.dynamictabs.model.NewsDataApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

//    @GET("top-headlines?country=us&apiKey=9bdc3eebf3d34aa099ab65815f3d75ed")
//    fun getNewsFromApi():Call<NewsDataApi>
    @GET("top-headlines")
    fun getNewsFromApi(@Query("country")country:String,@Query("apiKey")apiKey:String):Call<NewsDataApi>
}