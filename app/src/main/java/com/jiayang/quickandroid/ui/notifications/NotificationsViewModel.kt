package com.jiayang.quickandroid.ui.notifications

import androidx.lifecycle.ViewModel
import com.jiayang.quickandroid.ui.notifications.NotificationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 09：55
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
@HiltViewModel
class NotificationsViewModel @Inject constructor(private val repository: NotificationsRepository) :ViewModel() {
}