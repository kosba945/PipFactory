package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoices")
data class Invoice(
    @PrimaryKey
    val id: String,
    val invoiceNumber: String,
    val customerId: String,
    val customerName: String,
    val customerNameAr: String, // Arabic name
    val subtotal: Double,
    val taxAmount: Double,
    val discountAmount: Double,
    val totalAmount: Double,
    val profit: Double,
    val status: InvoiceStatus,
    val createdBy: String, // User ID
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null,
    val paidAt: Long? = null
)

enum class InvoiceStatus {
    DRAFT,
    SENT,
    PAID,
    OVERDUE,
    CANCELLED
}

