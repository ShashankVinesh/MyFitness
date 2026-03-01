package com.example.myfitness.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfitness.Adapter.ExerciseAdapter
import com.example.myfitness.R
import com.example.myfitness.dataModel.Exercise
import com.example.myfitness.databinding.FragmentHomeBinding

class AppMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}