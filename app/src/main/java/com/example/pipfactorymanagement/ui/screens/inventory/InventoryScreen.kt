package com.example.pipfactorymanagement.ui.screens.inventory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pipfactorymanagement.data.model.InventoryItem
import com.example.pipfactorymanagement.data.model.InventoryType
import com.example.pipfactorymanagement.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    navController: NavController,
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val inventoryItems by viewModel.inventoryItems.collectAsState()
    val selectedType by viewModel.selectedType.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "إدارة المخزون",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            
            IconButton(onClick = { navController.navigate(Screen.AddInventoryItem.route) }) {
                Icon(Icons.Default.Add, contentDescription = "إضافة عنصر")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Search and Filter
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { 
                searchQuery = it
                viewModel.searchItems(it)
            },
            label = { Text("البحث في المخزون") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "بحث") }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Type Filter Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InventoryType.values().forEach { type ->
                FilterChip(
                    onClick = { viewModel.filterByType(type) },
                    label = { Text(getTypeDisplayName(type)) },
                    selected = selectedType == type
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Inventory List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(inventoryItems) { item ->
                InventoryItemCard(
                    item = item,
                    onEdit = { navController.navigate(Screen.EditInventoryItem.createRoute(item.id)) },
                    onDelete = { viewModel.deleteItem(item) }
                )
            }
        }
    }
}

@Composable
fun InventoryItemCard(
    item: InventoryItem,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.nameAr,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.name,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "تعديل")
                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Default.Delete, contentDescription = "حذف")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "الكمية: ${item.quantity} ${item.unitAr}",
                    fontSize = 14.sp
                )
                Text(
                    text = "السعر: ${item.sellingPrice} جنيه",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            
            if (item.isLowStock) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "تحذير: مخزون منخفض!",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("تأكيد الحذف") },
            text = { Text("هل أنت متأكد من حذف هذا العنصر؟") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete()
                    showDeleteDialog = false
                }) {
                    Text("حذف")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("إلغاء")
                }
            }
        )
    }
}

private fun getTypeDisplayName(type: InventoryType): String {
    return when (type) {
        InventoryType.RAW_MATERIAL -> "مواد خام"
        InventoryType.FINISHED_PRODUCT -> "منتجات نهائية"
        InventoryType.TOOL -> "أدوات"
        InventoryType.EQUIPMENT -> "معدات"
    }
}

