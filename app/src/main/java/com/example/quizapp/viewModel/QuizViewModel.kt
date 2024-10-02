package com.example.quizapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.model.QuizModel
import com.example.quizapp.domain.DataRepository.Companion.repository
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel(){
    //var questionIndex  =0
    private var quizMutableLiveData=MutableLiveData<QuizModel>()
    val quizLiveData:LiveData<QuizModel> =quizMutableLiveData
    var category:String?=null
    var difficulty:String?=null
    fun getQuizData(){
        viewModelScope.launch {
           quizMutableLiveData.value=repository.getQuiz(dataCategory = category!!, dataDifficulty = difficulty!!)
        }
    }
}