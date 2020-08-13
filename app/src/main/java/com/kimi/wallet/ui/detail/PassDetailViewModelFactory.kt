package com.kimi.wallet.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kimi.wallet.data.DataRepository

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
class PassDetailViewModelFactory (val context: Context, val id: Long) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DataRepository::class.java, id.javaClass)
            .newInstance(DataRepository.getInstance(context), id)
    }
}