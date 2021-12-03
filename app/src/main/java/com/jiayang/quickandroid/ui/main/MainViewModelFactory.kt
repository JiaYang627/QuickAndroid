package com.jiayang.quickandroid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author ：张 奎
 * @date ：2021-11-17 11：29
 * 邮箱   ：JiaYang627@163.com
 */
class MainViewModelFactory(private val mainRepository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}