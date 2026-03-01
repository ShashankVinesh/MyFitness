package com.example.myfitness.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitness.dataModel.ExerciseList
import com.example.myfitness.R


class ExerciseListAdapter(
    private val list: List<ExerciseList>,
    private val onClick: (ExerciseList) -> Unit
) : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

// ---------- ViewHolder ---------- class

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.exerciseListImage)
        val name: TextView = itemView.findViewById(R.id.exerciseListName)
        val duration: TextView = itemView.findViewById(R.id.exerciseListDuration)
    }

    // ---------- create item ----------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_list, parent, false)
        return ViewHolder(view)
    }

    // ---------- bind data ----------

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val exercise = list[position]
        holder.name.text = exercise.name
        holder.duration.text = exercise.duration
        holder.image.setImageResource(exercise.imageRes)

        holder.itemView.setOnClickListener {
            onClick(exercise)
        }

        val pref = holder.itemView.context
            .getSharedPreferences("workout", Context.MODE_PRIVATE)

        val doneIcon = holder.itemView.findViewById<ImageView>(R.id.doneIcon)

        if (pref.getBoolean(exercise.name, false)) {
            doneIcon.visibility = View.VISIBLE
        } else {
            doneIcon.visibility = View.GONE
        }

        Log.d("CHECK_DEBUG", exercise.name +
                " = " + pref.getBoolean(exercise.name, false))

    }
      override fun getItemCount(): Int = list.size
}


