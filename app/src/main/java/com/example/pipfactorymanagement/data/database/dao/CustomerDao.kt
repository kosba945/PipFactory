package com.example.pipfactorymanagement.data.database.dao

import androidx.room.*
import com.example.pipfactorymanagement.data.model.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers WHERE isActive = 1 ORDER BY name")
    fun getAllActiveCustomers(): Flow<List<Customer>>
    
    @Query("SELECT * FROM customers WHERE id = :customerId")
    suspend fun getCustomerById(customerId: String): Customer?
    
    @Query("SELECT * FROM customers WHERE name LIKE :searchQuery OR nameAr LIKE :searchQuery")
    fun searchCustomers(searchQuery: String): Flow<List<Customer>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)
    
    @Update
    suspend fun updateCustomer(customer: Customer)
    
    @Delete
    suspend fun deleteCustomer(customer: Customer)
    
    @Query("UPDATE customers SET isActive = 0 WHERE id = :customerId")
    suspend fun deactivateCustomer(customerId: String)
}

