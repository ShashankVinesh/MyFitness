package com.example.myfitness.AppFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitness.Adapter.ExerciseListAdapter
import com.example.myfitness.R
import com.example.myfitness.ViewModels.ExerciseListViewModel

class ExerciseListFragment : Fragment() {

    private val viewModel: ExerciseListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.exerciseListRecycler)

        val category = arguments?.getString("category") ?: "Unknown"

        val title = view.findViewById<TextView>(R.id.categoryTitle)
        title.text = category

        recycler.layoutManager = LinearLayoutManager(context)
        Log.d("EX_LIST_DEBUG", "Category = $category")

        viewModel.loadExerciseList(category)

        viewModel.exercises.observe(viewLifecycleOwner) {list ->

            recycler.adapter = ExerciseListAdapter(list) { exerciseList ->
                val bundle = Bundle().apply {
                    putString("name" , exerciseList.name)
                    putString("duration", exerciseList.duration)
                    putInt("image", exerciseList.imageRes)
                }
                findNavController().navigate(R.id.action_exerciseListFragment_to_exerciseDetail,
                    bundle
                )
            }


        }
    }
}