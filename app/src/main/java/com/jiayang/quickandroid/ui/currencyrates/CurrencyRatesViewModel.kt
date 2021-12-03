package com.jiayang.quickandroid.ui.currencyrates

import androidx.lifecycle.ViewModel
import com.jiayang.quickandroid.ui.currencyrates.CurrencyRatesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 11：58
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(private val repository: CurrencyRatesRepository) :ViewModel() {

}