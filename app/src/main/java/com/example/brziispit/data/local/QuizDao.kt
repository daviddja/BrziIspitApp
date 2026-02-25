package com.example.brziispit.data.local

import androidx.room.*
import com.example.brziispit.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_sets")
    fun getAllSets(): Flow<List<QuizSet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSet(quizSet: QuizSet): Long

    @Query("SELECT * FROM questions WHERE setId = :setId")
    fun getQuestionsForSet(setId: Int): Flow<List<Question>>

    @Insert
    suspend fun insertQuestion(question: Question): Long

    @Insert
    suspend fun insertAnswer(answer: Answer)

    // Implementacija pravila: Nasumičan redoslijed
    @Query("SELECT * FROM questions WHERE setId = :setId ORDER BY RANDOM()")
    suspend fun getRandomQuestions(setId: Int): List<Question>
}