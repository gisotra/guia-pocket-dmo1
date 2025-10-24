package com.example.projeto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.projeto.R
import com.example.projeto.databinding.ItemPratoBinding
import com.example.projeto.model.Prato

class PratoAdapter(
    private val context: Context,
    private val lista: List<Prato>
) : ArrayAdapter<Prato>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemPratoBinding
        val itemView: View
        if (convertView == null) {
            binding = ItemPratoBinding.inflate(LayoutInflater.from(context), parent, false)
            itemView = binding.root
            itemView.tag = binding
        } else {
            itemView = convertView
            binding = itemView.tag as ItemPratoBinding
        }
        val contato = lista[position]
        binding.imgFoto.setImageResource(contato.foto)
        binding.tvNome.text = contato.nome
        return itemView
    }
}
}