package com.wdretzer.firebase.new_user

import android.content.Intent
import android.net.Uri
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
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.wdretzer.firebase.databinding.FragmentRegisterNewUserBinding


class RegisterNewUserFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentRegisterNewUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener { checkRegistrationData() }
    }

    private fun checkRegistrationData() = with(binding) {
        progressBarRegister.isVisible = true

        if ((inputEmailRegister.text?.isEmpty() == true) ||
            (inputPasswordRegister.text?.isEmpty() == true) ||
            (inputNameRegister.text?.isEmpty() == true) ||
            (inputCheckPasswordRegister.text?.isEmpty() == true)
        ) {
            Toast.makeText(requireContext(), "Há Campos não Preenchidos!", Toast.LENGTH_LONG).show()
            progressBarRegister.isVisible = false

        } else if (inputPasswordRegister.text?.toString() != inputCheckPasswordRegister.text?.toString()) {
            Toast.makeText(requireContext(), "Verifique as Senhas Digitadas!", Toast.LENGTH_LONG)
                .show()
            progressBarRegister.isVisible = false

        } else if (inputPasswordRegister.text?.length!! <= 5 || inputPasswordRegister.text?.length!! <= 5) {
            Toast.makeText(requireContext(), "As Senhas devem conter 6 Números!", Toast.LENGTH_LONG)
                .show()
            progressBarRegister.isVisible = false

        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmailRegister.text.toString())
                .matches()
        ) {
            Toast.makeText(requireContext(), "O Email digitado não é válido!", Toast.LENGTH_LONG)
                .show()
            progressBarRegister.isVisible = false

        } else if ((inputPasswordRegister.text?.toString() == inputCheckPasswordRegister.text?.toString()) &&
            (android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmailRegister.text.toString())
                .matches())
        ) {
            registerUserInFirebaseAuthentication()
        }
    }

    private fun registerUserInFirebaseAuthentication() {
        binding.btnRegister.isVisible = false
        auth.createUserWithEmailAndPassword(
            binding.inputEmailRegister.text.toString(),
            binding.inputPasswordRegister.text.toString()

        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    requireContext(),
                    "Cadastro Realizado com Sucesso!",
                    Toast.LENGTH_LONG
                ).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    authenticateLogin()
                }, 5000)

                Handler(Looper.getMainLooper()).postDelayed({
                    checkUser()
                }, 10000)

            } else {
                Toast.makeText(
                    requireContext(),
                    "Deu erro ao Fazer o Cadastro!!",
                    Toast.LENGTH_LONG
                ).show()

                binding.progressBarRegister.isVisible = false
                binding.btnRegister.isVisible = true
            }
        }
    }

    private fun authenticateLogin() {
        auth.signInWithEmailAndPassword(
            binding.inputEmailRegister.text.toString(),
            binding.inputPasswordRegister.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Login Realizado com Sucesso!", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Não foi possível realizar o Login!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun checkUser() {
        if (auth.currentUser != null) {
            auth.currentUser?.apply {
                updateProfile(userProfileChangeRequest {
                    displayName = binding.inputNameRegister.text.toString()
                    photoUri =
                        Uri.parse("https://img.freepik.com/free-vector/cute-astronaut-jumping-with-metal-hands-cartoon-vector-icon-illustration-science-technology-icon-concept-isolated-premium-vector-flat-cartoon-style_138676-4189.jpg?t=st=1650992946~exp=1650993546~hmac=5f1baeadf83b886a56d751df0bce8ebc501b4ccc661e192158703c34e2d8d019&w=740")
                }).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Astronauta: ${auth.currentUser?.displayName} incluído(a) no Perfil do Usuário!!",
                            Toast.LENGTH_LONG
                        ).show()

                        Handler(Looper.getMainLooper()).postDelayed({
                            val intent = Intent(
                                requireContext(),
                                RegisterNewUserActivity::class.java
                            )
                            startActivity(intent)
                        }, 4000)

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Deu erro ao atualizar o cadastro!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        binding.progressBarRegister.isVisible = false
        binding.btnRegister.isVisible = true
    }
}
