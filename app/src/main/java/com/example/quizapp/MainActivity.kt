    package com.example.quizapp

    import android.annotation.SuppressLint
    import android.app.Dialog
    import android.content.Intent
    import android.os.Bundle
    import android.os.CountDownTimer
    import android.util.Log
    import androidx.activity.viewModels
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import com.example.quizapp.databinding.ActivityMainBinding
    import com.example.quizapp.view.activity.ResultActivity
    import com.example.quizapp.viewModel.QuizViewModel

    @Suppress("DEPRECATION")
    class MainActivity : AppCompatActivity() {
        private var countDownTimer: CountDownTimer?=null
        private lateinit var dialog: Dialog
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
            startTimer()
            quizViewModel.getQuizData()
            showLoader()

            quizViewModel.quizLiveData.observe(this) {
                binding.txtQuestions.text = it?.get(0)?.qa
                binding.txtOption1.text = it[0].opList?.get(0)
                binding.txtOption2.text = it[0].opList?.get(1)
                binding.txtOption3.text = it[0].opList?.get(2)
                binding.txtOption4.text = it[0].opList?.get(3)
                dialog.dismiss()
                countDownTimer?.start()
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
                if (quizViewModel.index.value!!+1 == 10) {
                    binding.btnNext.text = "SUBMIT"
                    binding.btnNext.setOnClickListener {
                        Log.d("TAG", "onCreate: ${quizViewModel.selectedAnswer}")
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("correctAnswer", quizViewModel.selectedAnswer.size)
                        Log.d("TAG", "Submit List Size=================================  ${quizViewModel.selectedAnswer.size}")
                        startActivity(intent)
                    }
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
                if(binding.cvOption1.cardBackgroundColor.defaultColor == unselectedColor &&
                    binding.cvOption2.cardBackgroundColor.defaultColor == unselectedColor &&
                    binding.cvOption3.cardBackgroundColor.defaultColor == unselectedColor &&
                    binding.cvOption4.cardBackgroundColor.defaultColor == unselectedColor) {

                } else {
                    quizViewModel.changeQuestion()
                    countDownTimer?.cancel()
                    countDownTimer?.start()
                    binding.txtCount.text = "${quizViewModel.index.value!!+1}"
                    quizViewModel.selectedOption =null
                    binding.btnNext.setBackgroundColor(selectedColor)
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

                quizViewModel.selectedOption = binding.txtOption1.text.toString()
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
                quizViewModel.selectedOption = binding.txtOption2.text.toString()
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
                quizViewModel.selectedOption = binding.txtOption3.text.toString()
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
                quizViewModel.selectedOption = binding.txtOption4.text.toString()
            }
        }
        private fun startTimer() {
            countDownTimer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.txtSeconds.text = (millisUntilFinished / 1000).toString()
                }
                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    quizViewModel.selectedOption=null
                    quizViewModel.changeQuestion()
                    countDownTimer?.cancel()
                    countDownTimer?.start()
                    binding.txtCount.text = "${quizViewModel.index.value!!+1}"
                }
            }
        }
        private fun showLoader() {
            dialog = Dialog(this)
            dialog.setContentView(R.layout.loader_item)
            dialog.show()
        }
    }