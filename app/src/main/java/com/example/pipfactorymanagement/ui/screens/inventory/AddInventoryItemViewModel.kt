package com.example.pipfactorymanagement.ui.screens.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pipfactorymanagement.data.model.InventoryItem
import com.example.pipfactorymanagement.data.model.InventoryType
import com.example.pipfactorymanagement.data.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddInventoryItemViewModel @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {
    
    private val _addState = MutableStateFlow<AddInventoryItemState>(AddInventoryItemState.Idle)
    val addState: StateFlow<AddInventoryItemState> = _addState.asStateFlow()
    
    fun addItem(
        nameAr: String,
        name: String,
        type: InventoryType,
        quantity: Double,
        unitAr: String,
        unit: String,
        costPrice: Double,
        sellingPrice: Double,
        minStockLevel: Double
    ) {
        viewModelScope.launch {
            _addState.value = AddInventoryItemState.Loading
            
            val item = InventoryItem(
                id = UUID.randomUUID().toString(),
                name = name,
                nameAr = nameAr,
                type = type,
                quantity = quantity,
                unit = unit,
                unitAr = unitAr,
                costPrice = costPrice,
                sellingPrice = sellingPrice,
                minStockLevel = minStockLevel,
                isLowStock = quantity <= minStockLevel
            )
            
            inventoryRepository.insertItem(item)
                .onSuccess {
                    _addState.value = AddInventoryItemState.Success
                }
                .onFailure { exception ->
                    _addState.value = AddInventoryItemState.Error(exception.message ?: "فشل في إضافة العنصر")
                }
        }
    }
}

sealed class AddInventoryItemState {
    object Idle : AddInventoryItemState()
    object Loading : AddInventoryItemState()
    object Success : AddInventoryItemState()
    data class Error(val message: String) : AddInventoryItemState()
}

