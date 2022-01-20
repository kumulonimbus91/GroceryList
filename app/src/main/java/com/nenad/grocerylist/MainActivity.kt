package com.nenad.grocerylist

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ItemClickInterface {

    lateinit var recyclerV: RecyclerView
    lateinit var addFab: FloatingActionButton
    lateinit var list: List<GroceryListItem>
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: GroceryListViewModel





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerV = findViewById(R.id.recycler_view_id)
        addFab = findViewById(R.id.add_btn)

        list = ArrayList<GroceryListItem> ()

        recyclerViewAdapter = RecyclerViewAdapter(list, this)

        recyclerV.layoutManager = LinearLayoutManager(this)

        recyclerV.adapter = recyclerViewAdapter

        val groceryRepository = GroceryListRepository(Database(this))

        val factory = GroceryListViewModelFactory(groceryRepository)

        viewModel = ViewModelProvider(this, factory).get(GroceryListViewModel::class.java)
        viewModel.getAllGroceryItems().observe(this, {
            recyclerViewAdapter.list = it
            recyclerViewAdapter.notifyDataSetChanged()
        })

        addFab.setOnClickListener {
         openDialog()
        }


    }

    fun openDialog() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.add_dialog)
        val cancelBtn = dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        val addBtn = dialog.findViewById<AppCompatButton>(R.id.add_btn)
        val itemId = dialog.findViewById<EditText>(R.id.edit_text_id)
        val itemPrice = dialog.findViewById<EditText>(R.id.edit_text_price)
        val itemAmount = dialog.findViewById<EditText>(R.id.tv_tc_amount)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        addBtn.setOnClickListener {
            val itemName: String = itemId.text.toString()
            val itemPr: String = itemPrice.text.toString()
            val itemAm: String = itemAmount.text.toString()

            val qty: Int = itemAm.toInt()
            val pr: Int = itemPr.toInt()
            if (itemName.isNotEmpty() && itemPr.isNotEmpty() && itemAm.isNotEmpty()) {

                val item = GroceryListItem(itemName, qty, pr)

                viewModel.insert(item)

                Toast.makeText(applicationContext, "Item inserted in the list", Toast.LENGTH_SHORT).show()

                recyclerViewAdapter.notifyDataSetChanged()
                dialog.dismiss()

            } else {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()


    }

    override fun onItemClicked(groceryListItem: GroceryListItem) {
       viewModel.delete(groceryListItem)
        recyclerViewAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show()
    }
}