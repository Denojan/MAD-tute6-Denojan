package com.example.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val imgAdd:ImageButton = findViewById(R.id.add)
        val imgTable:ImageButton = findViewById(R.id.table)
        val imgSum:ImageButton = findViewById(R.id.summary)
        val fragmentAdd = AddFragment()
        val fragmentTable = TableFragment()
        val fragmentSummary = SummaryFragment()
        imgAdd.setOnClickListener {
            imgAdd.setImageResource(R.drawable.add_selected)
            imgTable.setImageResource(R.drawable.table)
            imgSum.setImageResource(R.drawable.summary)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragmentAdd)
                commit()
            }
        }
        imgTable.setOnClickListener {


            imgAdd.setImageResource(R.drawable.add)
            imgTable.setImageResource(R.drawable.table_selected)
            imgSum.setImageResource(R.drawable.summary)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragmentTable)

                commit()
            }
        }
        imgSum.setOnClickListener {
            imgAdd.setImageResource(R.drawable.add)
            imgTable.setImageResource(R.drawable.table)
            imgSum.setImageResource(R.drawable.summary_select)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragmentSummary)
                commit()
            }
        }
    }
}