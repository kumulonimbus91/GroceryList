package com.nenad.grocerylist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_items")
data class GroceryListItem (
    @ColumnInfo(name = "itemName")
    var itemName: String,

    @ColumnInfo(name = "itemAmount")
    var itemAmount: Int,

    @ColumnInfo(name = "itemPrice")
    var itemPrice: Int,


) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}