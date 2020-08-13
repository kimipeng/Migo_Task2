package com.kimi.wallet.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kimi.wallet.util.executeThread

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
class DataRepository(private val passDao: PassDao) {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(context: Context): DataRepository? {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = PassDatabase.getInstance(context)
                    instance = DataRepository(database.passDao())
                }
                return instance
            }
        }

    }


    fun getAllPass(): LiveData<PagedList<Pass>> {
        val dataSource = passDao.getAllPass()
        return LivePagedListBuilder(dataSource, 10).build()
    }

    fun getPass(id: Long): LiveData<Pass> {
        return passDao.getPass(id)
    }

    fun delete(pass: Pass){
        executeThread {
            passDao.delete(pass)
        }
    }

    fun insert(pass: Pass) {
        executeThread {
            passDao.insert(pass)
        }
    }


}
