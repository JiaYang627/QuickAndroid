package com.iappsasia.industry_android.ui.marketplace

import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.SpanUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.network.MarketPlaceMultiItemBean

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 11：19
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class MarketPlaceAdapter(mList: MutableList<MarketPlaceMultiItemBean> = mutableListOf()) :
    BaseMultiItemQuickAdapter<MarketPlaceMultiItemBean, BaseViewHolder>(mList) {

    companion object {
        val TYPE_TITLE = 1
        val TYPE_CONTENT = 2
    }

    init {
        addItemType(TYPE_TITLE, R.layout.item_marketplace_type_title)
        addItemType(TYPE_CONTENT, R.layout.item_marketplace_type_content)
    }

    override fun convert(holder: BaseViewHolder, item: MarketPlaceMultiItemBean) {
        when (item.itemType) {
            TYPE_TITLE -> {
            }
            TYPE_CONTENT -> {
                SpanUtils.with(holder.getView(R.id.item_market_place_left_text_view))
                    .appendLine("100 SGD >").setFontSize(AdaptScreenUtils.pt2Px(55F))
                    .append("X MoneyChanger")
                    .create()
            }
            else -> {

            }
        }
    }
}

class MarketPlaceRepository {
}