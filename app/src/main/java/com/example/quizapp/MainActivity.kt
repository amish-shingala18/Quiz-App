    package com.example.quizapp

    import android.annotation.SuppressLint
    import android.app.Dialog
    import android.os.Bundle
    import android.os.CountDownTimer
    import android.util.Log
    import androidx.activity.viewModels
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import com.example.quizapp.databinding.ActivityMainBinding
    import com.example.quizapp.viewModel.QuizViewModel

    @Suppress("DEPRECATION")
    class MainActivity : AppCompatActivity() {
        private lateinit var dialog: Dialog
        private var selectedAnswer = mutableListOf<String?>()
        private lateinit var binding: ActivityMainBinding
        private val quizViewModel by viewModels<QuizViewModel>()
        @SuppressLint("ResourceType", "SetTextI18n")
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
                binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.white))
                binding.imgOption1.setImageResource(R.drawable.unselected_answer)
                binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.white))
                binding.imgOption2.setImageResource(R.drawable.unselected_answer)
                binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.white))
                binding.imgOption3.setImageResource(R.drawable.unselected_answer)
                binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.white))
                binding.imgOption4.setImageResource(R.drawable.unselected_answer)
                binding.txtQuestions.text = quizViewModel.quizLiveData.value?.get(it)?.qa
                binding.txtOption1.text = quizViewModel.quizLiveData.value?.get(it)?.opList?.get(0)
                binding.txtOption2.text = quizViewModel.quizLiveData.value?.get(it)?.opList?.get(1)
                binding.txtOption3.text = quizViewModel.quizLiveData.value?.get(it)?.opList?.get(2)
                binding.txtOption4.text = quizViewModel.quizLiveData.value?.get(it)?.opList?.get(3)
                binding.btnNext.isClickable=true
                if (quizViewModel.count == 10) {
                    binding.btnNext.text = "SUBMIT"
                } else {
                    binding.btnNext.text = "Next"
                }
            }
        }
        @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "ResourceType")
        private fun initCLick() {
            option()
            binding.btnNext.setOnClickListener {
                val unselectedColor = resources.getColor(R.color.white)
                val selectedColor = resources.getColor(R.color.light_green)
                if (binding.cvOption1.cardBackgroundColor.defaultColor == unselectedColor &&
                    binding.cvOption2.cardBackgroundColor.defaultColor == unselectedColor &&
                    binding.cvOption3.cardBackgroundColor.defaultColor == unselectedColor &&
                    binding.cvOption4.cardBackgroundColor.defaultColor == unselectedColor) {
                    binding.btnNext.isClickable = false
                } else {
                    binding.btnNext.isClickable = true
                    when {
                        binding.cvOption1.cardBackgroundColor.defaultColor == selectedColor -> {
                            val option1 = binding.txtOption1.text.toString()
                            if(option1==quizViewModel.quizLiveData.value?.get(quizViewModel.index.value!!)?.cans){
                                selectedAnswer.add(option1)
                            }
                        }
                        binding.cvOption2.cardBackgroundColor.defaultColor == selectedColor -> {
                            val option2 = binding.txtOption2.text.toString()
                            if(option2==quizViewModel.quizLiveData.value?.get(quizViewModel.index.value!!)?.cans) {
                                selectedAnswer.add(option2)
                            }
                        }
                        binding.cvOption3.cardBackgroundColor.defaultColor == selectedColor -> {
                            val option3 = binding.txtOption3.text.toString()
                            if(option3==quizViewModel.quizLiveData.value?.get(quizViewModel.index.value!!)?.cans) {
                                selectedAnswer.add(binding.txtOption3.text.toString())
                            }
                        }
                        binding.cvOption4.cardBackgroundColor.defaultColor == selectedColor -> {
                            val option4 = binding.txtOption4.text.toString()
                            if(option4==quizViewModel.quizLiveData.value?.get(quizViewModel.index.value!!)?.cans) {
                                selectedAnswer.add(binding.txtOption4.text.toString())
                            }
                        }
                    }
                    Log.d("TAG", "Selected Answers: $selectedAnswer")
                    quizViewModel.changeQuestion()
                    quizViewModel.changeCount()
                    Log.d("TAG", "Current count: ${quizViewModel.count}")
                    binding.txtCount.text = "${quizViewModel.count}"
                }
            }
        }
        private fun option() {
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
        }
        private fun startTimer() {
            object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.txtSeconds.text = (millisUntilFinished / 1000).toString()
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    binding.txtSeconds.text = "0"
                    quizViewModel.changeQuestion()
                }
            }.start()
        }
        private fun showLoader() {
            dialog = Dialog(this)
            dialog.setContentView(R.layout.loader_item)
            dialog.show()
        }
    }