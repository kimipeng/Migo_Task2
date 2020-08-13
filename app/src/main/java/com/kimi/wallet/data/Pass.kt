package com.kimi.wallet.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
@Entity(tableName = "pass")
data class Pass(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo val status: Int,
    @ColumnInfo val purchaseTime: Long,
    @ColumnInfo val activationTime: Long,
    @ColumnInfo val expirationTime: Long,
    @ColumnInfo val passType: String,
    @ColumnInfo val passDuration: Int
)