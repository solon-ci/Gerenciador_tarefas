package com.example.gerenciador_tarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gerenciador_tarefas.useri.MainNavHost
import com.example.gerenciador_tarefas.ui.theme.Gerenciador_tarefasTheme
import com.example.gerenciador_tarefas.viewmodels.TarefaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val viewModel = ViewModelProvider(this).get(TarefaViewModel::class.java)

        setContent {
            Gerenciador_tarefasTheme {
                MainNavHost(viewModel) // Passar o viewModel para o MainNavHost
            }
        }
    }
}