package com.jiayang.quickandroid.ui.marketplace

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 11：19
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
@HiltViewModel
class MarketPlaceViewModel @Inject constructor(private val repository: MarketPlaceRepository) :ViewModel() {

}