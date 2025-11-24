package com.example.projeto.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.projeto.R
import com.example.projeto.data.db.AgendaDatabase
import com.example.projeto.databinding.ActivityCadastroPratoBinding
import com.example.projeto.model.Prato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastroPratoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCadastroPratoBinding
    private lateinit var launcherGaleria: ActivityResultLauncher<Array<String>>
    private lateinit var uriSelecionada: String
    private lateinit var db: AgendaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroPratoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AgendaDatabase.getInstance(this)
        setupLauncher()
        setupListeners()
    }

    private fun setupLauncher() {
        launcherGaleria = registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) { uri ->
            if (uri != null) {
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

                uriSelecionada = uri.toString()
                binding.imgFoto.setImageURI(uri)
            }
        }
    }

    private fun setupListeners() {
        binding.imgFoto.setOnClickListener {
            launcherGaleria.launch(arrayOf("image/*"))
        }

        binding.btnSalvar.setOnClickListener {
            val foto = uriSelecionada.toString()
            val peso = binding.edtPeso.text.toString()
            val valor = binding.edtValor.text.toString()
            val nome = binding.edtNome.text.toString()
            val descricao = binding.edtDesc.text.toString()

            if (peso.isNotBlank()
                    && valor.isNotBlank() && nome.isNotBlank() && descricao.isNotBlank()
                ) {
                val novoPrato = Prato(
                    foto = foto,
                    peso = peso.toString(),
                    valor = valor,
                    nome = nome,
                    descricao = descricao
                )
                salvarNoBanco(novoPrato)
                } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnVoltar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
            private fun salvarNoBanco(prato: Prato) {
                lifecycleScope.launch(Dispatchers.IO) {
                    db.pratoDao().inserir(prato)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
}


