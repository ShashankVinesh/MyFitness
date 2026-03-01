package com.example.myfitness.AppFragments

import android.R.attr.category
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitness.Adapter.ExerciseAdapter
import com.example.myfitness.Data.prefKeys
import com.example.myfitness.R
import com.example.myfitness.dataModel.Exercise
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.core.content.edit
import com.example.myfitness.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.exerciseRecycler)

        val exercises = listOf(
            Exercise("HIIT Workout", "High Intensity", R.drawable.hiit , "HIIT"),
            Exercise("Yoga Stretch", "Flexibility", R.drawable.yoga, "Yoga"),
            Exercise("Abs Training", "Core", R.drawable.abs , "Abs")
        )

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = ExerciseAdapter(exercises) { category ->
            openExerciseList(category)
        }

        checkNewDay()
        showStreak()
        animateStreakText()
    }

    private fun checkNewDay() {
        val pref = context?.getSharedPreferences(prefKeys.PREF_NAME , 0)

        val today = SimpleDateFormat(
            "yyyyMMdd",
            Locale.getDefault()
        ).format(Date())

        val lastDay = pref?.getString(prefKeys.LAST_DAY, null)

        if (today == lastDay) return

        val workedToday = pref?.getBoolean(prefKeys.WORKED_TODAY , false)

        var streak = pref?.getInt(prefKeys.STREAK, 0)

        if (workedToday == true)  {
            streak = streak!! + 1
        } else {
            streak = 0
        }

        pref?.edit {
            clear()
                .putInt(prefKeys.STREAK, streak)
                .putString(prefKeys.LAST_DAY, today)
                .putBoolean(prefKeys.WORKED_TODAY, false)
        }

    }

    private fun showStreak() {
        val pref = requireContext()
            .getSharedPreferences(prefKeys.PREF_NAME, 0)

        val streak = pref.getInt(prefKeys.STREAK, 0)

        // make sure this TextView exists in fragment_home.xml
        binding.tvStreak.text = "🔥 $streak Day Streak"
    }

    private fun openExerciseList(category: String) {
        val bundle = Bundle().apply {
            putString("category" , category)
        }

        findNavController().navigate(
            R.id.action_homeFragment_to_exerciseListFragment,
            bundle
        )
    }

    private fun animateStreakText() {

        binding.tvStreak.animate().cancel()

        binding.tvStreak.apply {

            scaleX = 0.8f
            scaleY = 0.8f
            alpha = 0.6f
            rotation = -8f

            animate()
                .scaleX(1.15f)
                .scaleY(1.15f)
                .alpha(1f)
                .rotation(8f)
                .setDuration(220)
                .withEndAction {

                    animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .rotation(0f)
                        .setDuration(180)
                        .start()
                }
                .start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}