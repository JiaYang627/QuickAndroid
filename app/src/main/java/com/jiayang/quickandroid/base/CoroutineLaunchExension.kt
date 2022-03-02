package com.iappsasia.industry_android.base

import com.jiayang.quickandroid.network.StatefulCoroutine
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @author ：张 奎
 * @date ：2020-04-29 15：07
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */

data class CoroutineCallback(

    var block: suspend () -> Unit = {},
    var onError: (Throwable) -> Unit = {}
)


fun CoroutineScope.safeLaunch(init: CoroutineCallback.() -> Unit): Job {

    val callback = CoroutineCallback().apply(init)

    return launch(CoroutineExceptionHandler { _, throwable ->
        callback.onError(throwable)
    }+ GlobalScope.coroutineContext) {
        callback.block()
    }
}

fun CoroutineScope.delayLaunch(timeMills: Long, init: CoroutineCallback.() -> Unit): Job {

    check(timeMills >= 0) {"timeMills must be positive"}
    val callback = CoroutineCallback().apply(init)

    return launch(CoroutineExceptionHandler { _, throwable ->
        callback.onError(throwable)
    }+ GlobalScope.coroutineContext) {
        delay(timeMills)
        callback.block()
    }
}

@OptIn(InternalCoroutinesApi::class)
fun <BaseResultData> CoroutineScope.launchSafety(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> BaseResultData,
): StatefulCoroutine<BaseResultData> {
    val newContext = newCoroutineContext(context)
    val coroutine = StatefulCoroutine<BaseResultData>(newContext)
    coroutine.start(start,coroutine,block)
    return coroutine
}


@Suppress("FunctionName")
fun IOScope(): CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())