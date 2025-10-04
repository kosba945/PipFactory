package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_orders")
data class ProductionOrder(
    @PrimaryKey
    val id: String,
    val productName: String,
    val productNameAr: String, // Arabic name
    val quantity: Double,
    val unit: String,
    val unitAr: String, // Arabic unit
    val totalCost: Double,
    val laborCost: Double,
    val materialCost: Double,
    val overheadCost: Double,
    val status: ProductionStatus,
    val createdBy: String, // User ID
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null
)

enum class ProductionStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}

