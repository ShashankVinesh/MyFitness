package com.example.myfitness.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitness.dataModel.Exercise
import com.example.myfitness.R

class ExerciseAdapter(
    private val list: List<Exercise>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tvExerciseName)
        val type = view.findViewById<TextView>(R.id.tvExerciseType)
        val image = view.findViewById<ImageView>(R.id.imgExercise)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = list[position]

        holder.name.text = exercise.name
        holder.type.text = exercise.type
        holder.image.setImageResource(exercise.image)

        holder.itemView.setOnClickListener {

            val category = when (exercise.name) {
                "HIIT Workout" -> "HIIT"
                "Yoga Stretch" -> "Yoga"
                "Abs Training" -> "Abs"
                else -> ""
            }
            onItemClick(category)

        }

    }
}