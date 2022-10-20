package com.wdretzer.firebase.new_user

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.wdretzer.firebase.R

class RegisterNewUserActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val btnCadastrar: Button
        get() = findViewById(R.id.btn_cadastrar)

    private val progressBar: FrameLayout
        get() = findViewById(R.id.progress_bar_cadastro)

    private val textoNome: EditText
        get() = findViewById(R.id.input_nome_cadastro)

    private val textoEmail: EditText
        get() = findViewById(R.id.input_email_cadastro)

    private val textoSenha: EditText
        get() = findViewById(R.id.input_password_cadastro)

    private val textoSenhaConfirma: EditText
        get() = findViewById(R.id.input_check_password_cadastro)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_user)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        btnCadastrar.setOnClickListener { cadastrarDados() }
    }

    private fun cadastrarDados() {

        progressBar.isVisible = true

        if ((textoEmail.text?.isEmpty() == true) ||
            (textoSenha.text?.isEmpty() == true) ||
            (textoNome.text?.isEmpty() == true) ||
            (textoSenhaConfirma.text?.isEmpty() == true)
        ) {
            Toast.makeText(this, "Há Campos não Preenchidos!", Toast.LENGTH_LONG).show()
            progressBar.isVisible = false

        } else if (textoSenha.text?.toString() != textoSenhaConfirma.text?.toString()) {
            Toast.makeText(this, "Verifique as Senhas Digitadas!", Toast.LENGTH_LONG).show()
            progressBar.isVisible = false

        } else if (textoSenha.text.length <= 5 || textoSenhaConfirma.text.length <= 5) {
            Toast.makeText(this, "As Senhas devem conter 6 Números!", Toast.LENGTH_LONG).show()
            progressBar.isVisible = false

        } else if ((!textoEmail.text.toString().contains("@") ||
                    !textoEmail.text.toString().contains(".") ||
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(textoEmail.text.toString())
                        .matches())
        ) {
            Toast.makeText(this, "O Email digitado não é válido!", Toast.LENGTH_LONG).show()
            progressBar.isVisible = false

        } else if ((textoSenha.text?.toString() == textoSenhaConfirma.text?.toString()) &&
            (textoEmail.text.toString().contains("@") ||
                    textoEmail.text.toString().contains(".") ||
                    android.util.Patterns.EMAIL_ADDRESS.matcher(textoEmail.text.toString()).matches())
        ) {

            btnCadastrar.isVisible = false
            auth.createUserWithEmailAndPassword(
                textoEmail.text.toString(),
                textoSenha.text.toString()

            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Cadastro Realizado com Sucesso!", Toast.LENGTH_LONG)
                        .show()

                    Handler().postDelayed({
                        userAuth()
                    }, 5000)

                    Handler().postDelayed({
                        checkUser()
                    }, 10000)

                } else {
                    Toast.makeText(this, "Deu erro ao Fazer o Cadastro!!", Toast.LENGTH_LONG)
                        .show()
                    progressBar.isVisible = false
                    btnCadastrar.isVisible = true
                }
            }
        }
    }

    private fun userAuth() {
        auth.signInWithEmailAndPassword(
            textoEmail.text.toString(),
            textoSenha.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Autenticando Login...", Toast.LENGTH_LONG).show()

                    Handler().postDelayed({
//                        dialogCorrect.show(supportFragmentManager, dialogCorrect.tag)
                    }, 3000)

                } else {
                    Toast.makeText(this, "Não foi possível realizar o Login!", Toast.LENGTH_LONG)
                        .show()

                }
            }
    }

    private fun checkUser() {
        if (auth.currentUser != null) {
            auth.currentUser?.apply {
                updateProfile(userProfileChangeRequest {

//                    saveNamePerfil(textoNome.text.toString())
//                    saveId("firebase_$uid")

                    displayName = textoNome.text.toString()
                    photoUri =
                        Uri.parse("https://img.freepik.com/free-vector/cute-astronaut-jumping-with-metal-hands-cartoon-vector-icon-illustration-science-technology-icon-concept-isolated-premium-vector-flat-cartoon-style_138676-4189.jpg?t=st=1650992946~exp=1650993546~hmac=5f1baeadf83b886a56d751df0bce8ebc501b4ccc661e192158703c34e2d8d019&w=740")

                }).addOnCompleteListener {

                    if (it.isSuccessful) {
                        Toast.makeText(
                            this@RegisterNewUserActivity,
                            "Astronauta: ${auth.currentUser?.displayName} incluído(a) no Perfil do Usuário!!",
                            Toast.LENGTH_LONG
                        ).show()

                        Handler().postDelayed({
                            val intent = Intent(this@RegisterNewUserActivity, RegisterNewUserActivity::class.java)
                            startActivity(intent)
                        }, 4000)

                    } else {
                        Toast.makeText(
                            this@RegisterNewUserActivity,
                            "Deu erro ao atualizar o cadastro!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        progressBar.isVisible = false
        btnCadastrar.isVisible = true
    }

}