package com.example.projeto.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projeto.R
import com.example.projeto.adapter.PratoAdapter
import com.example.projeto.data.db.AgendaDatabase
import com.example.projeto.databinding.ActivityMainBinding
import com.example.projeto.model.Prato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
//pacote ui representa as telas (activity)
    private lateinit var binding: ActivityMainBinding
    private lateinit var pratos: MutableList<Prato>
    private lateinit var adapter: PratoAdapter
    private lateinit var launcherCadastro: ActivityResultLauncher<Intent>
    private lateinit var db: AgendaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Inicialização*/
        loadData()
        setupRecyclerView()
        setupLauncherCadastro()
        setupListeners()
    }

    fun loadData(){
        pratos = mutableListOf()
        db = AgendaDatabase.getInstance(this)
        lifecycleScope.launch(Dispatchers.IO) {
            pratos = db.pratoDao().listarTodos().toMutableList()
            withContext(Dispatchers.Main) {
                adapter.updateLista(pratos)
            }
        }
    }

    fun setupRecyclerView(){
        adapter = PratoAdapter(pratos) { prato ->
            val intent = Intent(this, DetalhePratoActivity::class.java)
            intent.putExtra("prato", prato);
            startActivity(intent);
        }

        binding.listViewPratos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun setupLauncherCadastro() {
        launcherCadastro = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                loadData()
            }
        }
    }

    fun setupListeners(){
        binding.btnAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroPratoActivity::class.java)
            launcherCadastro.launch(intent)
        }
        binding.edtFiltro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtro = s.toString().lowercase()
                val filtrados = pratos.filter {
                    it.nome.lowercase().contains(filtro)
                }
                adapter.updateLista(filtrados)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
    }


