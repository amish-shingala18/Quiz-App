package com.example.quizapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.viewModel.QuizViewModel

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var dialog: Dialog
    private var selectedAnswer: String? = null
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
        val getDifficulty = intent.getStringExtra("difficulty")
        val getCategory = intent.getStringExtra("category")
        quizViewModel.difficulty = getDifficulty
        quizViewModel.category = getCategory
        initCLick()
        quizViewModel.getQuizData()
        showLoader()
        quizViewModel.quizLiveData.observe(this) {
            binding.txtQuestions.text = it?.get(0)?.qa
            binding.txtOption1.text = it[0].opList?.get(0)
            binding.txtOption2.text = it[0].opList?.get(1)
            binding.txtOption3.text = it[0].opList?.get(2)
            binding.txtOption4.text = it[0].opList?.get(3)
            dialog.dismiss()
        }
        quizViewModel.index.observe(this) {
            binding.txtQuestions.text = quizViewModel.quizLiveData.value?.get(it)?.qa
            binding.txtOption1.text = quizViewModel.quizLiveData.value?.get(it)?.opList?.get(0)
            binding.txtOption2.text = quizViewModel.quizLiveData.value?.get(it)?.opList?.get(1)
            binding.txtOption3.text = quizViewModel.quizLiveData.value?.get(it)?.opList?.get(2)
            binding.txtOption4.text = quizViewModel.quizLiveData.value?.get(it)?.opList?.get(3)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initCLick() {
        binding.cvOption1.setOnClickListener {
            binding.imgOption1.setImageResource(R.drawable.selected_answer)
            binding.imgOption2.setImageResource(R.drawable.unselected_answer)
            binding.imgOption3.setImageResource(R.drawable.unselected_answer)
            binding.imgOption4.setImageResource(R.drawable.unselected_answer)
            binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.light_green))
            binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.btnNext.setBackgroundColor(resources.getColor(R.color.app))
        }
        binding.cvOption2.setOnClickListener {
            binding.imgOption1.setImageResource(R.drawable.unselected_answer)
            binding.imgOption2.setImageResource(R.drawable.selected_answer)
            binding.imgOption3.setImageResource(R.drawable.unselected_answer)
            binding.imgOption4.setImageResource(R.drawable.unselected_answer)
            binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.light_green))
            binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.btnNext.setBackgroundColor(resources.getColor(R.color.app))
        }
        binding.cvOption3.setOnClickListener {
            binding.imgOption1.setImageResource(R.drawable.unselected_answer)
            binding.imgOption2.setImageResource(R.drawable.unselected_answer)
            binding.imgOption3.setImageResource(R.drawable.selected_answer)
            binding.imgOption4.setImageResource(R.drawable.unselected_answer)
            binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.light_green))
            binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.btnNext.setBackgroundColor(resources.getColor(R.color.app))
        }
        binding.cvOption4.setOnClickListener {
            binding.imgOption1.setImageResource(R.drawable.unselected_answer)
            binding.imgOption2.setImageResource(R.drawable.unselected_answer)
            binding.imgOption3.setImageResource(R.drawable.unselected_answer)
            binding.imgOption4.setImageResource(R.drawable.selected_answer)
            binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.light_green))
            binding.btnNext.setBackgroundColor(resources.getColor(R.color.app))
        }
        binding.btnNext.setOnClickListener {
            if (binding.btnNext.background is ColorDrawable) {
                val color = (binding.btnNext.background as ColorDrawable).color
                if (color == resources.getColor(R.color.disable_btn)) {
                    !binding.btnNext.isClickable
                }
            }
            else {
                binding.btnNext.isClickable
                if (binding.cvOption1.cardBackgroundColor.defaultColor == resources.getColor(R.color.light_green)) {
                    selectedAnswer = binding.txtOption1.text.toString()
                } else if (binding.cvOption2.cardBackgroundColor.defaultColor == resources.getColor(R.color.light_green)) {
                    selectedAnswer = binding.txtOption2.text.toString()
                } else if (binding.cvOption3.cardBackgroundColor.defaultColor == resources.getColor(R.color.light_green)) {
                    selectedAnswer = binding.txtOption3.text.toString()
                } else if (binding.cvOption4.cardBackgroundColor.defaultColor == resources.getColor(R.color.light_green)) {
                    selectedAnswer = binding.txtOption4.text.toString()
                }
                quizViewModel.changeQuestion()
                //after changing the question no option should be selected
                
            }
        }
    }
    private fun showLoader() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.loader_item)
        dialog.show()
    }
}