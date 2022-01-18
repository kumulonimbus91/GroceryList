package com.nenad.grocerylist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [GroceryListItem::class], version = 1)
abstract class Database: RoomDatabase () {
    abstract fun getGroceryDao(): GroceryListDao

    companion object {
        @Volatile
        private var instance: com.nenad.grocerylist.Database? = null
        private val LOCK = Any()

        operator fun invoke (context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context)= Room.databaseBuilder(context.applicationContext,
            com.nenad.grocerylist.Database::class.java,
            "GroceryList.db").build()



    }




}