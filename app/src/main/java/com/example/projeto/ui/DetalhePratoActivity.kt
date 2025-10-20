package com.example.projeto.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto.R
import com.example.projeto.databinding.ActivityDetalhePratoBinding
import com.example.projeto.model.Prato

class DetalhePratoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetalhePratoBinding
    private lateinit var prato : Prato

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhePratoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detalhe_prato)
        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData()
    {
        prato = intent.getSerializableExtra("prato", Prato::class.java) as Prato
    }

    private fun setupViews()
    {
        binding.tvNome.text = intent.getStringExtra("nome")
        binding.tvTelefone.text = intent.getStringExtra("telefone")
        binding.tvEmail.text = intent.getStringExtra("email")
        binding.imgFoto.setImageResource(intent.getIntExtra("foto", R.drawable.ic_launcher_foreground))
    }

    private fun setupListeners() {
        binding.btnLigar.setOnClickListener {
            Toast
                .makeText(this, "Ligar para o contato", Toast.LENGTH_SHORT)
                .show()
        }
        binding.btnEmail.setOnClickListener {
            Toast
                .makeText(this, "Enviar email para o contato", Toast.LENGTH_SHORT)
                .show()
        }
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}