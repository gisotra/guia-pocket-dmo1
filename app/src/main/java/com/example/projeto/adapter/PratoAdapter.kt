package com.example.projeto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.projeto.R
import com.example.projeto.model.Prato

class PratoAdapter(
    private val context: Context,
    private val lista: List<Prato>
) : ArrayAdapter<Prato>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.itemprato, parent, false)
        val prato = lista[position]
        val imgFoto = itemView.findViewById<ImageView>(R.id.imgFoto)
        val tvNome = itemView.findViewById<TextView>(R.id.tvNome)
        val tvPeso = itemView.findViewById<TextView>(R.id.tvPeso)
        val tvCategoria = itemView.findViewById<TextView>(R.id.tvCategoria)

        imgFoto.setImageResource(prato.foto)
        tvNome.text = prato.nome
        tvPeso.text = "%.2f".format(prato.peso)
        tvCategoria.text = prato.categoria.name

        return itemView
    }
}