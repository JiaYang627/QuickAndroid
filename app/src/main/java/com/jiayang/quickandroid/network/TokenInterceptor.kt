package com.iappsasia.industry_android.network

import android.annotation.SuppressLint
import com.iappsasia.industry_android.base.CommonLiveBusEvent
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jiayang.quickandroid.utils.decodeString
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

/**
 * @author ：张 奎
 * @date ：2021-11-18 10：29
 * 邮箱   ：JiaYang627@163.com
 */
class TokenInterceptor:Interceptor {
    @SuppressLint("CheckResult")
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.run {
            val request = request()
            val authorString = decodeString("Authorization")
            if (authorString.isEmpty()) {
                proceed(request)
            } else {
                val build = request.newBuilder()
                    .header("Authorization", authorString)
                    .build()
                proceed(build)
            }
        }

        response.body?.let { it ->
            it.source().let { it ->
                it.request(Long.MAX_VALUE)
                val readString = it.buffer.clone().readString(StandardCharsets.UTF_8)
                val mTypes :Type =
                    Types.newParameterizedType(BaseResultData::class.java, String()::class.java)
                val fromJson = Moshi.Builder().build().adapter<BaseResultData<String>>(mTypes)
                    .fromJson(readString)
                fromJson?.let {
                    when(it.mStatusCode){
//                        1023->ToastUtils.showLong("Token 失效")
                        1023-> {
                            LiveEventBus.get(CommonLiveBusEvent::class.java)
                                .post(CommonLiveBusEvent(CommonLiveBusEvent.token_is_invalid,true))
                        }
                        else -> {}
                    }
                }
            }
        }
        return response

    }
}

