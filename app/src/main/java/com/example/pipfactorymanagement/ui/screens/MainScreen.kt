package com.example.pipfactorymanagement.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pipfactorymanagement.ui.components.PipFactoryBottomNavigation
import com.example.pipfactorymanagement.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    content: @Composable () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Show bottom navigation for main screens only
    val showBottomNav = currentRoute in listOf(
        Screen.Dashboard.route,
        Screen.Inventory.route,
        Screen.Production.route,
        Screen.Sales.route,
        Screen.Expenses.route
    )
    
    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                PipFactoryBottomNavigation(navController = navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            content()
        }
    }
}
