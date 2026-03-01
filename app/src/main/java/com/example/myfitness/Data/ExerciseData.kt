package com.example.myfitness.Data

import com.example.myfitness.R
import com.example.myfitness.dataModel.ExerciseList

object ExerciseData {
    val hiitExercises = listOf(
        ExerciseList("Burpees", "30 sec", R.drawable.burpees),
        ExerciseList("Jump Squats", "15 reps x 3 sets", R.drawable.jumpingsquat),
        ExerciseList("Mountain Climbers", "30 sec", R.drawable.mountain),
        ExerciseList("High Knees", "40 sec", R.drawable.highknees),
        ExerciseList("Jumping Jacks", "45 sec", R.drawable.jjacks),
        ExerciseList("Skater Jumps", "20 reps x 3 sets", R.drawable.skaterjumps),
        ExerciseList("Plank Jacks", "30 sec", R.drawable.plank),
        ExerciseList("Butt Kicks", "40 sec", R.drawable.buttkicks)
    )

    val yogaExercises = listOf(
        ExerciseList("Downward Dog", "60 sec", R.drawable.down_dog),
        ExerciseList("Warrior Pose", "60 sec each side", R.drawable.warriorpose),
        ExerciseList("Tree Pose", "45 sec each side", R.drawable.treepose),
        ExerciseList("Child's Pose", "60 sec", R.drawable.childpose),
        ExerciseList("Cobra Pose", "45 sec", R.drawable.cobrapose),
        ExerciseList("Cat-Cow Stretch", "60 sec", R.drawable.catcow),
        ExerciseList("Bridge Pose", "45 sec", R.drawable.bridgepose)
    )

    val absExercises = listOf(
        ExerciseList("Crunches", "20 reps x 3 sets", R.drawable.crunches),
        ExerciseList("Leg Raises", "15 reps x 3 sets", R.drawable.legraise),
        ExerciseList("Plank", "40 sec", R.drawable.plankabs),
        ExerciseList("Bicycle Crunches", "20 reps x 3 sets", R.drawable.bicycle),
        ExerciseList("Russian Twists", "30 reps", R.drawable.russintwist),
        ExerciseList("Flutter Kicks", "30 sec", R.drawable.flutterkicks),
        ExerciseList("Heel Touches", "25 reps x 3 sets", R.drawable.heeltouches)
    )
}