package com.example.pipfactorymanagement.ui.screens.inventory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pipfactorymanagement.data.model.InventoryType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInventoryItemScreen(
    navController: NavController,
    viewModel: AddInventoryItemViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var nameAr by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(InventoryType.RAW_MATERIAL) }
    var quantity by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var unitAr by remember { mutableStateOf("") }
    var costPrice by remember { mutableStateOf("") }
    var sellingPrice by remember { mutableStateOf("") }
    var minStockLevel by remember { mutableStateOf("") }
    
    val addState by viewModel.addState.collectAsState()
    
    LaunchedEffect(addState) {
        when (addState) {
            is AddInventoryItemState.Success -> {
                navController.popBackStack()
            }
            else -> {}
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "رجوع")
            }
            
            Text(
                text = "إضافة عنصر مخزون",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Form
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = nameAr,
                    onValueChange = { nameAr = it },
                    label = { Text("الاسم بالعربية") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("الاسم بالإنجليزية") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Type selection - simplified approach
                var expanded by remember { mutableStateOf(false) }
                Box {
                    OutlinedTextField(
                        value = getTypeDisplayName(type),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("النوع") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        InventoryType.values().forEach { typeOption ->
                            DropdownMenuItem(
                                text = { Text(getTypeDisplayName(typeOption)) },
                                onClick = {
                                    type = typeOption
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = quantity,
                        onValueChange = { quantity = it },
                        label = { Text("الكمية") },
                        modifier = Modifier.weight(1f),
                        keyboardType = KeyboardType.Decimal
                    )
                    
                    OutlinedTextField(
                        value = unitAr,
                        onValueChange = { unitAr = it },
                        label = { Text("الوحدة") },
                        modifier = Modifier.weight(1f)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = unit,
                    onValueChange = { unit = it },
                    label = { Text("الوحدة بالإنجليزية") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = costPrice,
                        onValueChange = { costPrice = it },
                        label = { Text("سعر التكلفة") },
                        modifier = Modifier.weight(1f),
                        keyboardType = KeyboardType.Decimal
                    )
                    
                    OutlinedTextField(
                        value = sellingPrice,
                        onValueChange = { sellingPrice = it },
                        label = { Text("سعر البيع") },
                        modifier = Modifier.weight(1f),
                        keyboardType = KeyboardType.Decimal
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = minStockLevel,
                    onValueChange = { minStockLevel = it },
                    label = { Text("الحد الأدنى للمخزون") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Decimal
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = {
                        if (nameAr.isNotEmpty() && name.isNotEmpty() && quantity.isNotEmpty() && 
                            unitAr.isNotEmpty() && unit.isNotEmpty() && costPrice.isNotEmpty() && 
                            sellingPrice.isNotEmpty()) {
                            viewModel.addItem(
                                nameAr = nameAr,
                                name = name,
                                type = type,
                                quantity = quantity.toDoubleOrNull() ?: 0.0,
                                unitAr = unitAr,
                                unit = unit,
                                costPrice = costPrice.toDoubleOrNull() ?: 0.0,
                                sellingPrice = sellingPrice.toDoubleOrNull() ?: 0.0,
                                minStockLevel = minStockLevel.toDoubleOrNull() ?: 0.0
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = addState !is AddInventoryItemState.Loading
                ) {
                    if (addState is AddInventoryItemState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("إضافة العنصر")
                    }
                }
                
                when (addState) {
                    is AddInventoryItemState.Error -> {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = addState.message,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    else -> {}
                }
            }
        }
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