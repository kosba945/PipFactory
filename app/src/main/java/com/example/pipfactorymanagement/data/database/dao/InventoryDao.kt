package com.example.pipfactorymanagement.data.database.dao

import androidx.room.*
import com.example.pipfactorymanagement.data.model.InventoryItem
import com.example.pipfactorymanagement.data.model.InventoryType
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Query("SELECT * FROM inventory_items ORDER BY name")
    fun getAllItems(): Flow<List<InventoryItem>>
    
    @Query("SELECT * FROM inventory_items WHERE type = :type ORDER BY name")
    fun getItemsByType(type: InventoryType): Flow<List<InventoryItem>>
    
    @Query("SELECT * FROM inventory_items WHERE id = :itemId")
    suspend fun getItemById(itemId: String): InventoryItem?
    
    @Query("SELECT * FROM inventory_items WHERE isLowStock = 1")
    fun getLowStockItems(): Flow<List<InventoryItem>>
    
    @Query("SELECT * FROM inventory_items WHERE name LIKE :searchQuery OR nameAr LIKE :searchQuery")
    fun searchItems(searchQuery: String): Flow<List<InventoryItem>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: InventoryItem)
    
    @Update
    suspend fun updateItem(item: InventoryItem)
    
    @Delete
    suspend fun deleteItem(item: InventoryItem)
    
    @Query("UPDATE inventory_items SET quantity = :newQuantity, updatedAt = :timestamp WHERE id = :itemId")
    suspend fun updateQuantity(itemId: String, newQuantity: Double, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE inventory_items SET isLowStock = :isLowStock WHERE id = :itemId")
    suspend fun updateLowStockStatus(itemId: String, isLowStock: Boolean)
}

