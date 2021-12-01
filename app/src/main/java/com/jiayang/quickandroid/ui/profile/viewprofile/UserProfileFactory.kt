package com.iappsasia.industry_android.ui.profile.viewprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiayang.quickandroid.ui.profile.viewprofile.UserProfileRepository

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 16：52
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class UserProfileFactory(private val repository: UserProfileRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserProfileViewModel(repository) as T
    }
}