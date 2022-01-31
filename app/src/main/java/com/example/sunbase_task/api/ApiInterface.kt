package com.example.sunbase_task.api

import com.example.sunbase_task.models.model
import com.example.sunbase_task.models.searchmodel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("photos")
    suspend fun getAllImages(
        @Query("page")
        pageNumber:Int=1,
        @Query("client_id")
        apikey:String = "_8IfsW1uHE616ZobsteW7ITjt9V6ZgsZ40RoM44C7KI"
    ): Response<model>

    @GET("search/photos")
    suspend fun searchImages(
        @Query("query")
        query:String,
        @Query("page")
        pageNumber:Int=1,
        @Query("client_id")
        apikey:String = "_8IfsW1uHE616ZobsteW7ITjt9V6ZgsZ40RoM44C7KI"
    ): Response<searchmodel>
}