package com.example.gerenciador_tarefas.useri

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gerenciador_tarefas.R
import com.example.gerenciador_tarefas.models.Tarefa


@Composable
fun TarefaItem(tarefa: Tarefa, onDelete: () -> Unit, onCheckChange: (Boolean) -> Unit, onClick: () -> Unit) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = tarefa.status, onCheckedChange = onCheckChange)
        Text(text = tarefa.nome, modifier = Modifier.weight(1f).padding(start = 8.dp))
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "Excluir Tarefa")
        }
    }
}