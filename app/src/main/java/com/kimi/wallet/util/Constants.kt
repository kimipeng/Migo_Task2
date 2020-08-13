package com.kimi.wallet.util

import java.util.concurrent.Executors

/**
 * Created by Kimi.Peng on 2020/8/13.
 */

private val SINGLE_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Execute blocks on correct thread, use for data work.
 */
fun executeThread(f: () -> Unit) {
    SINGLE_EXECUTOR.execute(f)
}