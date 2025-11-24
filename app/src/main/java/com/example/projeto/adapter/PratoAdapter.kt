package com.example.projeto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto.databinding.ItempratoBinding
import com.example.projeto.model.Prato

/*
A classe PratoAdapter é responsável por conectar os dados da lista de pratos
(List<Prato>) com os itens visuais exibidos dentro do RecyclerView.
 */


class PratoAdapter(
    private var pratos: List<Prato>,
    private val onClick: (Prato) -> Unit
) : RecyclerView.Adapter<PratoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItempratoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pratos[position])
    }

    override fun getItemCount(): Int = pratos.size

    fun updateLista(novosPratos: List<Prato>) {
        this.pratos = novosPratos
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding: ItempratoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(prato: Prato) {
            binding.imgFoto.setImageURI(prato.foto.toUri())
            binding.tvNome.text = prato.nome
            binding.tvValor.text = prato.valor
            binding.root.setOnClickListener { onClick(prato) }
        }
    }
}
