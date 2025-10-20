package com.example.projeto.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto.R
import com.example.projeto.adapter.PratoAdapter
import com.example.projeto.databinding.ActivityMainBinding
import com.example.projeto.model.Categoria
import com.example.projeto.model.Prato

class MainActivity : AppCompatActivity() {
//pacote ui representa as telas (activity)
    private lateinit var binding: ActivityMainBinding
    private lateinit var pratos: List<Prato>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }

    fun loadData(){
        pratos = listOf(
            Prato(
                R.drawable.merengue,
                Categoria.SOBREMESA,
                8.5,
                "merengue"
            ),
            Prato(
                R.drawable.merengue,
                Categoria.SOBREMESA,
                8.5,
                "merengue"
            ),
            Prato(
                R.drawable.merengue,
                Categoria.SOBREMESA,
                8.5,
                "merengue"
            ),
            Prato(
                R.drawable.merengue,
                Categoria.SOBREMESA,
                8.5,
                "merengue"
            ),
            Prato(
                R.drawable.merengue,
                Categoria.SOBREMESA,
                8.5,
                "merengue"
            ),
            Prato(
                R.drawable.merengue,
                Categoria.SOBREMESA,
                8.5,
                "merengue"
            )
        ).sortedBy { it.nome }
    }

    fun setupViews(){
        val adapter = PratoAdapter(this, pratos)
        binding.listViewPratos.adapter = adapter
    }

    fun setupListeners(){
        binding.listViewPratos.setOnItemClickListener { _, _, position, _ ->
            val prato = pratos[position]
            val intent = Intent(this, DetalhePratoActivity::class.java)
            intent.putExtra("nome", prato.nome)
            intent.putExtra("categoria", prato.categoria)
            intent.putExtra("peso", prato.peso)
            intent.putExtra("foto", prato.foto)
            startActivity(intent)
        }
    }
}