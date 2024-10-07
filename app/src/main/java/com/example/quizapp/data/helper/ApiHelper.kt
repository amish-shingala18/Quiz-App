package com.example.quizapp.data.helper

import com.example.quizapp.data.model.QuizModel
import com.example.quizapp.data.network.ApiClient.Companion.getApi
import com.example.quizapp.data.network.ApiInterface
import retrofit2.awaitResponse

class ApiHelper {
    suspend fun callingApi(category: String,difficulty: String) : QuizModel?{
        val retrofit = getApi().create(ApiInterface::class.java)
        val response=retrofit.getCategoryApi(category = category, difficulty = difficulty).awaitResponse()
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }
}