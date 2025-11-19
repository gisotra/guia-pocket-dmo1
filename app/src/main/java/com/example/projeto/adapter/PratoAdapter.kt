package com.example.projeto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto.databinding.ItempratoBinding
import com.example.projeto.model.Prato

/*
A classe PratoAdapter é responsável por conectar os dados da lista de pratos
(List<Prato>) com os itens visuais exibidos dentro do RecyclerView.
 */


class PratoAdapter(
    private val pratos: List<Prato>,
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

    inner class ViewHolder(val binding: ItempratoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(prato: Prato) {
            binding.imgFoto.setImageResource(prato.foto)
            binding.tvNome.text = prato.nome
            binding.tvValor.text = "%.2f".format(prato.valor)
            binding.tvCategoria.text = prato.categoria.name
            binding.root.setOnClickListener { onClick(prato) }
        }
    }
}
