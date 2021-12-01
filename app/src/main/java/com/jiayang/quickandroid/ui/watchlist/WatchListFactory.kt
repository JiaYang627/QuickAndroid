package com.iappsasia.industry_android.ui.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiayang.quickandroid.ui.watchlist.WatchListRepository

/**
 * @author ：张 奎
 * @date ：2021-11-24 14：13
 * 邮箱   ：JiaYang627@163.com
 */
class WatchListFactory(private val repository: WatchListRepository):
    ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WatchListViewModel(repository) as T
    }

}