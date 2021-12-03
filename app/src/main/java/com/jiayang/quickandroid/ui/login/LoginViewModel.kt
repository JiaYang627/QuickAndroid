package com.jiayang.quickandroid.ui.login

import androidx.lifecycle.ViewModel
import com.jiayang.quickandroid.ui.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author ：张 奎
 * @date ：2021-11-23 16：11
 * 邮箱   ：JiaYang627@163.com
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository):ViewModel() {
}