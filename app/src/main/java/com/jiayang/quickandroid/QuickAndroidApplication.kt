package com.jiayang.quickandroid

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.tencent.mmkv.MMKV

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-12-01 14：03
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class QuickAndroidApplication :Application() {

    companion object{
        lateinit var mApplication: Context

    }



    override fun onCreate() {
        super.onCreate()

        mApplication = applicationContext
        MMKV.initialize(mApplication)
        LogUtils.getConfig().isLogSwitch = BuildConfig.DEBUG
    }
}