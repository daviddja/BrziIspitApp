package com.example.brziispit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.brziispit.data.local.AppDatabase
import com.example.brziispit.data.repository.QuizRepository
import com.example.brziispit.ui.theme.BrziispitTheme
import com.example.brziispit.ui.theme.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = QuizRepository(database.quizDao())
        val viewModel = QuizViewModel(repository)

        setContent {
            BrziispitTheme {
                val navController = rememberNavController()

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "main") {
                        // Glavni ekran
                        composable("main") {
                            MainScreen(
                                viewModel = viewModel,
                                onEditClick = { quizId -> navController.navigate("questions/$quizId") },
                                onPlayClick = { quizId -> navController.navigate("play/$quizId") }
                            )
                        }
                        // Ekran za dodavanje pitanja
                        composable(
                            route = "questions/{quizId}",
                            arguments = listOf(navArgument("quizId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val quizId = backStackEntry.arguments?.getInt("quizId") ?: 0
                            QuestionScreen(quizSetId = quizId, viewModel = viewModel, onBack = { navController.popBackStack() })
                        }
                        // Ekran za igranje kviza
                        composable(
                            route = "play/{quizId}",
                            arguments = listOf(navArgument("quizId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val quizId = backStackEntry.arguments?.getInt("quizId") ?: 0
                            QuizPlayScreen(quizSetId = quizId, viewModel = viewModel, onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: QuizViewModel, onEditClick: (Int) -> Unit, onPlayClick: (Int) -> Unit) {
    val quizSets by viewModel.allSets.collectAsState(initial = emptyList())
    var textInput by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Moji Kvizovi", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            label = { Text("Naziv novog kviza") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if(textInput.isNotBlank()) {
                    viewModel.addQuizSet(textInput, "Opis")
                    textInput = ""
                }
            },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        ) {
            Text("Spremi u bazu")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn {
            items(quizSets) { set ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = set.title, style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            TextButton(onClick = { onEditClick(set.id) }) { Text("Uredi") }
                            Button(onClick = { onPlayClick(set.id) }) { Text("Pokreni") }
                        }
                    }
                }
            }
        }
    }
}