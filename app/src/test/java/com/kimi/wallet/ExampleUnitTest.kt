package com.kimi.wallet

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val timeDuration = 1
        val activateTime = System.currentTimeMillis()
        val expirationTime = activateTime + timeDuration * 1000 * 3600
        assertEquals(System.currentTimeMillis() + 3600000, expirationTime)
    }
}
