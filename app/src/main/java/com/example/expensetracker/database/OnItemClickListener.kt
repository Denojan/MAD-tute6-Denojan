package com.example.expensetracker.database

interface OnItemClickListener {
    fun updateExpenseById(expenseId: Long)
    fun deleteExpenseById(expenseId: Long)
}