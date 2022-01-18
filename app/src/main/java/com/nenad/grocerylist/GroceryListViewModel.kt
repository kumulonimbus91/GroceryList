package com.nenad.grocerylist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryListViewModel (private val repository: GroceryListRepository):ViewModel() {




    fun insert(item: GroceryListItem) = GlobalScope.launch {

    }
}