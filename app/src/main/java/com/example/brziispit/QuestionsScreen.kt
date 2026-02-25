package com.example.brziispit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.brziispit.ui.theme.QuizViewModel

@Composable
fun QuestionScreen(quizSetId: Int, viewModel: QuizViewModel, onBack: () -> Unit) {
    // Varijable za unos teksta
    var pitanje by remember { mutableStateOf("") }
    var odgA by remember { mutableStateOf("") }
    var odgB by remember { mutableStateOf("") }
    var odgC by remember { mutableStateOf("") }
    var odgD by remember { mutableStateOf("") }
    var tocan by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text("Dodaj novo pitanje", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(value = pitanje, onValueChange = { pitanje = it }, label = { Text("Pitanje") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = odgA, onValueChange = { odgA = it }, label = { Text("Odgovor A") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = odgB, onValueChange = { odgB = it }, label = { Text("Odgovor B") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = odgC, onValueChange = { odgC = it }, label = { Text("Odgovor C") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = odgD, onValueChange = { odgD = it }, label = { Text("Odgovor D") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = tocan, onValueChange = { tocan = it }, label = { Text("Točan odgovor (A, B, C ili D)") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (pitanje.isNotBlank() && tocan.isNotBlank()) {
                    // KLJUČNI DIO: Šaljemo sve podatke u ViewModel
                    viewModel.addQuestion(
                        quizSetId = quizSetId,
                        qText = pitanje,
                        a = odgA,
                        b = odgB,
                        c = odgC,
                        d = odgD,
                        correct = tocan.uppercase() // Osiguravamo da je veliko slovo
                    )
                    onBack() // Vraća nas na početni ekran nakon spremanja
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Spremi pitanje")
        }

        TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Odustani")
        }
    }
}