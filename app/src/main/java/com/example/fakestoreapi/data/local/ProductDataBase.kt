package com.example.fakestoreapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDataBase: RoomDatabase() {
    abstract fun getProductDao() : ProductDao

    companion object{
        @Volatile

        private var INSTANCE: ProductDataBase? = null

        fun getDatabase(context: Context): ProductDataBase{
            val tempInstance = INSTANCE
            if (tempInstance !=null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDataBase::class.java,
                    "products_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}