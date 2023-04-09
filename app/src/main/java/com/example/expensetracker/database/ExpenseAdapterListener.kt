package com.example.expensetracker.database

interface ExpenseAdapterListener {
    fun updateExpenseById(expenseId: Long)
    fun deleteExpenseById(expenseId: Long)
}