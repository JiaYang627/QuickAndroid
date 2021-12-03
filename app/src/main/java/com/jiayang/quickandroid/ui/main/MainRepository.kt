package com.jiayang.quickandroid.ui.main

import cn.jiayang.kotlinstudyjetpack.network.RetrofitManager
import com.jiayang.quickandroid.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author ：张 奎
 * @date ：2021-11-17 11：27
 * 邮箱   ：JiaYang627@163.com
 */

class MainRepository @Inject constructor(private val apiService: ApiService){
    suspend fun login(userName: String, password: String) =
        withContext(Dispatchers.IO){
        apiService.login(userName, password)
    }


}