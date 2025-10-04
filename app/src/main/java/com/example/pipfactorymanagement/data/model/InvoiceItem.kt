package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoice_items")
data class InvoiceItem(
    @PrimaryKey
    val id: String,
    val invoiceId: String,
    val inventoryItemId: String,
    val itemName: String,
    val itemNameAr: String, // Arabic name
    val quantity: Double,
    val unitPrice: Double,
    val totalPrice: Double,
    val profit: Double
)

