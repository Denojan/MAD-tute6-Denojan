package com.example.expensetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.expensetracker.database.ExpenseDatabaseHelper
import android.widget.Spinner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {

    private lateinit var categorySpinner: Spinner
    private lateinit var categoryAdapter: ArrayAdapter<String>
    private lateinit var categories: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val edtproduct = view.findViewById<EditText>(R.id.edtproduct)
        val edtAmount = view.findViewById<EditText>(R.id.edtamount)
     //   val category = view.findViewById<Spinner>(R.id.category)
        val addbtn = view.findViewById<Button>(R.id.addbtn)
      val category = view.findViewById<Spinner>(R.id.category)
// Initialize spinner and adapter

       categories = listOf("", "Food", "Travel", "Study", "Others")
        categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        category.adapter = categoryAdapter



        // Set an OnItemSelectedListener to get the selected item
        category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCategory = parent.getItemAtPosition(position) as String
                // Do something with the selected category
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }


        addbtn.setOnClickListener {
            val dbHelper = ExpenseDatabaseHelper(requireContext())
            val product = edtproduct.text.toString()
            val amount = edtAmount.text.toString().toDouble()
            val category1 = category.selectedItem.toString()
                                                            //
            val result = dbHelper.insertExpense(product, amount, category1)
            if (result != -1L) {
                Toast.makeText(context, "Expense added successfully,$product,$amount,$category1", Toast.LENGTH_SHORT).show()
                edtproduct.text.clear()
                edtAmount.text.clear()

                category.setSelection(0)


                /*          // Get the FragmentManager
                         val fragmentManager = requireActivity().supportFragmentManager

          // Begin the fragment transaction
                          val fragmentTransaction = fragmentManager.beginTransaction()

          // Replace the current fragment with a new one
                          val tableFragment = TableFragment()
                          fragmentTransaction.replace(R.id.fragmentContainerView, tableFragment)



          // Commit the transaction
                          fragmentTransaction.commit()
          */
            } else {
                Toast.makeText(context, "Failed to add expense", Toast.LENGTH_SHORT).show()
            }


        }

        //  return inflater.inflate(R.layout.fragment_add, container, false)
        return view
    }



}