package com.jiayang.quickandroid.entity

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * @author ：张 奎
 * @date ：2021-11-17 16：04
 * 邮箱   ：JiaYang627@163.com
 */
class TabEntity(
    private var mTitle : String,
    private val mSelectedIcon : Int,
    private val mUnSelectedIcon : Int):CustomTabEntity {
    override fun getTabTitle(): String = mTitle

    override fun getTabSelectedIcon(): Int = mSelectedIcon

    override fun getTabUnselectedIcon(): Int = mUnSelectedIcon
}