package com.example.myfitness.AuthFragments

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myfitness.Activities.AppMainActivity
import com.example.myfitness.R
import com.example.myfitness.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signInFragment)
        }
        auth = FirebaseAuth.getInstance()

        btnLoginClick()
    }

    private fun btnLoginClick() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString().trim()
            val password = binding.etLoginPassword.text.toString().trim()

            if (email == "" || password == "") {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        openHome()
                    } else {
                        Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun openHome() {
        startActivity(Intent(requireContext() , AppMainActivity::class.java))
        requireActivity().finish()
    }


}