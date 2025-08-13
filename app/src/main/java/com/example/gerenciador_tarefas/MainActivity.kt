package com.example.gerenciador_tarefas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gerenciador_tarefas.ui.theme.Gerenciador_tarefasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gerenciador_tarefasTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){}

@Composable
fun AdicionarTarefaScreen(onSave: (String) -> Unit, onCancel: () -> Unit) {
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
                    onSave(titulo.value.text) // Salva a tarefa
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