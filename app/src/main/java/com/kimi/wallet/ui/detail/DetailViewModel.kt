package com.kimi.wallet.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kimi.wallet.data.DataRepository
import com.kimi.wallet.data.Pass

/**
 * Created by Kimi.Peng on 2020/8/13.
 */

class DetailViewModel (
    private val dataRepository: DataRepository,
    passId: Long
): ViewModel(){

    val pass: LiveData<Pass> = dataRepository.getPass(passId)

    fun update(
        pass: Pass,
        activateTime: Long,
        expirationTime: Long
    ) {
        val passCopy = pass.copy(
            activationTime = activateTime,
            expirationTime = expirationTime,
            isActivate = true
        )

        dataRepository.update(passCopy)
    }
}