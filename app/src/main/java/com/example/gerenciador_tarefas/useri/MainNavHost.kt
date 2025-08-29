package com.example.gerenciador_tarefas.useri


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gerenciador_tarefas.viewmodels.TarefaViewModel

@Composable
fun MainNavHost(viewModel: TarefaViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") { MainScreen(viewModel, navController) }
        composable("addTaskScreen") {
            AdicionarTarefaScreen(
                onSave = { task, descricao ->
                    viewModel.addTarefa(task, descricao)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}