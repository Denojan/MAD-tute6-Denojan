package com.example.expensetracker.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.expensetracker.database.entities.Expense

class ExpenseDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "expense.db"

        // Define the table and columns
        private const val TABLE_EXPENSES = "expenses"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PRODUCT = "product"
        private const val COLUMN_AMOUNT = "amount"
        private const val COLUMN_CATEGORY = "category"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Define the create table SQL statement
        val CREATE_EXPENSES_TABLE = ("CREATE TABLE $TABLE_EXPENSES("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_PRODUCT TEXT,"
                + "$COLUMN_AMOUNT REAL,"
                + "$COLUMN_CATEGORY TEXT)")
        db?.execSQL(CREATE_EXPENSES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop the table if it exists and create a new one
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EXPENSES")
        onCreate(db)
    }


    fun insertExpense(product: String, amount: Double, category: String): Long {
        // Get a writable database
        val db = this.writableDatabase

        // Create a ContentValues object with the values to insert
        val values = ContentValues().apply {
            put(COLUMN_PRODUCT, product)
            put(COLUMN_AMOUNT, amount)
            put(COLUMN_CATEGORY, category)
        }

        // Insert the values into the database and get the inserted row ID
        val rowId = db.insert(TABLE_EXPENSES, null, values)

        // Close the database
        db.close()

        // Return the inserted row ID
        return rowId
    }

    fun getAllExpenses(): List<Expense> {
        val expenses = mutableListOf<Expense>()

        // Get a readable database
        val db = this.readableDatabase

        // Define the columns to be retrieved

        val projection = arrayOf(COLUMN_ID, COLUMN_PRODUCT, COLUMN_AMOUNT, COLUMN_CATEGORY)

        // Query the table and get a cursor pointing to the result set
        val cursor = db.query(TABLE_EXPENSES, projection, null, null, null, null, null)

        // Loop through the cursor and create Expense objects for each row
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val product = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT))
            val amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))
            val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))

            // Add a null check for the category variable
            val expense = if (category != null) {
                Expense(id, product, amount, category)
            } else {
                Expense(id, product, amount, "")
            }
            expenses.add(expense)
        }

        // Close the cursor and database
        cursor.close()
        db.close()

        // Return the list of Expense objects
        return expenses
    }

    fun getExpensesByCategory(category: String): List<Expense> {
        val expenses = mutableListOf<Expense>()

        // Get a readable database
        val db = this.readableDatabase

        // Define the columns to be retrieved
        val projection = arrayOf(COLUMN_ID, COLUMN_PRODUCT, COLUMN_AMOUNT, COLUMN_CATEGORY)

        // Define the selection and selection arguments
        val selection = "$COLUMN_CATEGORY = ?"
        val selectionArgs = arrayOf(category)

        // Query the table and get a cursor pointing to the result set
        val cursor = db.query(TABLE_EXPENSES, projection, selection, selectionArgs, null, null, null)

        // Loop through the cursor and create Expense objects for each row
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val product = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT))
            val amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))
            val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))

            // Add a null check for the category variable
            val expense = if (category != null) {
                Expense(id, product, amount, category)
            } else {
                Expense(id, product, amount, "")
            }
            expenses.add(expense)
        }

        // Close the cursor and database
        cursor.close()
        db.close()

        // Return the list of Expense objects
        return expenses
    }

    fun deleteExpenseById(id: Long): Int {
        // Get a writable database
        val db = this.writableDatabase

        // Define the selection and selection arguments
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())

        // Delete the row from the table
        val deletedRows = db.delete(TABLE_EXPENSES, selection, selectionArgs)

        // Close the database
        db.close()

        // Return the number of deleted rows
        return deletedRows
    }

    fun updateExpenseById(expense: Expense): Int {
        // Get a writable database
        val db = this.writableDatabase

        // Define the values to be updated
        val values = ContentValues().apply {
            put(COLUMN_PRODUCT, expense.product)
            put(COLUMN_AMOUNT, expense.amount)
            put(COLUMN_CATEGORY, expense.category)
        }

        // Define the selection and selection arguments
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(expense.id.toString())

        // Update the row in the table
        val updatedRows = db.update(TABLE_EXPENSES, values, selection, selectionArgs)

        // Close the database
        db.close()

        // Return the number of updated rows
        return updatedRows
    }



}