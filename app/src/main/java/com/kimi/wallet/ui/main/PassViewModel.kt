package com.kimi.wallet.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kimi.wallet.data.DataRepository
import com.kimi.wallet.data.Pass

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
class PassViewModel(private val dataRepository: DataRepository): ViewModel() {

    val allPass: LiveData<PagedList<Pass>> = dataRepository.getAllPass()

    fun remove(pass: Pass) {
        dataRepository.delete(pass)
    }

}