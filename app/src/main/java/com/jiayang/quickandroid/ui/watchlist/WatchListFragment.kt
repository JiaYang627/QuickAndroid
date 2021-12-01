package com.jiayang.quickandroid.ui.watchlist

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import coil.load
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.jiayang.quickandroid.ui.notifications.NotificationsActivity
import com.iappsasia.industry_android.ui.watchlist.WatchListFactory
import com.iappsasia.industry_android.ui.watchlist.WatchListViewModel
import com.iappsasia.industry_android.widget.recyclerview.QuickLinearLayoutManager
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseLazyFragment
import com.jiayang.quickandroid.databinding.FragmentWatchListBinding


/**
 * @author ：张 奎
 * @date ：2021-11-17 13：25
 * 邮箱   ：JiaYang627@163.com
 */
class WatchListFragment : BaseLazyFragment<FragmentWatchListBinding>() {

    private val mViewModel: WatchListViewModel by viewModels {
        WatchListFactory(WatchListRepository())
    }
    private val mList: MutableList<String> = mutableListOf()

    private val mAdapter: WatchListAdapter by lazy { WatchListAdapter(mList) }

    override fun initLazyFragment() {

        mBindingView.apply {
            includeTitleBarLayout.let {
                BarUtils.addMarginTopEqualStatusBarHeight(it.commonTitleBarRoot)
                it.titleBarCenterTitleText.text = "WatchList"
                it.titleBarRightBellImg.let {
                    it.visibility = View.VISIBLE
                    it.load(R.drawable.icon_bell_white)
                    it.setOnClickListener {
                        startActivity(Intent(activity, NotificationsActivity::class.java))
                    }
                }
            }

            watchlistBottomAddLayout.setOnClickListener {
                ToastUtils.showLong("Click Bottom Add Button")
            }

            mList.add("1")
            mList.add("1")
            mList.add("1")
            mList.add("1")

            watchlistRecyclerView.apply {
                layoutManager = QuickLinearLayoutManager(activity as AppCompatActivity)
                adapter = mAdapter.apply {
                    setOnItemClickListener { adapter, view, position ->
                        ToastUtils.showLong("点击了第${position}个View")
                    }
                }
                visibility = View.VISIBLE
            }


        }
    }


}