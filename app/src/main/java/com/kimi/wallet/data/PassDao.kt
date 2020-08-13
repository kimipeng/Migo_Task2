package com.kimi.wallet.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
interface PassDao {

    @Query("SELECT * FROM pass")
    fun getAllPass(): DataSource.Factory<Int, Pass>

    @Query("SELECT * FROM pass WHERE id=:passId")
    fun getPass(passId: Long): LiveData<Pass>

    @Insert
    fun insert(pass: Pass)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(pass: Pass)

    @Delete
    fun delete(pass: Pass)
}