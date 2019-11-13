package com.es.android.database


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.es.android.data.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        private val LOG_TAG = AppDatabase::class.java.simpleName
        private val LOCK = Any()
        private val DATABASE_NAME = "PersonInfo"
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                synchronized(LOCK) {
                    Log.d(LOG_TAG, "Creating new database instance")
                    sInstance = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                            .build()
                }
            }
            Log.d(LOG_TAG, "Getting the database instance")
            return sInstance as AppDatabase
        }
    }
}
