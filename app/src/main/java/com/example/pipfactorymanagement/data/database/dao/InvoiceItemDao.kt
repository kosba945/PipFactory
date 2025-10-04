package com.example.pipfactorymanagement.data.database.dao

import androidx.room.*
import com.example.pipfactorymanagement.data.model.InvoiceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceItemDao {
    @Query("SELECT * FROM invoice_items WHERE invoiceId = :invoiceId")
    fun getItemsByInvoiceId(invoiceId: String): Flow<List<InvoiceItem>>
    
    @Query("SELECT * FROM invoice_items WHERE id = :itemId")
    suspend fun getItemById(itemId: String): InvoiceItem?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: InvoiceItem)
    
    @Update
    suspend fun updateItem(item: InvoiceItem)
    
    @Delete
    suspend fun deleteItem(item: InvoiceItem)
    
    @Query("DELETE FROM invoice_items WHERE invoiceId = :invoiceId")
    suspend fun deleteItemsByInvoiceId(invoiceId: String)
}

