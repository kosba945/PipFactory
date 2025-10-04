package com.example.pipfactorymanagement.ui.screens.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pipfactorymanagement.data.repository.AuthRepository
import com.example.pipfactorymanagement.data.repository.InventoryRepository
import com.example.pipfactorymanagement.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val inventoryRepository: InventoryRepository
) : ViewModel() {
    
    private val _dashboardData = MutableStateFlow(DashboardData())
    val dashboardData: StateFlow<DashboardData> = _dashboardData.asStateFlow()
    
    init {
        loadDashboardData()
    }
    
    private fun loadDashboardData() {
        viewModelScope.launch {
            combine(
                inventoryRepository.getAllItems(),
                inventoryRepository.getLowStockItems()
            ) { allItems, lowStockItems ->
                DashboardData(
                    stats = listOf(
                        StatItem("إجمالي المخزون", allItems.size.toString(), Icons.Default.Inventory, Screen.Inventory.route),
                        StatItem("منخفض المخزون", lowStockItems.size.toString(), Icons.Default.Warning, Screen.Inventory.route),
                        StatItem("طلبات الإنتاج", "12", Icons.Default.Build, Screen.Production.route),
                        StatItem("الفواتير", "8", Icons.Default.Receipt, Screen.Sales.route)
                    ),
                    quickActions = listOf(
                        QuickActionItem("إضافة مخزون", Icons.Default.Add, Screen.AddInventoryItem.route),
                        QuickActionItem("طلب إنتاج", Icons.Default.Build, Screen.AddProductionOrder.route),
                        QuickActionItem("فاتورة جديدة", Icons.Default.Receipt, Screen.AddInvoice.route),
                        QuickActionItem("مصروف جديد", Icons.Default.AttachMoney, Screen.AddExpense.route)
                    )
                )
            }.collect { data ->
                _dashboardData.value = data
            }
        }
    }
}

data class DashboardData(
    val stats: List<StatItem> = emptyList(),
    val quickActions: List<QuickActionItem> = emptyList()
)

data class StatItem(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val route: String
)

data class QuickActionItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

