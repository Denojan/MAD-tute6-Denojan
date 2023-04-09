package com.example.expensetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.adapters.ExpenseAdapter
import com.example.expensetracker.database.ExpenseDatabaseHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SummaryT1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SummaryT1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_summary_t1, container, false)
        val btnback = view.findViewById<ImageButton>(R.id.btnback)
        val dbHelper = ExpenseDatabaseHelper(requireContext())
        val expenses = dbHelper.getExpensesByCategory("Food")
        //  Toast.makeText(context, "Expense added successfully,$expenses", Toast.LENGTH_SHORT).show()
        //  Toast.makeText(context, "Expense added successfully,${requireContext()}", Toast.LENGTH_SHORT).show()

        val recyclerView: RecyclerView = view.findViewById(R.id.rvt1)
        val adapter = ExpenseAdapter(requireContext(), expenses)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        btnback.setOnClickListener{
            // Get the FragmentManager
            val fragmentManager = requireActivity().supportFragmentManager

            // Begin the fragment transaction
            val fragmentTransaction = fragmentManager.beginTransaction()


            // Replace the current fragment with a new one
            val summaryFragment = SummaryFragment()
            fragmentTransaction.replace(R.id.fragmentContainerView, summaryFragment)

            // Commit the transaction
            fragmentTransaction.commit()
        }

        return view
    }


}