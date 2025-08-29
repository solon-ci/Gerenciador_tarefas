package com.example.gerenciador_tarefas.useri

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

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
                    onSave(titulo.value.text, descricao.value.text)
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