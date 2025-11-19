package com.example.projeto.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projeto.R
import com.example.projeto.adapter.PratoAdapter
import com.example.projeto.databinding.ActivityMainBinding
import com.example.projeto.model.Categoria
import com.example.projeto.model.Prato

class MainActivity : AppCompatActivity() {
//pacote ui representa as telas (activity)
    private lateinit var binding: ActivityMainBinding
    private lateinit var pratos: MutableList<Prato>
    private lateinit var adapter: PratoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Inicialização*/
        loadData()
        setupRecyclerView()
        setupListeners()
    }

    fun loadData(){
        pratos = mutableListOf(
            Prato(
                R.drawable.merengue,
                Categoria.SOBREMESA,
                0.5,
                15.50,
                getString(R.string.sbm1_nome),
                getString(R.string.sbm1_descricao)
            ),
            Prato(
                R.drawable.frango,
                Categoria.ENTRADA,
                1.1,
                22.50,
                getString(R.string.ent1_nome),
                getString(R.string.ent1_descricao)
            ),
            Prato(
                R.drawable.soda,
                Categoria.BEBIDA,
                0.2,
                15.00,
                getString(R.string.bbd1_nome),
                getString(R.string.bbd1_descricao)
            ),
            Prato(
                R.drawable.fritas,
                Categoria.PORCAO,
                0.4,
                11.00,
                getString(R.string.prc1_nome),
                getString(R.string.prc1_descricao)
            ),
            Prato(
                R.drawable.moussechoccy,
                Categoria.SOBREMESA,
                0.3,
                12.50,
                getString(R.string.sbm3_nome),
                getString(R.string.sbm3_descricao)
            ),
            Prato(
                R.drawable.lilcheesebread,
                Categoria.APERITIVO,
                0.2,
                5.50,
                getString(R.string.apt5_nome),
                getString(R.string.apt5_descricao)
            ),
            Prato(
                R.drawable.orangejuice,
                Categoria.BEBIDA,
                0.3,
                10.00,
                getString(R.string.bbd2_nome),
                getString(R.string.bbd2_descricao)
            )
        ).sortedBy { it.nome }.toMutableList()
    }

    fun setupRecyclerView(){
        adapter = PratoAdapter(pratos) { prato ->
            val intent = Intent(this, DetalhePratoActivity::class.java)
            intent.putExtra("prato", prato);
            startActivity(intent);
        }

        binding.listViewPratos.layoutManager = LinearLayoutManager(this)
        binding.listViewPratos.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        binding.listViewPratos.adapter = adapter
    }

    fun setupListeners(){
        /*binding.btnAdicionar.setOnItemClickListener {
            val intent = Intent(this, CadastroPratoActivity::class.java)
            startActivity(intent)
        }*/
    }
}


/*
private fun setupListeners() {
binding.btnAdicionar.setOnClickListener {
val intent = Intent(this, CadastroContatoActivity::class.java)
startActivity(intent)
}
}
}

*/