package com.example.pipfactorymanagement.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object Inventory : Screen("inventory")
    object Production : Screen("production")
    object Sales : Screen("sales")
    object Expenses : Screen("expenses")
    object Reports : Screen("reports")
    object Settings : Screen("settings")
    
    // Inventory sub-screens
    object AddInventoryItem : Screen("add_inventory_item")
    object EditInventoryItem : Screen("edit_inventory_item/{itemId}") {
        fun createRoute(itemId: String) = "edit_inventory_item/$itemId"
    }
    
    // Production sub-screens
    object AddProductionOrder : Screen("add_production_order")
    object EditProductionOrder : Screen("edit_production_order/{orderId}") {
        fun createRoute(orderId: String) = "edit_production_order/$orderId"
    }
    
    // Sales sub-screens
    object AddInvoice : Screen("add_invoice")
    object EditInvoice : Screen("edit_invoice/{invoiceId}") {
        fun createRoute(invoiceId: String) = "edit_invoice/$invoiceId"
    }
    object AddCustomer : Screen("add_customer")
    object EditCustomer : Screen("edit_customer/{customerId}") {
        fun createRoute(customerId: String) = "edit_customer/$customerId"
    }
    
    // Delivery sub-screens
    object AddDelivery : Screen("add_delivery")
    object EditDelivery : Screen("edit_delivery/{deliveryId}") {
        fun createRoute(deliveryId: String) = "edit_delivery/$deliveryId"
    }
    
    // Expense sub-screens
    object AddExpense : Screen("add_expense")
    object EditExpense : Screen("edit_expense/{expenseId}") {
        fun createRoute(expenseId: String) = "edit_expense/$expenseId"
    }
}

