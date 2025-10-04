package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey
    val id: String,
    val category: ExpenseCategory,
    val categoryAr: String, // Arabic category name
    val amount: Double,
    val description: String,
    val descriptionAr: String, // Arabic description
    val date: Long,
    val createdBy: String, // User ID
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class ExpenseCategory {
    LABOR,
    RENT,
    UTILITIES,
    MATERIALS,
    MAINTENANCE,
    TRANSPORTATION,
    OFFICE_SUPPLIES,
    OTHER
}

