package com.example.projeto.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto.R
import com.example.projeto.databinding.ActivityCadastroPratoBinding
import com.example.projeto.model.Prato

class CadastroPratoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCadastroPratoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroPratoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSalvar.setOnClickListener {
            val foto = binding.imgFoto.text.toString()
            val categoria = binding.edtCategoria.text.toString()
            val peso = binding.edtPeso.text.toString()
            val valor = binding.edtValor.text.toString()
            val nome = binding.edtNome.text.toString()
            val descricao = binding.edtDesc.text.toString()

            if (foto.isNotBlank() && categoria.isNotBlank() && peso.isNotBlank() && valor.isNotBlank() && nome.isNotBlank() && descricao.isNotBlank()) {
                val novoContato = Prato(
                    foto = R.drawable.ic_launcher_foreground,
                    categoria = categoria,
                    nome = nome,
                    valor = valor,
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