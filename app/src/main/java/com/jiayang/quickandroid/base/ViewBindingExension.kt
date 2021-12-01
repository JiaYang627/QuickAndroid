package com.iappsasia.industry_android.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author ：张 奎
 * @date ：2020-04-29 09：39
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */

@Suppress("UNCHECKED_CAST")
fun <T : Any, VB : ViewBinding> T.createViewBindingForAct(layoutInflater: LayoutInflater): VB? {
    (javaClass.genericSuperclass as ParameterizedType).let {
        val clazz = it.actualTypeArguments[0] as Class<VB>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, layoutInflater) as VB
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Any, VB : ViewBinding> T.createViewBindingForFragment(
    layoutInflater: LayoutInflater,
    container: ViewGroup?
): VB? {
    (javaClass.genericSuperclass as ParameterizedType).let {
        val clazz = it.actualTypeArguments[0] as Class<VB>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, layoutInflater, container, false) as VB
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Any, VB : ViewBinding> T.createViewBindingForAdapter(
    layoutInflater: LayoutInflater,
    layoutId :Int,
    container: ViewGroup?
): VB? {
    (javaClass.genericSuperclass as ParameterizedType).let {
        val clazz = it.actualTypeArguments[0] as Class<VB>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            Int::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, layoutInflater, layoutId,container, false) as VB
    }
}