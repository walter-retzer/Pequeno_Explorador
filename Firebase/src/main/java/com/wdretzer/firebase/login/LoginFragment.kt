package com.wdretzer.firebase.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.wdretzer.firebase.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener { checkLogin() }
        binding.btnCadastrar.setOnClickListener {

        }

    }

    private fun checkLogin() = with(binding) {
        progressBarLogin.isVisible = true

        if ((inputEmailLogin.text?.isEmpty() == true) || (inputPasswordLogin.text?.isEmpty() == true)) {
            Toast.makeText(requireContext(), "Há Campos não Preenchidos!", Toast.LENGTH_LONG).show()
            progressBarLogin.isVisible = false

        } else if (inputPasswordLogin.text?.length!! <= 5 && inputEmailLogin.text?.isNotEmpty() == true) {
            Toast.makeText(requireContext(), "A Senha deve conter 6 Números!", Toast.LENGTH_LONG)
                .show()
            progressBarLogin.isVisible = false

        } else if ((!inputEmailLogin.text.toString().contains("@") ||
                    !inputEmailLogin.text.toString().contains(".") ||
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmailLogin.text.toString())
                        .matches())
        ) {
            Toast.makeText(requireContext(), "O Email digitado não é válido!", Toast.LENGTH_LONG)
                .show()
            progressBarLogin.isVisible = false

        } else {
            checkLoginUser()
        }
    }


    private fun checkLoginUser() = with(binding) {
        auth.signInWithEmailAndPassword(
            inputEmailLogin.text.toString(),
            inputEmailLogin.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    Toast.makeText(requireContext(), "Autenticando Login...", Toast.LENGTH_LONG)
                        .show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        progressBarLogin.isVisible = false
                        btnLogin.isVisible = true
                    }, 5000)

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Login não realizado! Check seu e-mail e senha!",
                        Toast.LENGTH_LONG
                    ).show()
                    progressBarLogin.isVisible = false
                    btnLogin.isVisible = true
                }
            }
    }
}
