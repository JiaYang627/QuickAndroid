package com.jiayang.quickandroid.base

import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.ToastUtils
import com.jiayang.quickandroid.entity.UIState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.nio.charset.StandardCharsets

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-12-22 15：41
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
open class BaseViewModel : ViewModel() {


    private val _uiStateFlow = MutableSharedFlow<UIState>(
        replay = 0,
        extraBufferCapacity = 10,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val uiStateFlow: Flow<UIState> get() = _uiStateFlow

    fun postUIDialogState(dialogState: UIState) {
        _uiStateFlow.tryEmit(dialogState)
    }

    fun showLoadingAni(message: String? = null) {
        _uiStateFlow.tryEmit(UIState.LoadingAni(true, message))
    }

    fun dismissLoadingAni() {
        _uiStateFlow.tryEmit(UIState.LoadingAni(false))
    }
    fun showMessage(message: String? = "") {
        _uiStateFlow.tryEmit(UIState.ShowMessage(message))
    }


    fun dealWithThrowable(it: Throwable) {
        if (it is HttpException) {
                (it as HttpException).response()?.errorBody()?.let {
                    it.source().let { _it ->
                        _it.request(Long.MAX_VALUE)
                        val readString = _it.buffer.clone().readString(StandardCharsets.UTF_8)
                        val fromJson = fromJsonByBaseResultType<Any>(readString)
                        fromJson?.let {
                            ToastUtils.showLong(it.mRequestMessage)
                        }
                    }
                }
        }
    }

}