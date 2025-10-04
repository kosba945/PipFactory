package com.example.pipfactorymanagement.data.database.dao

import androidx.room.*
import com.example.pipfactorymanagement.data.model.Invoice
import com.example.pipfactorymanagement.data.model.InvoiceStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    @Query("SELECT * FROM invoices ORDER BY createdAt DESC")
    fun getAllInvoices(): Flow<List<Invoice>>
    
    @Query("SELECT * FROM invoices WHERE status = :status ORDER BY createdAt DESC")
    fun getInvoicesByStatus(status: InvoiceStatus): Flow<List<Invoice>>
    
    @Query("SELECT * FROM invoices WHERE id = :invoiceId")
    suspend fun getInvoiceById(invoiceId: String): Invoice?
    
    @Query("SELECT * FROM invoices WHERE customerId = :customerId ORDER BY createdAt DESC")
    fun getInvoicesByCustomer(customerId: String): Flow<List<Invoice>>
    
    @Query("SELECT * FROM invoices WHERE invoiceNumber LIKE :searchQuery")
    fun searchInvoices(searchQuery: String): Flow<List<Invoice>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoice(invoice: Invoice)
    
    @Update
    suspend fun updateInvoice(invoice: Invoice)
    
    @Delete
    suspend fun deleteInvoice(invoice: Invoice)
    
    @Query("UPDATE invoices SET status = :status, updatedAt = :timestamp WHERE id = :invoiceId")
    suspend fun updateInvoiceStatus(invoiceId: String, status: InvoiceStatus, timestamp: Long = System.currentTimeMillis())
}

