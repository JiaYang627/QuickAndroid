package com.jiayang.quickandroid.base

import android.content.Context
import android.util.Log
import com.iappsasia.industry_android.network.BaseResultData
import com.jiayang.quickandroid.R
import com.squareup.moshi.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException


/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-12-01 18：35
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */

fun genderMoshiBuild(): Moshi {
    return Moshi.Builder()
        .add(String::class.java, NullStringAdapter())
        .add(Int::class.java, NullIntAdapter())
        .add(Long::class.java, NullLongAdapter())
        .add(Double::class.java, NullDoubleAdapter())
        .add(Boolean::class.java, NullBooleanAdapter())
        .build()
}

inline fun <reified R : Any> toJsonByNormal(value: R): String =
    genderMoshiBuild().adapter(R::class.java).run {
        toJson(value)
    }

inline fun <reified R : Any> toJsonByListType(value: MutableList<R>): String {
    val types = Types.newParameterizedType(MutableList::class.java, R::class.java)
    return genderMoshiBuild().adapter<MutableList<R>>(types).run {
        toJson(value)
    }
}

inline fun <reified R : Any> toJsonByBaseResultType(value: BaseResultData<R>): String {
    val types = Types.newParameterizedType(BaseResultData::class.java, R::class.java)
    return genderMoshiBuild().adapter<BaseResultData<R>>(types).run {
        toJson(value)
    }
}

inline fun <reified R : Any> fromJsonByNormal(value: String): R? =
    genderMoshiBuild().adapter(R::class.java).run {
        fromJson(value)
    }

inline fun <reified R : Any> fromJsonByListType(value: String): MutableList<R>? {
    val types = Types.newParameterizedType(MutableList::class.java, R::class.java)
    return genderMoshiBuild().adapter<MutableList<R>>(types).run {
        fromJson(value)
    }
}

inline fun <reified R : Any> fromJsonByBaseResultType(value: String): BaseResultData<R>? {
    val types = Types.newParameterizedType(BaseResultData::class.java, R::class.java)
    return genderMoshiBuild().adapter<BaseResultData<R>>(types).run {
        fromJson(value)
    }
}


internal class NullStringAdapter : JsonAdapter<String>() {
    override fun fromJson(reader: JsonReader): String? {
        try {
            if (reader.peek() != JsonReader.Token.NULL) {
                return reader.nextString()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return ""
    }

    override fun toJson(writer: JsonWriter, value: String?) {
        writer.value(value)
    }
}

internal class NullIntAdapter : JsonAdapter<Int>() {
    override fun fromJson(reader: JsonReader): Int? {
        try {
            if (reader.peek() != JsonReader.Token.NULL || !"".equals(reader.peek())) {
                return reader.nextInt()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return 0
    }

    override fun toJson(writer: JsonWriter, value: Int?) {
        writer.value(value)
    }
}

internal class NullLongAdapter : JsonAdapter<Long>() {
    override fun fromJson(reader: JsonReader): Long? {
        try {
            if (reader.peek() != JsonReader.Token.NULL || !"".equals(reader.peek())) {
                return reader.nextLong()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return 0L
    }

    override fun toJson(writer: JsonWriter, value: Long?) {
        writer.value(value)
    }
}

internal class NullDoubleAdapter : JsonAdapter<Double>() {
    override fun fromJson(reader: JsonReader): Double? {
        try {
            if (reader.peek() != JsonReader.Token.NULL || !"".equals(reader.peek())) {
                return reader.nextDouble()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return 0.00
    }

    override fun toJson(writer: JsonWriter, value: Double?) {
        writer.value(value)
    }
}

internal class NullBooleanAdapter : JsonAdapter<Boolean>() {
    override fun fromJson(reader: JsonReader): Boolean? {
        try {
            if (reader.peek() != JsonReader.Token.NULL || !"".equals(reader.peek())) {
                return reader.nextBoolean()
            }
            reader.nextNull<Unit>()
        } catch (e: Exception) {

        }
        return false
    }

    override fun toJson(writer: JsonWriter, value: Boolean?) {
        writer.value(value)
    }
}