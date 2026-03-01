package com.example.myfitness.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfitness.Data.ExerciseData
import com.example.myfitness.dataModel.ExerciseList

class ExerciseListViewModel: ViewModel() {

    private val _exercise = MutableLiveData<List<ExerciseList>>()
    val exercises: LiveData<List<ExerciseList>> = _exercise

    fun loadExerciseList(category: String) {
        val list = when (category) {
            "HIIT" -> ExerciseData.hiitExercises
            "Yoga" -> ExerciseData.yogaExercises
            "Abs" -> ExerciseData.absExercises
            else -> emptyList()
        }
        _exercise.value = list

    }
}