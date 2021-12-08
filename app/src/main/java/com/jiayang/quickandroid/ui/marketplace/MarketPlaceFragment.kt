package com.jiayang.quickandroid.ui.marketplace

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import coil.load
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.jiayang.quickandroid.ui.notifications.NotificationsActivity
import com.jiayang.quickandroid.widget.recyclerview.QuickLinearLayoutManager
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseLazyFragment
import com.jiayang.quickandroid.databinding.FragmentMarketPlaceBinding
import com.jiayang.quickandroid.network.MarketPlaceMultiItemBean
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author ：张 奎
 * @date ：2021-11-17 13：33
 * 邮箱   ：JiaYang627@163.com
 */
@AndroidEntryPoint
class MarketPlaceFragment : BaseLazyFragment<FragmentMarketPlaceBinding>() {

//    private val mViewModel: MarketPlaceViewModel by viewModels {
//        MarketPlaceFactory(MarketPlaceRepository())
//    }
    // use hilt
    private val mViewModel: MarketPlaceViewModel by viewModels()

    private val mList : MutableList<MarketPlaceMultiItemBean> = arrayListOf()

    private val mAdapter : MarketPlaceAdapter by lazy {
        MarketPlaceAdapter(mList)
    }

    override fun initLazyFragment() {
        mBindingView.apply {
            includeTitleBarLayout.let {
                BarUtils.addMarginTopEqualStatusBarHeight(it.commonTitleBarRoot)
                it.titleBarCenterTitleText.text = "Marketplace"
                it.titleBarRightBellImg.let {
                    it.visibility = View.VISIBLE
                    it.load(R.drawable.icon_bell_white)
                    it.setOnClickListener {
                        startActivity(Intent(context, NotificationsActivity::class.java))
                    }
                }
            }
            marketTopAddLayout.apply {
                setOnClickListener {
//                    startActivity(Intent(activity,AddRequestActivity::class.java))
                }
            }

            val marketPlaceTitleBean = MarketPlaceMultiItemBean(MarketPlaceAdapter.TYPE_TITLE).apply {
                mTitleStr = "Expiring Requests:"
            }
            val marketPlaceContentBean = MarketPlaceMultiItemBean(MarketPlaceAdapter.TYPE_CONTENT).apply {
                mContentLeftTopStr = ""
                mContentRightTopStr = "5 bids"
                mContentRightMiddleStr = "06:30:00"
                mContentRightBottomStr = "01/09/2021"
            }

            mList.apply {
                add(marketPlaceTitleBean)
                add(marketPlaceContentBean)
                add(marketPlaceContentBean)
                add(marketPlaceTitleBean)
                add(marketPlaceContentBean)
                add(marketPlaceContentBean)
            }

            marketRecyclerView.apply {
                layoutManager = QuickLinearLayoutManager(activity as AppCompatActivity)
                adapter = mAdapter
            }
            mAdapter.setOnItemClickListener { adapter, view, position ->
                mAdapter.data[position].let {
                    when (it.itemType) {
                        MarketPlaceAdapter.TYPE_TITLE -> {
                            ToastUtils.showLong("点击了Title")
                        }
                        MarketPlaceAdapter.TYPE_CONTENT -> {
                            ToastUtils.showLong("点击了Content")
                        }
                        else ->{

                        }
                    }
                }
            }

        }
    }
}