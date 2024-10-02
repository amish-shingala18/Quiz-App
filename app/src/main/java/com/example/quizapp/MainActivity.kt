package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.viewModel.QuizViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel by viewModels<QuizViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            quizViewModel.quizLiveData.observe(this){
            Log.d("TAG", "onCreate:==================== ${it.results?.get(0)?.question}")
            binding.txtQuestions.text=it.results?.get(0)?.question
            binding.txtOption1.text=it.results?.get(0)?.correctAnswer
            binding.txtOption2.text=it.results?.get(0)?.incorrectAnswers?.get(0)
            binding.txtOption3.text=it.results?.get(0)?.incorrectAnswers?.get(1)
            binding.txtOption4.text=it.results?.get(0)?.incorrectAnswers?.get(2)
        }
    }
}