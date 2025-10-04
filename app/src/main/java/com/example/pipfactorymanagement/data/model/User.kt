package com.example.pipfactorymanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val username: String,
    val email: String,
    val passwordHash: String,
    val role: UserRole,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class UserRole {
    ADMIN,
    FACTORY_MANAGER,
    SALES_MANAGER,
    PRODUCTION_SUPERVISOR,
    WAREHOUSE_STAFF
}

