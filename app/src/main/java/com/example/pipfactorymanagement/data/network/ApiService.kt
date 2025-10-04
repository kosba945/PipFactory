package com.example.pipfactorymanagement.data.network

import com.example.pipfactorymanagement.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    // Authentication
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    
    @POST("auth/logout")
    suspend fun logout(): Response<Unit>
    
    @POST("auth/refresh")
    suspend fun refreshToken(@Body refreshRequest: RefreshTokenRequest): Response<LoginResponse>
    
    // Users
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
    
    @POST("users")
    suspend fun createUser(@Body user: User): Response<User>
    
    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User): Response<User>
    
    // Inventory
    @GET("inventory")
    suspend fun getInventoryItems(): Response<List<InventoryItem>>
    
    @POST("inventory")
    suspend fun createInventoryItem(@Body item: InventoryItem): Response<InventoryItem>
    
    @PUT("inventory/{id}")
    suspend fun updateInventoryItem(@Path("id") id: String, @Body item: InventoryItem): Response<InventoryItem>
    
    @DELETE("inventory/{id}")
    suspend fun deleteInventoryItem(@Path("id") id: String): Response<Unit>
    
    // Production
    @GET("production")
    suspend fun getProductionOrders(): Response<List<ProductionOrder>>
    
    @POST("production")
    suspend fun createProductionOrder(@Body order: ProductionOrder): Response<ProductionOrder>
    
    @PUT("production/{id}")
    suspend fun updateProductionOrder(@Path("id") id: String, @Body order: ProductionOrder): Response<ProductionOrder>
    
    // Sales
    @GET("invoices")
    suspend fun getInvoices(): Response<List<Invoice>>
    
    @POST("invoices")
    suspend fun createInvoice(@Body invoice: Invoice): Response<Invoice>
    
    @PUT("invoices/{id}")
    suspend fun updateInvoice(@Path("id") id: String, @Body invoice: Invoice): Response<Invoice>
    
    // Customers
    @GET("customers")
    suspend fun getCustomers(): Response<List<Customer>>
    
    @POST("customers")
    suspend fun createCustomer(@Body customer: Customer): Response<Customer>
    
    @PUT("customers/{id}")
    suspend fun updateCustomer(@Path("id") id: String, @Body customer: Customer): Response<Customer>
    
    // Delivery
    @GET("deliveries")
    suspend fun getDeliveries(): Response<List<Delivery>>
    
    @POST("deliveries")
    suspend fun createDelivery(@Body delivery: Delivery): Response<Delivery>
    
    @PUT("deliveries/{id}")
    suspend fun updateDelivery(@Path("id") id: String, @Body delivery: Delivery): Response<Delivery>
    
    // Expenses
    @GET("expenses")
    suspend fun getExpenses(): Response<List<Expense>>
    
    @POST("expenses")
    suspend fun createExpense(@Body expense: Expense): Response<Expense>
    
    @PUT("expenses/{id}")
    suspend fun updateExpense(@Path("id") id: String, @Body expense: Expense): Response<Expense>
    
    // Sync
    @POST("sync/upload")
    suspend fun uploadData(@Body syncData: SyncData): Response<SyncResponse>
    
    @GET("sync/download")
    suspend fun downloadData(@Query("lastSync") lastSync: Long): Response<SyncData>
}

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val refreshToken: String,
    val user: User
)

data class RefreshTokenRequest(
    val refreshToken: String
)

data class SyncData(
    val users: List<User>,
    val inventoryItems: List<InventoryItem>,
    val productionOrders: List<ProductionOrder>,
    val customers: List<Customer>,
    val invoices: List<Invoice>,
    val deliveries: List<Delivery>,
    val expenses: List<Expense>
)

data class SyncResponse(
    val success: Boolean,
    val message: String
)

