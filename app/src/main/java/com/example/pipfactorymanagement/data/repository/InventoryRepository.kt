package com.example.pipfactorymanagement.data.repository

import com.example.pipfactorymanagement.data.database.dao.InventoryDao
import com.example.pipfactorymanagement.data.model.InventoryItem
import com.example.pipfactorymanagement.data.model.InventoryType
import com.example.pipfactorymanagement.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InventoryRepository @Inject constructor(
    private val inventoryDao: InventoryDao,
    private val apiService: ApiService
) {
    
    fun getAllItems(): Flow<List<InventoryItem>> = inventoryDao.getAllItems()
    
    fun getItemsByType(type: InventoryType): Flow<List<InventoryItem>> = inventoryDao.getItemsByType(type)
    
    fun getLowStockItems(): Flow<List<InventoryItem>> = inventoryDao.getLowStockItems()
    
    fun searchItems(query: String): Flow<List<InventoryItem>> = inventoryDao.searchItems("%$query%")
    
    suspend fun getItemById(id: String): InventoryItem? = inventoryDao.getItemById(id)
    
    suspend fun insertItem(item: InventoryItem): Result<Unit> {
        return try {
            inventoryDao.insertItem(item)
            // Sync with server
            syncItemToServer(item)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateItem(item: InventoryItem): Result<Unit> {
        return try {
            inventoryDao.updateItem(item)
            // Sync with server
            syncItemToServer(item)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deleteItem(item: InventoryItem): Result<Unit> {
        return try {
            inventoryDao.deleteItem(item)
            // Sync with server
            syncDeleteItemToServer(item.id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateQuantity(itemId: String, newQuantity: Double): Result<Unit> {
        return try {
            inventoryDao.updateQuantity(itemId, newQuantity)
            // Check for low stock
            val item = inventoryDao.getItemById(itemId)
            item?.let {
                val isLowStock = newQuantity <= it.minStockLevel
                inventoryDao.updateLowStockStatus(itemId, isLowStock)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private suspend fun syncItemToServer(item: InventoryItem) {
        try {
            apiService.createInventoryItem(item)
        } catch (e: Exception) {
            // Handle sync error - could store for later sync
        }
    }
    
    private suspend fun syncDeleteItemToServer(itemId: String) {
        try {
            apiService.deleteInventoryItem(itemId)
        } catch (e: Exception) {
            // Handle sync error
        }
    }
}

