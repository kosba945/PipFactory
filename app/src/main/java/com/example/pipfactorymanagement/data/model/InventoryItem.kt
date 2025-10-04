package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class InventoryItem(
    @PrimaryKey
    val id: String,
    val name: String,
    val nameAr: String, // Arabic name
    val type: InventoryType,
    val quantity: Double,
    val unit: String,
    val unitAr: String, // Arabic unit
    val costPrice: Double,
    val sellingPrice: Double,
    val minStockLevel: Double = 0.0,
    val isLowStock: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class InventoryType {
    RAW_MATERIAL,
    FINISHED_PRODUCT,
    TOOL,
    EQUIPMENT
}

