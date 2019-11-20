package com.eshevtsov.android.crypto.core.feature

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface Scheduler {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
}

class MainScheduler : Scheduler {
    override val IO: CoroutineDispatcher = Dispatchers.IO
    override val Main: CoroutineDispatcher = Dispatchers.Main
}