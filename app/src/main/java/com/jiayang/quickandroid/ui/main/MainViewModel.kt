package com.jiayang.quickandroid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iappsasia.industry_android.base.launchSafety
import com.iappsasia.industry_android.base.safeLaunch
import com.jiayang.quickandroid.base.BaseViewModel
import com.jiayang.quickandroid.ui.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author ：张 奎
 * @date ：2021-11-17 11：26
 * 邮箱   ：JiaYang627@163.com
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository : MainRepository) : BaseViewModel() {


    fun searchPlace() {
        viewModelScope.safeLaunch {
            block = {
                val login = repository.login(
                    "chao+gcs_sa@iappsasia.com",
                    "123456"
                )
//                val searchPlace = repository.getSearchPlace("北京")

            }
        }
    }

    fun useNewCL() {
        viewModelScope.launchSafety {
            showLoadingAni()
            // 接下来 网络实体请求
//            repository.xxxx
        }.onError {
            dismissLoadingAni()
            dealWithThrowable(it)
        }.onComplete {
            dismissLoadingAni()
        }.onSuccess {

        }
    }
}