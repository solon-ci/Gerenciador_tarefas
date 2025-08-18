package com.example.gerenciador_tarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gerenciador_tarefas.ui.theme.Gerenciador_tarefasTheme
import androidx.navigation.compose.*

data class Tarefa(val nome: String, var descricao: String = "", var concluida: Boolean = false) // Classe Tarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gerenciador_tarefasTheme {
                MainNavHost() // Criação do gerenciador de navegação
            }
        }
    }
}

@Composable
// Gerenciar navegador
fun MainNavHost() {
    val navController = rememberNavController() // Controlador de navegação

    val tarefas = remember { mutableStateListOf<Tarefa>() } // Lista mutável de tarefas

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") { MainScreen(tarefas, navController) }
        composable("addTaskScreen") {
            AdicionarTarefaScreen(onSave = { task, descricao ->
                // Adiciona a nova tarefa à lista
                tarefas.add(Tarefa(task, descricao))
                navController.popBackStack() // Volta à tela principal
            }, onCancel = {
                navController.popBackStack() // Volta na tela ao cancelar
            })
        }
    }
}

@Composable
fun MainScreen(tarefas: MutableList<Tarefa>, navController: NavController) {
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
            // Exibição da lista de tarefas
            LazyColumn {
                items(tarefas.size) { index ->
                    TarefaItem(
                        tarefa = tarefas[index],
                        onDelete = { tarefas.removeAt(index) },
                        onCheckChange = { novoEstado ->
                            tarefas[index].concluida = novoEstado // Atualiza o estado de conclusão
                        },
                        onClick = {
                            selectedTaskDescription = tarefas[index].descricao
                            showDescriptionDialog = true // Mostra o diálogo
                        }
                    )
                }
            }
        }

        // Diálogo para mostrar a descrição da tarefa
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

@Composable
fun TarefaItem(tarefa: Tarefa, onDelete: () -> Unit, onCheckChange: (Boolean) -> Unit, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable(onClick = onClick), // Evento de clique para mostrar a descrição
        verticalAlignment = Alignment.CenterVertically) {
        // CheckBox
        Checkbox(checked = tarefa.concluida, onCheckedChange = onCheckChange)
        // Texto da tarefa
        Text(text = tarefa.nome, modifier = Modifier.weight(1f).padding(start = 8.dp))
        // Botão de excluir
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Excluir Tarefa")
        }
    }
}

@Composable
fun AdicionarTarefaScreen(onSave: (String, String) -> Unit, onCancel: () -> Unit) {
    val titulo = remember { mutableStateOf(TextFieldValue()) }
    val descricao = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = titulo.value,
            onValueChange = { titulo.value = it },
            label = { Text("Título") }
        )
        TextField(
            value = descricao.value,
            onValueChange = { descricao.value = it },
            label = { Text("Descrição") }
        )
        Row {
            Button(onClick = {
                if (titulo.value.text.isNotEmpty()) {
                    onSave(titulo.value.text, descricao.value.text) // Salva a tarefa
                }
            }) {
                Text("Salvar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCancel) {
                Text("Descartar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Gerenciador_tarefasTheme {
        MainScreen(mutableListOf(), rememberNavController())
    }
}