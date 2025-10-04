package com.example.pipfactorymanagement.data.database.dao

import androidx.room.*
import com.example.pipfactorymanagement.data.model.Delivery
import com.example.pipfactorymanagement.data.model.DeliveryStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM deliveries ORDER BY scheduledDate DESC")
    fun getAllDeliveries(): Flow<List<Delivery>>
    
    @Query("SELECT * FROM deliveries WHERE status = :status ORDER BY scheduledDate DESC")
    fun getDeliveriesByStatus(status: DeliveryStatus): Flow<List<Delivery>>
    
    @Query("SELECT * FROM deliveries WHERE id = :deliveryId")
    suspend fun getDeliveryById(deliveryId: String): Delivery?
    
    @Query("SELECT * FROM deliveries WHERE invoiceId = :invoiceId")
    suspend fun getDeliveryByInvoiceId(invoiceId: String): Delivery?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDelivery(delivery: Delivery)
    
    @Update
    suspend fun updateDelivery(delivery: Delivery)
    
    @Delete
    suspend fun deleteDelivery(delivery: Delivery)
    
    @Query("UPDATE deliveries SET status = :status, updatedAt = :timestamp WHERE id = :deliveryId")
    suspend fun updateDeliveryStatus(deliveryId: String, status: DeliveryStatus, timestamp: Long = System.currentTimeMillis())
}

