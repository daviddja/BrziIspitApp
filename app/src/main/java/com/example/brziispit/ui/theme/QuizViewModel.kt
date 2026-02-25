package com.example.brziispit.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brziispit.data.model.Question
import com.example.brziispit.data.model.QuizSet
import com.example.brziispit.data.repository.QuizRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class QuizViewModel(private val repository: QuizRepository) : ViewModel() {

    val allSets: StateFlow<List<QuizSet>> = repository.allSets
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addQuizSet(title: String, description: String) {
        viewModelScope.launch {
            repository.insertSet(QuizSet(title = title, description = description))
        }
    }

    fun addQuestion(quizSetId: Int, qText: String, a: String, b: String, c: String, d: String, correct: String) {
        viewModelScope.launch {
            repository.insertQuestion(
                Question(
                    setId = quizSetId,
                    text = qText,
                    optionA = a,
                    optionB = b,
                    optionC = c,
                    optionD = d,
                    correctAnswer = correct,
                    category = "Opće"
                )
            )
        }
    }

    fun getQuestionsForSet(quizSetId: Int): Flow<List<Question>> {
        return repository.getQuestionsForSet(quizSetId)
    }
}