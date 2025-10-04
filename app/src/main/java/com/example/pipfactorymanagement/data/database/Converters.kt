package com.example.pipfactorymanagement.data.database

import androidx.room.TypeConverter
import com.example.pipfactorymanagement.data.model.*

class Converters {
    
    @TypeConverter
    fun fromUserRole(role: UserRole): String = role.name
    
    @TypeConverter
    fun toUserRole(role: String): UserRole = UserRole.valueOf(role)
    
    @TypeConverter
    fun fromInventoryType(type: InventoryType): String = type.name
    
    @TypeConverter
    fun toInventoryType(type: String): InventoryType = InventoryType.valueOf(type)
    
    @TypeConverter
    fun fromProductionStatus(status: ProductionStatus): String = status.name
    
    @TypeConverter
    fun toProductionStatus(status: String): ProductionStatus = ProductionStatus.valueOf(status)
    
    @TypeConverter
    fun fromInvoiceStatus(status: InvoiceStatus): String = status.name
    
    @TypeConverter
    fun toInvoiceStatus(status: String): InvoiceStatus = InvoiceStatus.valueOf(status)
    
    @TypeConverter
    fun fromDeliveryStatus(status: DeliveryStatus): String = status.name
    
    @TypeConverter
    fun toDeliveryStatus(status: String): DeliveryStatus = DeliveryStatus.valueOf(status)
    
    @TypeConverter
    fun fromExpenseCategory(category: ExpenseCategory): String = category.name
    
    @TypeConverter
    fun toExpenseCategory(category: String): ExpenseCategory = ExpenseCategory.valueOf(category)
}

