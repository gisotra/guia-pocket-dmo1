package com.example.projeto.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projeto.model.Prato

@Dao
interface PratoDao {
    @Insert
    suspend fun inserir(prato: Prato)
    @Query("SELECT * FROM prato ORDER BY nome ASC")
    suspend fun listarTodos(): List<Prato>
    @Query("SELECT * FROM prato WHERE nome LIKE :filtro ORDER BY nome ASC")
    suspend fun filtrarPorNome(filtro: String): List<Prato>
    @Delete
    suspend fun deletar(prato: Prato)
    @Update
    suspend fun atualizar(prato: Prato)
}