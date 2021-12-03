package com.jiayang.quickandroid.ui.currencyrates.makeoffer

import androidx.lifecycle.ViewModel
import com.jiayang.quickandroid.ui.currencyrates.makeoffer.MakeOfferCurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 14：12
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
@HiltViewModel
class MakeOfferCurrencyViewModel @Inject constructor(private val repository: MakeOfferCurrencyRepository) :ViewModel() {
}