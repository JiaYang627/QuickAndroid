package com.jiayang.quickandroid.ui.watchlist

import androidx.lifecycle.ViewModel
import com.jiayang.quickandroid.ui.watchlist.WatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author ：张 奎
 * @date ：2021-11-24 14：11
 * 邮箱   ：JiaYang627@163.com
 */
@HiltViewModel
class WatchListViewModel @Inject constructor(private val repository: WatchListRepository) :ViewModel() {


}