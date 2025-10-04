package com.example.pipfactorymanagement.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pipfactorymanagement.ui.screens.auth.LoginScreen
import com.example.pipfactorymanagement.ui.screens.dashboard.DashboardScreen
import com.example.pipfactorymanagement.ui.screens.inventory.InventoryScreen
import com.example.pipfactorymanagement.ui.screens.inventory.AddInventoryItemScreen
import com.example.pipfactorymanagement.ui.screens.production.ProductionScreen
import com.example.pipfactorymanagement.ui.screens.sales.SalesScreen
import com.example.pipfactorymanagement.ui.screens.expenses.ExpensesScreen
import com.example.pipfactorymanagement.ui.screens.reports.ReportsScreen
import com.example.pipfactorymanagement.ui.screens.settings.SettingsScreen

@Composable
fun PipFactoryNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        
        composable(Screen.Inventory.route) {
            InventoryScreen(navController = navController)
        }
        
        composable(Screen.Production.route) {
            ProductionScreen(navController = navController)
        }
        
        composable(Screen.Sales.route) {
            SalesScreen(navController = navController)
        }
        
        composable(Screen.Expenses.route) {
            ExpensesScreen(navController = navController)
        }
        
        composable(Screen.Reports.route) {
            ReportsScreen(navController = navController)
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        
        composable(Screen.AddInventoryItem.route) {
            AddInventoryItemScreen(navController = navController)
        }
    }
}
