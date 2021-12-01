package com.iappsasia.industry_android.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiayang.quickandroid.ui.notifications.NotificationsRepository

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 09：57
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class NotificationsFactory(private val repository: NotificationsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotificationsViewModel(repository) as T
    }
}