package com.jiayang.quickandroid.network

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.resumeCancellableWith
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.createCoroutineUnintercepted
import kotlin.coroutines.intrinsics.intercepted

@OptIn(InternalCoroutinesApi::class)
class LazyStatefulCoroutine<T>(
    parentContext: CoroutineContext,
    block: suspend CoroutineScope.() -> T,
    errorBlock: ((e: Throwable) -> Unit)? = null,
) : StatefulCoroutine<T>(parentContext, false, errorBlock) {

    private val continuation = block.createCoroutineUnintercepted(this, this)

    /**
     * 开始之前的回调
     */
    private var startBlock = ArrayList<(() -> Unit)>(0)

    /**
     * 协程异常回调
     */
    private var errorBlock = ArrayList<((e: Throwable) -> Unit)>(0)

    /**
     * 执行完成
     */
    private var completeBlock = ArrayList<((T?) -> Unit)>(0)

    override fun handleJobException(exception: Throwable): Boolean {
        val boolean = super.handleJobException(exception)
        if (exception !is CancellationException) {
            errorBlock.forEach { it.invoke(exception) }
        }
        return boolean
    }

    override fun onCompleted(value: T) {
        super.onCompleted(value)
        completeBlock.forEach { it.invoke(value) }
        removeCallbacks()
    }

    override fun onCancelled(cause: Throwable, handled: Boolean) {
        super.onCancelled(cause, handled)
        completeBlock.forEach { it.invoke(null) }
        removeCallbacks()
    }

    override fun onStart() {
        super.onStart()

        startBlock.forEach {
            it.invoke()
        }

        runSafely(this) {
            continuation.intercepted().resumeCancellableWith(Result.success(Unit))
        }
    }

    private inline fun runSafely(completion: Continuation<*>, block: () -> Unit) {
        try {
            block()
        } catch (e: Throwable) {
            dispatcherFailure(completion, e)
        }
    }

    private fun dispatcherFailure(completion: Continuation<*>, e: Throwable) {
        /*
         * This method is invoked when we failed to start a coroutine due to the throwing
         * dispatcher implementation or missing Dispatchers.Main.
         * This situation is not recoverable, so we are trying to deliver the exception by all means:
         * 1) Resume the coroutine with an exception, so it won't prevent its parent from completion
         * 2) Rethrow the exception immediately, so it will crash the caller (e.g. when the coroutine had
         *    no parent or it was async/produce over MainScope).
         */
        completion.resumeWith(Result.failure(e))
        throw e
    }

    private fun removeCallbacks() {
        startBlock.clear()
        errorBlock.clear()
        completeBlock.clear()
    }

    fun onStart(block: () -> Unit) = apply { startBlock.add(block) }

    fun onError(error: (e: Throwable) -> Unit) = apply { errorBlock.add(error) }

    fun onComplete(complete: (T?) -> Unit) = apply { completeBlock.add(complete) }
}