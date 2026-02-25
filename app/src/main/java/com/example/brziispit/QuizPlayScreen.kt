package com.example.brziispit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.brziispit.ui.theme.QuizViewModel

@Composable
fun QuizPlayScreen(quizSetId: Int, viewModel: QuizViewModel, onBack: () -> Unit) {
    val questions by viewModel.getQuestionsForSet(quizSetId).collectAsState(initial = emptyList())
    var currentIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        if (questions.isEmpty()) {
            Box(contentAlignment = Alignment.Center) {
                Text("Nema pitanja. Prvo ih dodaj kroz 'Uredi'!")
            }
        } else if (finished) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("Rezultat: $score / ${questions.size}", style = MaterialTheme.typography.headlineMedium)
                Button(onClick = onBack) { Text("Natrag") }
            }
        } else {
            val q = questions[currentIndex]
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Pitanje ${currentIndex + 1}/${questions.size}")
                Text(q.text, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(20.dp))

                listOf("A" to q.optionA, "B" to q.optionB, "C" to q.optionC, "D" to q.optionD).forEach { (label, text) ->
                    Button(
                        onClick = {
                            if (label == q.correctAnswer) score++
                            if (currentIndex < questions.size - 1) currentIndex++ else finished = true
                        },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) { Text("$label: $text") }
                }
            }
        }
    }
}