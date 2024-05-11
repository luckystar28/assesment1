package org.d3if3067.assesment1fix.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3067.assesment1fix.model.Laundry

@Database(entities = [Laundry::class], version = 1, exportSchema = false)
abstract class LaundryDb : RoomDatabase() {

    abstract val dao:LaundryDao

    companion object {

        @Volatile
        private var INSTANCE:LaundryDb? = null

        fun getInstance(context: Context): LaundryDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LaundryDb::class.java,
                        "laundry.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}