package com.jiayang.quickandroid.ui.watchlist

import android.widget.TextView
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.SpanUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.network.ApiService
import javax.inject.Inject

/**
 * @author ：张 奎
 * @date ：2021-11-24 14：12
 * 邮箱   ：JiaYang627@163.com
 */
class WatchListAdapter(mList: MutableList<String>? = mutableListOf()) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_watch_list_layout, mList) {
    override fun convert(holder: BaseViewHolder, item: String) {

        holder.getView<TextView>(R.id.item_watch_list_left_text_view).let {
            SpanUtils.with(it)
                .appendLine("SGD >").setFontSize(AdaptScreenUtils.pt2Px(55F))
                .append("X MoneyChanger")
                .create()
        }
    }

}

class WatchListRepository @Inject constructor(private val apiService: ApiService) {
}