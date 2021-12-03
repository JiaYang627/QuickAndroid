package com.jiayang.quickandroid.ui.profile.viewprofile

import androidx.lifecycle.ViewModel
import com.jiayang.quickandroid.ui.profile.viewprofile.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 16：51
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
@HiltViewModel
class UserProfileViewModel @Inject constructor(private val repository: UserProfileRepository) :ViewModel(){

}