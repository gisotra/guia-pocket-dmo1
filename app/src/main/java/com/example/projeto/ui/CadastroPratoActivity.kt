package com.example.projeto.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto.R
import com.example.projeto.model.Prato

class CadastroPratoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSalvar.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val telefone = binding.edtTelefone.text.toString()
            val email = binding.edtEmail.text.toString()

            if (nome.isNotBlank() && telefone.isNotBlank() && email.isNotBlank()) {
                val novoContato = Prato(
                    foto = R.drawable.ic_launcher_foreground,
                    nome = nome,
                    telefone = telefone,
                    email = email
                )

                val resultIntent = Intent().apply {
                    putExtra("contato", novoContato)
                }

                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnVoltar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}