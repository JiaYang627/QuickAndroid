package com.jiayang.quickandroid.ui.profile.viewprofile

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.network.ApiService
import javax.inject.Inject

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 16：52
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */

class UserProfileAdapter(mList :MutableList<String> = mutableListOf()):
    BaseQuickAdapter<String,BaseViewHolder>(
        R.layout.item_user_profile,
        mList
    ){
    override fun convert(holder: BaseViewHolder, item: String) {
    }

}

class UserProfileRepository @Inject constructor(private val apiService: ApiService){
}