package com.example.quizapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityDifficultyBinding

class DifficultyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDifficultyBinding
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
            val easyIntent= Intent(this@DifficultyActivity,MainActivity::class.java)
            easyIntent.putExtra("category",getCategory)
            easyIntent.putExtra("difficulty","easy")
            startActivity(easyIntent)
        }
        binding.cardMedium.setOnClickListener {
            val mediumIntent= Intent(this@DifficultyActivity,MainActivity::class.java)
            mediumIntent.putExtra("category",getCategory)
            mediumIntent.putExtra("difficulty","medium")
            startActivity(mediumIntent)
        }
        binding.cardHard.setOnClickListener {
            val hardIntent= Intent(this@DifficultyActivity,MainActivity::class.java)
            hardIntent.putExtra("category",getCategory)
            hardIntent.putExtra("difficulty","hard")
            startActivity(hardIntent)
        }

    }
}