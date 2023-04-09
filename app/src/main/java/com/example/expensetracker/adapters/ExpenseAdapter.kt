package com.example.expensetracker.adapters

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.database.ExpenseAdapterListener
import com.example.expensetracker.database.entities.Expense
import com.example.expensetracker.database.ExpenseDatabaseHelper
//In the line holder.category.selectedItem.toString() = expense.category, you are trying to assign the value of expense.category to holder.category.selectedItem. However, selectedItem is a read-only property and cannot be assigned a value directly.
//
//To set the selected item of the Spinner in your ExpenseAdapter, you can use the setSelection method. You can modify the line to:
//
//kotlin
//Copy code
//val categoryPosition = categories.indexOf(expense.category)
//holder.category.setSelection(categoryPosition)
//Here, categories is a list of strings containing all possible values for the category field. You can define this list in your ExpenseAdapter and initialize it in the constructor.
//
//Your ExpenseAdapter should look something like this:
//
//kotlin
//Copy code
class ExpenseAdapter(private val context: Context, private val expenses: List<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {

    private val categories = listOf("", "Food", "Travel", "Study", "Others")

      var listener: OnItemClickListener? = null
  //  var listener: ExpenseAdapterListener? = null

    interface OnItemClickListener {
        fun updateExpenseById(expenseId: Long)
        fun deleteExpenseById(expenseId: Long)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tablecat: TextView = view.findViewById(R.id.tablecat)
        val tablepro: TextView = view.findViewById(R.id.tablepro)
        val tableamount: TextView = view.findViewById(R.id.tableamount)
        val tableedit: ImageButton = view.findViewById(R.id.tableedit)
        val tabledel: ImageButton = view.findViewById(R.id.tabledel)



      /*    init {
              tableedit.setOnClickListener {
                  val expenseId = expenses[adapterPosition].id
                 listener?.updateExpenseById(expenseId)
              }

              tabledel.setOnClickListener {
                  val expenseId = expenses[adapterPosition].id
                  listener?.deleteExpenseById(expenseId)
              }
          }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_expense, parent, false)
        return ViewHolder(view)

    }


    override fun getItemCount(): Int {
        return expenses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expense = expenses[position]

        holder.tablecat.text = expense.category
        holder.tablepro.text = expense.product
        holder.tableamount.text = expense.amount.toString()


        holder.tableedit.setOnClickListener {
            // Call the updateExpenseById method of the listener with the expense ID
            listener?.updateExpenseById(expense.id)
        }

        holder.tabledel.setOnClickListener {
            // Call the deleteExpenseById method of the listener with the expense ID
            listener?.deleteExpenseById(expense.id)
        }

        /*   val categoryPosition = categories.indexOf(expense.category)
           if (categoryPosition != -1) {
               holder.category.setSelection(categoryPosition)
           }*/
    }
    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}


