package com.iappsasia.industry_android.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author ：张 奎
 * @date ：2021-11-23 16：12
 * 邮箱   ：JiaYang627@163.com
 */
class LoginFactory(private val loginRepository: LoginRepository) :
    ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
    }
}