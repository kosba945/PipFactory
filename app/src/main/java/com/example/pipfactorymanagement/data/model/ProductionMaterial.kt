package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_materials")
data class ProductionMaterial(
    @PrimaryKey
    val id: String,
    val productionOrderId: String,
    val inventoryItemId: String,
    val quantityUsed: Double,
    val unitCost: Double,
    val totalCost: Double
)

