package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey
    val id: String,
    val name: String,
    val nameAr: String, // Arabic name
    val email: String?,
    val phone: String?,
    val address: String?,
    val addressAr: String?, // Arabic address
    val creditLimit: Double = 0.0,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

