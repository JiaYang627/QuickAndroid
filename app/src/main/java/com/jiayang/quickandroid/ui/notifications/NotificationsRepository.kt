package com.jiayang.quickandroid.ui.notifications

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.network.ApiService
import javax.inject.Inject

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 09：57
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */

class NotificationsAdapter(mList: MutableList<String> = mutableListOf()) :
    BaseQuickAdapter<String, BaseViewHolder>(
        R.layout.item_notifications_layout,
        mList
    ) {
    override fun convert(holder: BaseViewHolder, item: String) {
    }

}

class NotificationsRepository @Inject constructor(private val apiService: ApiService){
}