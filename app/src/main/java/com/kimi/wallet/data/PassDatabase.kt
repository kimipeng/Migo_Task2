package com.kimi.wallet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Kimi.Peng on 2020/8/13.
 */

@Database(entities = [Pass::class], version = 1, exportSchema = false)
abstract class PassDatabase : RoomDatabase() {

    abstract fun passDao(): PassDao

    companion object {

        @Volatile
        private var instance: PassDatabase? = null

        fun getInstance(context: Context): PassDatabase{

            return instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(context, PassDatabase::class.java, "pass.db").build()
            }
        }

    }

}