package com.example.projeto.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.projeto.R
import com.example.projeto.databinding.ActivityDetalhePratoBinding
import com.example.projeto.model.Prato

class DetalhePratoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetalhePratoBinding
    private lateinit var prato : Prato

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhePratoBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.tvNome.text = prato.nome
        binding.tvValor.text = prato.valor
        binding.tvDescricao.text = prato.descricao
        binding.tvPeso.text = prato.peso
        binding.imgFoto.setImageURI(prato.foto.toUri())
    }

    private fun setupListeners() {
        binding.btnLigar.setOnClickListener {
            Toast
                .makeText(this, "Ligar para a Cantina.", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:16994298117".toUri()
            startActivity(intent)
        }
        binding.btnMapa.setOnClickListener {
            Toast
                .makeText(this, "Encontrar a localização no mapa.", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "geo:0,0?q=Instituto+Federal+de+São+Paulo+Campus+Araraquara".toUri()
            startActivity(intent)
        }
        binding.btnSite.setOnClickListener {
            Toast
                .makeText(this, "Pedir no Site.", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "https://app.anota.ai/m/hXuQj51lw".toUri()
            startActivity(intent)

        }
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}