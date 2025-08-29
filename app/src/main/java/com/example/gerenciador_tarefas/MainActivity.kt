package com.example.gerenciador_tarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gerenciador_tarefas.useri.MainNavHost
import com.example.gerenciador_tarefas.ui.theme.Gerenciador_tarefasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gerenciador_tarefasTheme {
                MainNavHost(viewModel())
            }
        }
    }
}