package com.example.projeto.model

import java.io.Serializable

data class Prato(
    val foto: Int,
    val categoria: Enum<Categoria>,
    val peso: Double,
    val valor: Double,
    val nome: String,
    val descricao: String

) : Serializable
