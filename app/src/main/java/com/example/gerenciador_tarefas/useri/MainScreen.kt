package com.example.gerenciador_tarefas.useri

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gerenciador_tarefas.models.Tarefa
import com.example.gerenciador_tarefas.viewmodels.TarefaViewModel

@Composable
fun MainScreen(viewModel: TarefaViewModel, navController: NavController) {
    var showDescriptionDialog by remember { mutableStateOf(false) }
    var selectedTaskDescription by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addTaskScreen") }) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Text(
                text = "Gerenciador de Tarefas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
            )
            LazyColumn {
                items(viewModel.tarefas) { tarefa ->
                    TarefaItem(
                        tarefa = tarefa,
                        onDelete = { viewModel.removeTarefa(tarefa) },
                        onCheckChange = { novoEstado -> viewModel.changeTarefaStatus(tarefa, novoEstado) },
                        onClick = {
                            selectedTaskDescription = tarefa.descricao
                            showDescriptionDialog = true
                        }
                    )
                }
            }
        }

        if (showDescriptionDialog) {
            AlertDialog(
                onDismissRequest = { showDescriptionDialog = false },
                title = { Text("Descrição da Tarefa") },
                text = { Text(selectedTaskDescription) },
                confirmButton = {
                    TextButton(onClick = { showDescriptionDialog = false }) {
                        Text("Fechar")
                    }
                }
            )
        }
    }
}