package com.example.gerenciador_tarefas.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import com.example.gerenciador_tarefas.models.Tarefa

@Dao
interface TarefaDao {
    @Query("SELECT * FROM tarefas")
    suspend fun getAllTarefas(): List<Tarefa>

    @Insert
    suspend fun insert(tarefa: Tarefa)

    @Delete
    suspend fun delete(tarefa: Tarefa)
}