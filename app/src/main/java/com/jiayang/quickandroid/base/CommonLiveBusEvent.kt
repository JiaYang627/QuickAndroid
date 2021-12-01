package com.iappsasia.industry_android.base

import com.jeremyliao.liveeventbus.core.LiveEvent

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 14：18
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class CommonLiveBusEvent(private val mType: Int, private val objects: Any) :LiveEvent {


    val mEventObject :Any get() = objects
    val mEventType :Int get() = mType

    companion object {
        // eg
        val token_is_invalid = 1
    }


}