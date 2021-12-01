package com.jiayang.quickandroid.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iappsasia.industry_android.ui.profile.ProfileRepository
import com.iappsasia.industry_android.ui.profile.ProfileViewModel

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 15：04
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class ProfileFactory(private val repository: ProfileRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}