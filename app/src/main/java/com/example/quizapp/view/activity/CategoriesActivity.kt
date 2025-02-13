package com.example.quizapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityCategoriesBinding

class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initClick()
    }
    private fun initClick(){
        binding.lnrAllCategories.setOnClickListener {
            val anyCategoryIntent= Intent(this@CategoriesActivity,DifficultyActivity::class.java)
            anyCategoryIntent.putExtra("category","")
            startActivity(anyCategoryIntent)
        }
        binding.lnrGK.setOnClickListener {
            val gkIntent= Intent(this@CategoriesActivity,DifficultyActivity::class.java)
            gkIntent.putExtra("category","9")
            startActivity(gkIntent)
        }
        binding.lnrTelevision.setOnClickListener {
            val televisionIntent= Intent(this@CategoriesActivity,DifficultyActivity::class.java)
            televisionIntent.putExtra("category","14")
            startActivity(televisionIntent)
        }
        binding.lnrHistory.setOnClickListener {
            val historyIntent= Intent(this@CategoriesActivity,DifficultyActivity::class.java)
            historyIntent.putExtra("category","23")
            startActivity(historyIntent)
        }
        binding.lnrSports.setOnClickListener {
            val sportsIntent= Intent(this@CategoriesActivity,DifficultyActivity::class.java)
            sportsIntent.putExtra("category","21")
            startActivity(sportsIntent)
        }
        binding.lnrMaths.setOnClickListener {
            val mathsIntent= Intent(this@CategoriesActivity,DifficultyActivity::class.java)
            mathsIntent.putExtra("category","19")
            startActivity(mathsIntent)
        }
        binding.lnrPolitics.setOnClickListener {
            val politicsIntent= Intent(this@CategoriesActivity,DifficultyActivity::class.java)
            politicsIntent.putExtra("category","24")
            startActivity(politicsIntent)
        }
        binding.lnrCelebrities.setOnClickListener {
            val celebritiesIntent= Intent(this@CategoriesActivity,DifficultyActivity::class.java)
            celebritiesIntent.putExtra("category","27")
            startActivity(celebritiesIntent)
        }
    }
}