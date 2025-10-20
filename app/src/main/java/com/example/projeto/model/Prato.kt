package com.example.projeto.model

data class Prato(
    val valor: Int,
    val categoria: Enum<Categoria>,
    val peso: Double,
    val nome: String,
)
