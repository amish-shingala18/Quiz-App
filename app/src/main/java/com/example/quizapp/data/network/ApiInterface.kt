package com.example.quizapp.data.network

import com.example.quizapp.data.model.QuizModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api.php")
    fun getQuizApi():Call<QuizModel>

    @GET("api.php")
    fun getCategoryApi(
        @Query("type") type:String="multiple",
        @Query("amount") amount:String="10",
        @Query("difficulty") difficulty:String,
        @Query("category") category:String
    ):Call<QuizModel>
}