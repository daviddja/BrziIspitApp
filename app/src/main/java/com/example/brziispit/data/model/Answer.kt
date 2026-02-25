package com.example.brziispit.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "answers",
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = ["questionId"],
            childColumns = ["questionOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Answer(
    @PrimaryKey(autoGenerate = true) val answerId: Int = 0,
    val questionOwnerId: Int,
    val text: String,
    val isCorrect: Boolean
)