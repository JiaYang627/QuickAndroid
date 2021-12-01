package com.iappsasia.industry_android.ui.currencyrates

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.SpanUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jiayang.quickandroid.R

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 12：00
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class CurrencyRatesAdapter(mList: MutableList<String> = mutableListOf()) :
    BaseQuickAdapter<String, BaseViewHolder>(
        R.layout.item_currency_rates,
        mList
    ) {
    private var isShowDistanceLayout  = false
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.getView<TextView>(R.id.item_currency_left_text).apply {
            SpanUtils.with(this)
                .appendLine("SGD >").setFontSize(AdaptScreenUtils.pt2Px(55f))
                .append("X MoneyChanger")
                .append("\n")
                .append("West Mall")
                .create()
        }
        holder.getView<LinearLayout>(R.id.item_currency_distance_layout).apply {
            visibility = if (isShowDistanceLayout) View.VISIBLE else View.GONE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDistanceVisible(showDistanceLayout: Boolean) {
        isShowDistanceLayout = showDistanceLayout
        notifyDataSetChanged()
    }

}


class CurrencyRatesRepository {
}