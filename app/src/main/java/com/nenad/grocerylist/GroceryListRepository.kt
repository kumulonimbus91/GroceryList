package com.nenad.grocerylist

class GroceryListRepository(private val db:Database) {

    suspend fun insert(item: GroceryListItem) = db.getGroceryDao().insert(item)

    suspend fun delete(item: GroceryListItem) = db.getGroceryDao().delete(item)

    fun getAllItems() = db.getGroceryDao().getAllGrocieries()

}