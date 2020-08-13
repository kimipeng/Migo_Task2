package com.kimi.wallet.ui.add


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimi.wallet.data.DataRepository
import com.kimi.wallet.data.Pass
import com.kimi.wallet.util.Event


/**
 * Created by Kimi.Peng on 2020/8/13.
 */
class AddPassViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val _saved = MutableLiveData<Event<Boolean>>()

    val saved: LiveData<Event<Boolean>>
        get() = _saved


    fun save(passType: String, passDuration: Int) {
        if (passType.isEmpty() || passDuration == 0) {
            _saved.value = Event(false)
            return
        }

        val pass = Pass(
            purchaseTime = System.currentTimeMillis(),
            activationTime = 0,
            expirationTime = 0,
            passType = passType,
            passDuration = passDuration,
            isActivate = false
        )

        dataRepository.save(pass)
        _saved.value = Event(true)
    }

}