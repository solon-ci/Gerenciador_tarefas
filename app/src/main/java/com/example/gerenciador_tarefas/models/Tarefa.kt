package com.example.gerenciador_tarefas.models

data class Tarefa(val nome: String, var descricao: String = "", var status: Boolean = false)