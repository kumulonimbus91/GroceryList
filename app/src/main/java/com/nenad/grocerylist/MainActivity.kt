package com.nenad.grocerylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        viewModel.getAllGroceryItems().observe(this, Observer {
            recyclerViewAdapter.list = it
            recyclerViewAdapter.notifyDataSetChanged()
        })

        addFab.setOnClickListener {

        }


    }

    override fun onItemClicked(groceryListItem: GroceryListItem) {

    }
}