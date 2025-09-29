package com.example.gerenciador_tarefas.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciador_tarefas.data.AppDatabase
import com.example.gerenciador_tarefas.models.Tarefa
import kotlinx.coroutines.launch

class TarefaViewModel(application: Application) : ViewModel() {
    private val db = AppDatabase.getDatabase(application)
    private val tarefaDao = db.tarefaDao()
    val tarefas = mutableStateListOf<Tarefa>()

    init {
        viewModelScope.launch {
            tarefas.addAll(tarefaDao.getAllTarefas())
        }
    }

    fun addTarefa(nome: String, descricao: String) {
        val tarefa = Tarefa(nome = nome, descricao = descricao)
        viewModelScope.launch {
            tarefaDao.insert(tarefa)
            tarefas.add(tarefa)
        }
    }

    fun removeTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            tarefaDao.delete(tarefa)
            tarefas.remove(tarefa)
        }
    }

    fun changeTarefaStatus(tarefa: Tarefa, novoEstado: Boolean) {
        val index = tarefas.indexOf(tarefa)
        if (index != -1) {
            tarefas[index] = tarefas[index].copy(status = novoEstado)
            // Você pode também atualizar o banco de dados aqui, se necessário
        }
    }
}