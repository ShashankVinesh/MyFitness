package com.example.myfitness.AppFragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myfitness.databinding.FragmentExerciseDetailBinding
import androidx.core.content.edit
import com.example.myfitness.Data.prefKeys


class ExerciseDetailFragment : Fragment() {

    private lateinit var binding: FragmentExerciseDetailBinding


    private var countDownTimer: CountDownTimer? = null
    private var totalSeconds:Long = 0
    private var timerRunning = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseDetailBinding.inflate(inflater, container, false)
        return (binding.root)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")
        val duration = arguments?.getString("duration")
        val image: Int = arguments?.getInt("image") ?: 0

        val isTimedExercise = duration?.contains("rep", true) != true

        binding.apply {
            tvExerciseDetailName.text = name
            tvExerciseDetailInfo.text = duration
            tvExerciseDetailImg.setImageResource(image)

            btnBackDetail.setOnClickListener {
                findNavController().popBackStack()
            }

            if (isTimedExercise) {
                totalSeconds =
                    duration!!.filter { it.isDigit() }.toLongOrNull() ?: 30L

                tvExerciseDetailInfo.text = totalSeconds.toString()
                btnDone.text = "Start"

                btnDone.setOnClickListener {

                    if(!timerRunning) {
                        startTimer()
                    } else {
                        restartTimer()
                    }
                }
            } else {
                btnDone.setOnClickListener {
                    saveAsDone()
                    findNavController().popBackStack()

                }
            }
        }
    }

    private fun saveAsDone() {
        val name = arguments?.getString("name")
        name?.let {
            val pref = requireContext()
                .getSharedPreferences(prefKeys.PREF_NAME, Context.MODE_PRIVATE)

            pref.edit {
                putBoolean(it, true)
            }
        }
    }


    private fun startTimer() {

        timerRunning = true
        binding.btnDone.text = "Restart"

        countDownTimer = object : CountDownTimer(
            totalSeconds * 1000,
            1000
        ) {

            override fun onTick(millisUntilFinished: Long) {
                binding.tvExerciseDetailInfo.text =
                    (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                timerRunning = false
                binding.tvExerciseDetailInfo.text = "Well Done!"
                binding.btnDone.text = "Next"
                saveAsDone()

                binding.btnDone.setOnClickListener {
                    findNavController().popBackStack()
                }
            }


        }.start()
    }



    private fun restartTimer() {
        countDownTimer?.cancel()
        startTimer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer?.cancel()
    }
}