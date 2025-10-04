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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {
    
    private val _inventoryItems = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventoryItems: StateFlow<List<InventoryItem>> = _inventoryItems.asStateFlow()
    
    private val _selectedType = MutableStateFlow<InventoryType?>(null)
    val selectedType: StateFlow<InventoryType?> = _selectedType.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    
    init {
        loadInventoryItems()
    }
    
    private fun loadInventoryItems() {
        viewModelScope.launch {
            combine(
                inventoryRepository.getAllItems(),
                _selectedType,
                _searchQuery
            ) { allItems, selectedType, searchQuery ->
                var filteredItems = allItems
                
                // Filter by type
                selectedType?.let { type ->
                    filteredItems = filteredItems.filter { it.type == type }
                }
                
                // Filter by search query
                if (searchQuery.isNotEmpty()) {
                    filteredItems = filteredItems.filter { 
                        it.name.contains(searchQuery, ignoreCase = true) ||
                        it.nameAr.contains(searchQuery, ignoreCase = true)
                    }
                }
                
                filteredItems
            }.collect { items ->
                _inventoryItems.value = items
            }
        }
    }
    
    fun filterByType(type: InventoryType?) {
        _selectedType.value = if (_selectedType.value == type) null else type
    }
    
    fun searchItems(query: String) {
        _searchQuery.value = query
    }
    
    fun deleteItem(item: InventoryItem) {
        viewModelScope.launch {
            inventoryRepository.deleteItem(item)
        }
    }
}

