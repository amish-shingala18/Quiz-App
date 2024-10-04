package com.example.quizapp.domain

import com.example.quizapp.data.helper.ApiHelper

class DataRepository {
    companion object{
        val repository=DataRepository()
    }
    private val apiHelper=ApiHelper()
    suspend fun getQuiz(dataCategory:String,dataDifficulty:String) = apiHelper.callingApi(dataCategory,dataDifficulty)
}