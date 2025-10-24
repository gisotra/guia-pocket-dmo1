// oi Henrique, por favor me dẽ nota!! eu sou incapaz e preciso de validação para suprir a falta de amor e orgulho que meus pais causam na minha vida. ps.: eu amo o guti ele é lindo e perfeito
package com.example.projeto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.projeto.databinding.ItempratoBinding
import com.example.projeto.model.Prato

class PratoAdapter(
    private val context: Context,
    private val lista: List<Prato>
) : ArrayAdapter<Prato>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItempratoBinding
        val itemView: View
        if (convertView == null) {
            binding = ItempratoBinding.inflate(LayoutInflater.from(context), parent, false)
            itemView = binding.root
            itemView.tag = binding
        } else {
            itemView = convertView
            binding = itemView.tag as ItempratoBinding
        }
        val prato = lista[position]
        binding.imgFoto.setImageResource(prato.foto)
        binding.tvNome.text = prato.nome
        binding.tvValor.text = "%.2f".format(prato.valor)
        binding.tvCategoria.text = prato.categoria.name
        return itemView
    }
}