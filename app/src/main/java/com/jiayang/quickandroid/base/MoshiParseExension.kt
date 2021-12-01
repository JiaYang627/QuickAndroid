package com.jiayang.quickandroid.base

import com.iappsasia.industry_android.network.BaseResultData
import com.jiayang.quickandroid.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-12-01 18：35
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
inline fun <reified R : Any> toJsonByNormal(value: R): String =
    Moshi.Builder().build().adapter(R::class.java).run {
        toJson(value)
    }

inline fun <reified R : Any> toJsonByListType(value: MutableList<R>): String {
    val types = Types.newParameterizedType(MutableList::class.java, R::class.java)
    return Moshi.Builder().build().adapter<MutableList<R>>(types).run {
        toJson(value)
    }
}
inline fun <reified R : Any> toJsonByBaseResultType(value: BaseResultData<R>): String {
    val types = Types.newParameterizedType(BaseResultData::class.java, R::class.java)
    return Moshi.Builder().build().adapter<BaseResultData<R>>(types).run {
        toJson(value)
    }
}

inline fun <reified R : Any> fromJsonByNormal(value: String): R? =
    Moshi.Builder().build().adapter(R::class.java).run {
        fromJson(value)
    }

inline fun <reified R : Any> fromJsonByListType(value: String): MutableList<R>? {
    val types = Types.newParameterizedType(MutableList::class.java, R::class.java)
    return Moshi.Builder().build().adapter<MutableList<R>>(types).run {
        fromJson(value)
    }
}

inline fun <reified R : Any> fromJsonByBaseResultType(value: String): BaseResultData<R>? {
    val types = Types.newParameterizedType(BaseResultData::class.java, R::class.java)
    return Moshi.Builder().build().adapter<BaseResultData<R>>(types).run {
        fromJson(value)
    }
}