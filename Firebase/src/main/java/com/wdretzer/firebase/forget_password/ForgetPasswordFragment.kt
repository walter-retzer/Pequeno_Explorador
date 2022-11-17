package com.wdretzer.firebase.forget_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.wdretzer.firebase.databinding.FragmentForgetPasswordBinding


class ForgetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgetPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.forgotPasswordBtnContinue.setOnClickListener {
            if (binding.inputEmailToSendForgotPassword.text?.isNotEmpty() == true) {
                val email = binding.inputEmailToSendForgotPassword.text.toString()
                resetPasswordFirebase(email)
            } else {
                Toast.makeText(context, "Digite um e-mail.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun resetPasswordFirebase(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    context,
                    "Link com o resete da sua senha foi enviado ao email digitado!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "Falha em enviar o link de resete da senha!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}