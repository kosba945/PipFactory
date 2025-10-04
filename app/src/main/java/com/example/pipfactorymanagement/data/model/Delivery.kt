package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deliveries")
data class Delivery(
    @PrimaryKey
    val id: String,
    val invoiceId: String,
    val driverName: String,
    val driverNameAr: String, // Arabic name
    val vehicleNumber: String?,
    val deliveryCost: Double,
    val status: DeliveryStatus,
    val scheduledDate: Long,
    val actualDate: Long? = null,
    val notes: String?,
    val notesAr: String?, // Arabic notes
    val createdBy: String, // User ID
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class DeliveryStatus {
    SCHEDULED,
    IN_TRANSIT,
    DELIVERED,
    FAILED,
    CANCELLED
}

