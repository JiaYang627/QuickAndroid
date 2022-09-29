package com.jiayang.quickandroid.network

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@OptIn(InternalCoroutinesApi::class)
open class StatefulCoroutine<T>(
    parentContext: CoroutineContext,
    active :Boolean,
    errorBlock: ((e: Throwable) -> Unit)? = null,
) : AbstractCoroutine<T>(parentContext + CoroutineExceptionHandler { _, e ->
    errorBlock?.invoke(e)
    e.printStackTrace()
}, initParentJob = true, active = active)