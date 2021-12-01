package com.jiayang.quickandroid.ui.currencyrates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iappsasia.industry_android.ui.currencyrates.CurrencyRatesRepository
import com.iappsasia.industry_android.ui.currencyrates.CurrencyRatesViewModel

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 12：00
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class CurrencyRatesFactory(private val repository: CurrencyRatesRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyRatesViewModel(repository) as T
    }
}