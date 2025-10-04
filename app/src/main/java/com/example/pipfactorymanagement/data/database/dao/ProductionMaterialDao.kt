package com.example.pipfactorymanagement.data.database.dao

import androidx.room.*
import com.example.pipfactorymanagement.data.model.ProductionMaterial
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductionMaterialDao {
    @Query("SELECT * FROM production_materials WHERE productionOrderId = :orderId")
    fun getMaterialsByOrderId(orderId: String): Flow<List<ProductionMaterial>>
    
    @Query("SELECT * FROM production_materials WHERE id = :materialId")
    suspend fun getMaterialById(materialId: String): ProductionMaterial?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaterial(material: ProductionMaterial)
    
    @Update
    suspend fun updateMaterial(material: ProductionMaterial)
    
    @Delete
    suspend fun deleteMaterial(material: ProductionMaterial)
    
    @Query("DELETE FROM production_materials WHERE productionOrderId = :orderId")
    suspend fun deleteMaterialsByOrderId(orderId: String)
}

