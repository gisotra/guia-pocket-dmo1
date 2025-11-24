package com.example.projeto.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "prato")
data class Prato(
    @PrimaryKey(autoGenerate = true)

    val id: Int = 0,
    val foto: String,
    val peso: String,
    val valor: String,
    val nome: String,
    val descricao: String
) : Serializable
