package com.example.quizapp.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getResult()
        initClick()
        onBack
    }
    @SuppressLint("SetTextI18n")
    private fun getResult(){
        val correctAnswer=intent.getIntExtra("correctAnswer",0)
        binding.txtCorrect.text="$correctAnswer Correct"
        binding.txtInCorrect.text="${(10-correctAnswer)} Incorrect"

        if(correctAnswer<5){
            binding.imgResult.setImageResource(R.drawable.quiz_lose)
            binding.txtWish.text="Congratulations"
            binding.txtSlogan.text="You Crushed It – Quiz Genius!"
        }
        else{
            binding.imgResult.setImageResource(R.drawable.quiz_winner)
            binding.txtWish.text="Keep going, Success is near"
            binding.txtSlogan.text="Not your best try – keep practicing!"
        }
    }
    private fun initClick(){
        binding.txtNewQuiz.setOnClickListener {
            startActivity(Intent(this@ResultActivity,CategoriesActivity::class.java))
        }
    }
    private val onBack = onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            startActivity(Intent(this@ResultActivity,CategoriesActivity::class.java))
        }
    })
}