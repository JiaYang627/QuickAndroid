package com.iappsasia.industry_android.network

import com.squareup.moshi.*
import com.squareup.moshi.internal.Util
import java.lang.NullPointerException
import java.lang.ref.Reference
import java.lang.reflect.Constructor

/**
 * @author ：张 奎
 * @date ：2021-11-22 10：59
 * 邮箱   ：JiaYang627@163.com
 */


@JsonClass(generateAdapter = true)
data class BaseResultData<T>(
    @Json(name = "result")
    var `data`: T?,
    @Json(name = "status_code")
    var mStatusCode: Int = 0,
    @Json(name = "message")
    var mRequestMessage: String = "",
    @Json(name = "status")
    var mStatus: Boolean = false
){}
