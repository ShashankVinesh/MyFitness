package com.example.myfitness.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myfitness.R
import com.google.firebase.auth.FirebaseAuth
import kotlin.jvm.java

class AuthMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_main)

        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, AppMainActivity::class.java))
            finish()
        }

    }
}