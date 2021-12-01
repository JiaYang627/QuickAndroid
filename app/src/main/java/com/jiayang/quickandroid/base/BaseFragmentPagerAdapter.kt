package com.jiayang.quickandroid.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author ：张 奎
 * @date ：2021-11-17 11：34
 * 邮箱   ：JiaYang627@163.com
 */
class BaseFragmentPagerAdapter(
    fragmentManager : FragmentManager,
    fragments : ArrayList<out Fragment>,
    lifecycle: Lifecycle,
    titles: Array<String>? = null
) : FragmentStateAdapter(fragmentManager,lifecycle){


    private var mFragments = fragments
    private var mTitles = titles

    init {
        if (mTitles.isNullOrEmpty())
            mTitles = Array(fragments.size) { "" }
    }

    override fun getItemCount(): Int = mFragments.size
    override fun createFragment(position: Int): Fragment = mFragments[position]


}