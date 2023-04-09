package com.example.expensetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SummaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SummaryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        val btns1 = view.findViewById<Button>(R.id.btns1)
        val btns2= view.findViewById<Button>(R.id.btns2)
        val btns3 = view.findViewById<Button>(R.id.btns3)
        val btns4= view.findViewById<Button>(R.id.btns4)

        // Get the FragmentManager
        val fragmentManager = requireActivity().supportFragmentManager

        // Begin the fragment transaction
        val fragmentTransaction = fragmentManager.beginTransaction()

        btns1.setOnClickListener {

            // Replace the current fragment with a new one
            val t1Fragment = SummaryT1Fragment()
            fragmentTransaction.replace(R.id.fragmentContainerView, t1Fragment)

            // Commit the transaction
            fragmentTransaction.commit()
        }

        btns2.setOnClickListener {

            // Replace the current fragment with a new one
            val t2Fragment = SummaryT2Fragment()
            fragmentTransaction.replace(R.id.fragmentContainerView, t2Fragment)

            // Commit the transaction
            fragmentTransaction.commit()
        }

        btns3.setOnClickListener {

            // Replace the current fragment with a new one
            val t3Fragment = SummaryT3Fragment()
            fragmentTransaction.replace(R.id.fragmentContainerView, t3Fragment)

            // Commit the transaction
            fragmentTransaction.commit()
        }


        btns4.setOnClickListener {

            // Replace the current fragment with a new one
            val t4Fragment = SummaryT4Fragment()
            fragmentTransaction.replace(R.id.fragmentContainerView, t4Fragment)

            // Commit the transaction
            fragmentTransaction.commit()
        }




        return view
    }



}