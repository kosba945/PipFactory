package com.example.pipfactorymanagement.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pipfactorymanagement.ui.navigation.Screen

@Composable
fun PipFactoryBottomNavigation(
    navController: NavController
) {
    val navItems = listOf(
        BottomNavItem("الرئيسية", Icons.Default.Dashboard, Screen.Dashboard.route),
        BottomNavItem("المخزون", Icons.Default.Inventory, Screen.Inventory.route),
        BottomNavItem("الإنتاج", Icons.Default.Build, Screen.Production.route),
        BottomNavItem("المبيعات", Icons.Default.Receipt, Screen.Sales.route),
        BottomNavItem("المصروفات", Icons.Default.AttachMoney, Screen.Expenses.route)
    )
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

