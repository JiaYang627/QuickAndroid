package com.jiayang.quickandroid.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * fix NestedScrollView + RecyclerView  Nesting sliding problem
 *
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-29 10：28
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class QuickRecyclerView : RecyclerView {
    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )

    override fun setHasFixedSize(hasFixedSize: Boolean) {
        super.setHasFixedSize(true)
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        super.setNestedScrollingEnabled(false)
    }
}