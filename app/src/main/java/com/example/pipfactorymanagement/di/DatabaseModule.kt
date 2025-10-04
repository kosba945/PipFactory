package com.example.pipfactorymanagement.di

import android.content.Context
import com.example.pipfactorymanagement.data.database.PipFactoryDatabase
import com.example.pipfactorymanagement.data.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PipFactoryDatabase {
        return PipFactoryDatabase.getDatabase(context)
    }
    
    @Provides
    fun provideUserDao(database: PipFactoryDatabase): UserDao = database.userDao()
    
    @Provides
    fun provideInventoryDao(database: PipFactoryDatabase): InventoryDao = database.inventoryDao()
    
    @Provides
    fun provideProductionDao(database: PipFactoryDatabase): ProductionDao = database.productionDao()
    
    @Provides
    fun provideProductionMaterialDao(database: PipFactoryDatabase): ProductionMaterialDao = database.productionMaterialDao()
    
    @Provides
    fun provideCustomerDao(database: PipFactoryDatabase): CustomerDao = database.customerDao()
    
    @Provides
    fun provideInvoiceDao(database: PipFactoryDatabase): InvoiceDao = database.invoiceDao()
    
    @Provides
    fun provideInvoiceItemDao(database: PipFactoryDatabase): InvoiceItemDao = database.invoiceItemDao()
    
    @Provides
    fun provideDeliveryDao(database: PipFactoryDatabase): DeliveryDao = database.deliveryDao()
    
    @Provides
    fun provideExpenseDao(database: PipFactoryDatabase): ExpenseDao = database.expenseDao()
}

