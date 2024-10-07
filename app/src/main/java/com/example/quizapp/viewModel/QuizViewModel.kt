package com.example.quizapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.model.QModel
import com.example.quizapp.domain.DataRepository.Companion.repository
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private var quizMutableLiveData = MutableLiveData<MutableList<QModel>>()
    var selectedAnswer = mutableListOf<String?>()
    var selectedOption:String ?  = null

    val quizLiveData: LiveData<MutableList<QModel>> = quizMutableLiveData
    private var _index = MutableLiveData(0)
    val index: LiveData<Int> = _index
    var category: String? = null
    var difficulty: String? = null
    private var dummyList = mutableListOf<QModel>()
    fun getQuizData() {
        viewModelScope.launch {
            val list = repository.getQuiz(dataCategory = category!!, dataDifficulty = difficulty!!)
            for (x in list?.results!!) {
                val opList = x!!.incorrectAnswers
                opList!!.add(x.correctAnswer!!)
                opList.shuffle()
                dummyList.add(QModel(x.question!!, x.correctAnswer,opList))
                quizMutableLiveData.value = dummyList
                Log.d("TAG", "getQuizData: ${quizLiveData.value?.get(index.value!!)?.cans}")
                Log.d("TAG", "getQuizData: ${quizLiveData.value?.get(index.value!!)?.opList}")
            }
        }
    }
    fun changeQuestion() {
        if (_index.value!! < 9) {
            checkResult()
            _index.value = _index.value!! + 1
        }
    }

    private fun checkResult() {
        if (selectedOption == quizLiveData.value?.get(index.value!!)?.cans) {
            selectedAnswer.add(selectedOption)
        }
    }
}