package com.example.expensetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.adapters.ExpenseAdapter
import com.example.expensetracker.database.ExpenseDatabaseHelper
import androidx.recyclerview.widget.LinearLayoutManager



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TableFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TableFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_table, container, false)


        val dbHelper = ExpenseDatabaseHelper(requireContext())
        val expenses = dbHelper.getAllExpenses()
     //  Toast.makeText(context, "Expense added successfully,$expenses", Toast.LENGTH_SHORT).show()
      //  Toast.makeText(context, "Expense added successfully,${requireContext()}", Toast.LENGTH_SHORT).show()

        val recyclerView: RecyclerView = view.findViewById(R.id.rvExpense)
        val adapter = ExpenseAdapter(requireContext(), expenses)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbHelper = ExpenseDatabaseHelper(requireContext())
        val expenses = dbHelper.getAllExpenses()
        val adapter = ExpenseAdapter(requireContext(), expenses)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvExpense)
        adapter.listener = object : ExpenseAdapter.OnItemClickListener {
            override fun updateExpenseById(expenseId: Long) {
                // Handle the edit operation here
            }

            override fun deleteExpenseById(expenseId: Long) {
                // Handle the delete operation here
            }
        }
        recyclerView.adapter = adapter
    }


}
