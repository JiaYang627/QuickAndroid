package com.jiayang.quickandroid.network

import com.jiayang.quickandroid.network.HttpHeaders
import com.jiayang.quickandroid.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception

/**
 * @author ：张 奎
 * @date ：2021-11-18 10：13
 * 邮箱   ：JiaYang627@163.com
 */
class CommonHeaderInterceptor(private var mHeaders: HttpHeaders) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (mHeaders.headersMap.isEmpty()) return chain.proceed(builder.build())
        try {
            for ((key, value) in mHeaders.headersMap.entries) {
                //去除重复的header
                //builder.removeHeader(entry.getKey());
                //builder.addHeader(entry.getKey(), entry.getValue()).build();
                builder.header(key, value).build()
            }
        } catch (e: Exception) {
            LogUtils.error(e)
        }
        return chain.proceed(builder.build())
    }
}