package com.avvsoft2050.ftctesttask.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avvsoft2050.ftctesttask.data.db.dao.CardDao
import com.avvsoft2050.ftctesttask.data.model.Card

@Database(entities = arrayOf(Card::class), version = 1, exportSchema = false)
abstract class CardsInfoDatabase : RoomDatabase() {

    abstract fun loadCardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: CardsInfoDatabase? = null

        fun getDatabase(context: Context): CardsInfoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardsInfoDatabase::class.java,
                    "cards_info_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}