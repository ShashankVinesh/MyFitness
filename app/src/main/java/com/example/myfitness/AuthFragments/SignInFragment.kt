package com.example.myfitness.AuthFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.findNavController
import com.example.myfitness.Activities.AppMainActivity
import com.example.myfitness.R
import com.example.myfitness.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return (binding.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_loginFragment)

        }

        auth = FirebaseAuth.getInstance()

        btnSignUpClick()
    }

    private fun btnSignUpClick() {
        binding.btnSignUp.setOnClickListener {

            val email = binding.etSignInEmail.text.toString().trim()
            val password = binding.etSignInPassword.text.toString().trim()

            if (email == "" || password == "") {
                Toast.makeText(
                    requireContext(),
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(
                            requireContext(),
                            "Account created successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        openHome()

                    } else {

                        val exception = task.exception

                        if (exception is FirebaseAuthUserCollisionException) {

                            Toast.makeText(
                                requireContext(),
                                "This account already exists. Please login.",
                                Toast.LENGTH_LONG
                            ).show()

                        } else {

                            Toast.makeText(
                                requireContext(),
                                exception?.localizedMessage ?: "Something went wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

        }
    }

    private fun openHome() {
        startActivity(Intent(requireContext() , AppMainActivity::class.java))
        requireActivity().finish()
    }






}