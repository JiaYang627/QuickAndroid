package com.jiayang.quickandroid.base

import cn.jiayang.kotlinstudyjetpack.network.RetrofitManager
import com.jiayang.quickandroid.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-12-03 10：25
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Singleton
    @Provides
    fun provideApiService():ApiService{
        return RetrofitManager.apiService
    }
}