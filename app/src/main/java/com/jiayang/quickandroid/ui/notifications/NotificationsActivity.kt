package com.jiayang.quickandroid.ui.notifications

import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.iappsasia.industry_android.ui.notifications.NotificationsFactory
import com.iappsasia.industry_android.ui.notifications.NotificationsViewModel
import com.iappsasia.industry_android.widget.recyclerview.QuickLinearLayoutManager
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseActivity
import com.jiayang.quickandroid.databinding.ActivityNotificationsBinding

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 09：41
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class NotificationsActivity : BaseActivity<ActivityNotificationsBinding>() {

    private val mList: MutableList<String> = mutableListOf()

    private val mAdapter by lazy {
        NotificationsAdapter(mList)
    }

    private val mViewModel: NotificationsViewModel by viewModels {
        NotificationsFactory(NotificationsRepository())
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        mBindingView.apply {
            includeTitleBarLayout.let {
                BarUtils.addMarginTopEqualStatusBarHeight(it.commonTitleBarRoot)
                it.titleBarLeftBackImg.apply {
                    load(R.drawable.icon_back_white)
                    setOnClickListener {
                        this@NotificationsActivity.finish()
                    }
                }
                it.titleBarCenterTitleText.text = "Notifications"
            }
            mList.apply {
                add("1")
                add("1")
                add("1")
                add("1")
                add("1")
            }
            notificationRecyclerView.let {
                it.layoutManager = QuickLinearLayoutManager(this@NotificationsActivity)
                it.adapter = mAdapter
            }

            mAdapter.setOnItemClickListener { adapter, view, position ->
                ToastUtils.showLong("点击了第${position+1}个View")
            }
        }
    }
}