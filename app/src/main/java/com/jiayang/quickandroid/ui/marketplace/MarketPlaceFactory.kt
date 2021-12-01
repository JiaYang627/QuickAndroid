package com.iappsasia.industry_android.ui.marketplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 11：20
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class MarketPlaceFactory(private val repository: MarketPlaceRepository) :
    ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MarketPlaceViewModel(repository) as T
    }
}