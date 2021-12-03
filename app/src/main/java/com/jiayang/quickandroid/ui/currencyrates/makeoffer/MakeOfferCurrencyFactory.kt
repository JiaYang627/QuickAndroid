package com.jiayang.quickandroid.ui.currencyrates.makeoffer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 14：13
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class MakeOfferCurrencyFactory(private val repository: MakeOfferCurrencyRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MakeOfferCurrencyViewModel(repository) as T
    }
}