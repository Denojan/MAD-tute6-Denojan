package com.example.expensetracker.database.entities


data class Expense(var id: Long,
                   var product: String,
                   var amount: Double,
                   var category: String)
