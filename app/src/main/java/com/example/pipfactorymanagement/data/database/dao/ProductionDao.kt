package com.example.pipfactorymanagement.data.database.dao

import androidx.room.*
import com.example.pipfactorymanagement.data.model.ProductionOrder
import com.example.pipfactorymanagement.data.model.ProductionStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductionDao {
    @Query("SELECT * FROM production_orders ORDER BY createdAt DESC")
    fun getAllOrders(): Flow<List<ProductionOrder>>
    
    @Query("SELECT * FROM production_orders WHERE status = :status ORDER BY createdAt DESC")
    fun getOrdersByStatus(status: ProductionStatus): Flow<List<ProductionOrder>>
    
    @Query("SELECT * FROM production_orders WHERE id = :orderId")
    suspend fun getOrderById(orderId: String): ProductionOrder?
    
    @Query("SELECT * FROM production_orders WHERE createdBy = :userId ORDER BY createdAt DESC")
    fun getOrdersByUser(userId: String): Flow<List<ProductionOrder>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: ProductionOrder)
    
    @Update
    suspend fun updateOrder(order: ProductionOrder)
    
    @Delete
    suspend fun deleteOrder(order: ProductionOrder)
    
    @Query("UPDATE production_orders SET status = :status, updatedAt = :timestamp WHERE id = :orderId")
    suspend fun updateOrderStatus(orderId: String, status: ProductionStatus, timestamp: Long = System.currentTimeMillis())
}

