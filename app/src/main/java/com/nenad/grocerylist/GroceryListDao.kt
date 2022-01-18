package com.nenad.grocerylist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryListItem)

    @Delete
    suspend fun delete(item:GroceryListItem)

    @Query("SELECT * FROM grocery_items")
    fun getAllGrocieries () : LiveData<List<GroceryListItem>>


}