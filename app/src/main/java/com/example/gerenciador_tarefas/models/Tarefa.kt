package com.example.gerenciador_tarefas.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tarefas")
data class Tarefa(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Identificador único para cada tarefa
    val nome: String,
    var descricao: String = "",
    var status: Boolean = false
)