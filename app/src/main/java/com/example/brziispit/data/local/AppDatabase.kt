package com.example.brziispit.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.brziispit.data.model.Answer
import com.example.brziispit.data.model.Question
import com.example.brziispit.data.model.QuizSet

@Database(entities = [QuizSet::class, Question::class, Answer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "brzi_ispit_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}