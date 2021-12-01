package com.jiayang.quickandroid.ui.main

import cn.jiayang.kotlinstudyjetpack.network.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author ：张 奎
 * @date ：2021-11-17 11：27
 * 邮箱   ：JiaYang627@163.com
 */



class MainRepository{
    suspend fun login(userName: String, password: String) =
        withContext(Dispatchers.IO){
        RetrofitManager.apiService.login(userName, password)
    }


}