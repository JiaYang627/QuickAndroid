package com.jiayang.quickandroid.network

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@OptIn(InternalCoroutinesApi::class)
class StatefulCoroutine<T>(
    parentContext: CoroutineContext
) : AbstractCoroutine<T>(parentContext + CoroutineExceptionHandler { _, error ->
    error.printStackTrace()
}, initParentJob = true, active = true) {

    /**
     * 协程异常回调
     */
    private var errorBlock = ArrayList<((e: Throwable) -> Unit)>(0)

    /**
     * 执行成功
     */
    private var successBlock = ArrayList<((T) -> Unit)>(0)

    /**
     * 执行完成
     */
    private var completeBlock = ArrayList<((T?) -> Unit)>(0)

    override fun handleJobException(exception: Throwable): Boolean {
        handleCoroutineException(context, exception)
        if (exception !is CancellationException) {
            errorBlock.forEach { it.invoke(exception) }
        }
        return true
    }

    override fun onCompleted(value: T) {
        super.onCompleted(value)
        successBlock.forEach { it.invoke(value) }
        completeBlock.forEach { it.invoke(value) }
        removeCallbacks()
    }

    override fun onCancelled(cause: Throwable, handled: Boolean) {
        super.onCancelled(cause, handled)
        completeBlock.forEach { it.invoke(null) }
        removeCallbacks()
    }

    private fun removeCallbacks() {
        successBlock.clear()
        errorBlock.clear()
        completeBlock.clear()
    }

    fun onError(error: (e: Throwable) -> Unit) = apply {
        errorBlock.add(error)
    }

    fun onSuccess(success: (T) -> Unit) = apply {
        successBlock.add(success)
    }

    fun onComplete(complete: (T?) -> Unit) = apply {
        completeBlock.add(complete)
    }
}