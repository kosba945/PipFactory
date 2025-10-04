package com.example.pipfactorymanagement.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.example.pipfactorymanagement.data.database.dao.*
import com.example.pipfactorymanagement.data.model.*

@Database(
    entities = [
        User::class,
        InventoryItem::class,
        ProductionOrder::class,
        ProductionMaterial::class,
        Customer::class,
        Invoice::class,
        InvoiceItem::class,
        Delivery::class,
        Expense::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PipFactoryDatabase : RoomDatabase() {
    
    abstract fun userDao(): UserDao
    abstract fun inventoryDao(): InventoryDao
    abstract fun productionDao(): ProductionDao
    abstract fun productionMaterialDao(): ProductionMaterialDao
    abstract fun customerDao(): CustomerDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun invoiceItemDao(): InvoiceItemDao
    abstract fun deliveryDao(): DeliveryDao
    abstract fun expenseDao(): ExpenseDao
    
    companion object {
        @Volatile
        private var INSTANCE: PipFactoryDatabase? = null
        
        fun getDatabase(context: Context): PipFactoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PipFactoryDatabase::class.java,
                    "pip_factory_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

