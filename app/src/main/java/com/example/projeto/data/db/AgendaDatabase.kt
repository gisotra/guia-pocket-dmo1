package com.example.projeto.data.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projeto.data.dao.PratoDao
import com.example.projeto.model.Prato

@Database(entities = [Prato::class], version = 1)
abstract class AgendaDatabase : RoomDatabase() {
    abstract fun pratoDao(): PratoDao
    companion object {
        @Volatile
        private var INSTANCE: AgendaDatabase? = null
        fun getInstance(context: Context): AgendaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AgendaDatabase::class.java,
                    "agenda_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}