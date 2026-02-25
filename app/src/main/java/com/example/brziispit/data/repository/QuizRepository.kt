package com.example.brziispit.data.repository

import com.example.brziispit.data.local.QuizDao
import com.example.brziispit.data.model.Answer
import com.example.brziispit.data.model.Question
import com.example.brziispit.data.model.QuizSet
import kotlinx.coroutines.flow.Flow

class QuizRepository(private val quizDao: QuizDao) {
    val allSets: Flow<List<QuizSet>> = quizDao.getAllSets()

    suspend fun insertSet(quizSet: QuizSet): Long {
        return quizDao.insertSet(quizSet)
    }

    suspend fun insertQuestion(question: Question): Long {
        return quizDao.insertQuestion(question)
    }

    // Ova funkcija je bila "Unresolved reference"
    fun getQuestionsForSet(quizSetId: Int): Flow<List<Question>> {
        return quizDao.getQuestionsForSet(quizSetId)
    }
}