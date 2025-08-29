package com.example.gerenciador_tarefas.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.gerenciador_tarefas.models.Tarefa

class TarefaViewModel : ViewModel() {
    val tarefas = mutableStateListOf<Tarefa>()

    fun addTarefa(nome: String, descricao: String) {
        tarefas.add(Tarefa(nome, descricao))
    }

    fun removeTarefa(tarefa: Tarefa) {
        tarefas.remove(tarefa)
    }

    fun changeTarefaStatus(tarefa: Tarefa, novoEstado: Boolean) {
        val index = tarefas.indexOf(tarefa)
        if (index != -1) {
            tarefas[index] = tarefas[index].copy(status = novoEstado)
        }
    }
}