package com.jiayang.quickandroid.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * fix NestedScrollView + RecyclerView  Nesting sliding problem
 *
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-29 10：25
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class QuickLinearLayoutManager : LinearLayoutManager {
    constructor(context: Context) : super(context)

    constructor(
        context: Context, @RecyclerView.Orientation orientation: Int,
        reverseLayout: Boolean
    ) : super(context, orientation, reverseLayout)

    constructor(
        context: Context, attrs: AttributeSet, defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun isSmoothScrollbarEnabled(): Boolean = true

    override fun isAutoMeasureEnabled(): Boolean = true
}