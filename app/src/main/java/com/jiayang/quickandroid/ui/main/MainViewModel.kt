package com.iappsasia.industry_android.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iappsasia.industry_android.base.safeLaunch
import com.jiayang.quickandroid.ui.main.MainRepository

/**
 * @author ：张 奎
 * @date ：2021-11-17 11：26
 * 邮箱   ：JiaYang627@163.com
 */
class MainViewModel(private val repository : MainRepository) : ViewModel() {


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
}