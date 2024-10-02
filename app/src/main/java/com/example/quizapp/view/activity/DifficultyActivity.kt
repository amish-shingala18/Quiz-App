package com.example.quizapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityDifficultyBinding
import com.example.quizapp.viewModel.QuizViewModel

class DifficultyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDifficultyBinding
    private val quizViewModel by viewModels<QuizViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDifficultyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initClick()
    }
    private fun initClick(){
        val getCategory = intent.getStringExtra("category")
        binding.cardEasy.setOnClickListener {
            quizViewModel.difficulty="easy"
            quizViewModel.category=getCategory
            quizViewModel.getQuizData()
            val easyIntent= Intent(this@DifficultyActivity,MainActivity::class.java)
            startActivity(easyIntent)
        }
        binding.cardMedium.setOnClickListener {
            quizViewModel.difficulty="medium"
            quizViewModel.category=getCategory
            quizViewModel.getQuizData()
            val mediumIntent= Intent(this@DifficultyActivity,MainActivity::class.java)
            startActivity(mediumIntent)
        }
        binding.cardHard.setOnClickListener {
            quizViewModel.difficulty="hard"
            quizViewModel.category=getCategory
            quizViewModel.getQuizData()
            val hardIntent= Intent(this@DifficultyActivity,MainActivity::class.java)
            startActivity(hardIntent)
        }

    }
}