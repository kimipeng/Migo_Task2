package com.kimi.wallet.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kimi.wallet.data.DataRepository

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
class DataViewModelFactory(val context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(DataRepository::class.java)
                .newInstance(DataRepository.getInstance(context))
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}