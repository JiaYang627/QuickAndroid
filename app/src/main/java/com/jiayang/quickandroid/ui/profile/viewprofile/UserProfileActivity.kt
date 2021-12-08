package com.jiayang.quickandroid.ui.profile.viewprofile

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import com.flyco.tablayout.listener.OnTabSelectListener
import com.jiayang.quickandroid.ui.marketplace.MarketPlaceAdapter
import com.iappsasia.industry_android.widget.ldialog.LDialog
import com.iappsasia.industry_android.widget.ldialog.base.BaseLDialog
import com.iappsasia.industry_android.widget.ldialog.base.ViewHandlerListener
import com.iappsasia.industry_android.widget.ldialog.base.ViewHolder
import com.iappsasia.industry_android.widget.ldialog.base.setOnClickListener
import com.jiayang.quickandroid.widget.recyclerview.QuickLinearLayoutManager
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseActivity
import com.jiayang.quickandroid.databinding.ActivityUserProfileBinding
import com.jiayang.quickandroid.network.MarketPlaceMultiItemBean
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 15：24
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
@AndroidEntryPoint
class UserProfileActivity : BaseActivity<ActivityUserProfileBinding>() {

//    private val mViewModel: UserProfileViewModel by viewModels {
//        UserProfileFactory(UserProfileRepository())
//    }
    // use hilt
    private val mViewModel: UserProfileViewModel by viewModels()

    private val mList: MutableList<String> = mutableListOf()

    private val mAdapter: UserProfileAdapter by lazy {
        UserProfileAdapter(mList).apply {
            setOnItemClickListener { adapter, view, position ->
                ToastUtils.showLong("Click ${position + 1} View")
            }
        }
    }

    private val mRequestsList: MutableList<MarketPlaceMultiItemBean> = arrayListOf()

    private val mRequestsAdapter: MarketPlaceAdapter by lazy {
        MarketPlaceAdapter(mRequestsList)
    }

    private val mTableData = arrayOf("Currency rate", "Requests")

    private val mChangeOutDialog: LDialog by lazy {
        LDialog.init(supportFragmentManager)
            .setLayoutRes(R.layout.dialog_change_outlet)
            .setBackgroundDrawableRes(R.drawable.dialog_bg_grey)
            .setGravity(Gravity.CENTER)
            .setWidthScale(0.85f)
            .setCancelableAll(false)
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        mBindingView.apply {
            includeTitleBarLayout.apply {
                BarUtils.addMarginTopEqualStatusBarHeight(commonTitleBarRoot)
                titleBarCenterTitleText.text = "XXX Profile"
                titleBarLeftBackImg.apply {
                    load(R.drawable.icon_back_white)
                    setOnClickListener {
                        this@UserProfileActivity.finish()
                    }
                }
            }
            userPic.apply {
                load(R.mipmap.ic_launcher) {
                    transformations(CircleCropTransformation())
                }
            }
            transitionText.apply {
                SpanUtils.with(this)
                    .appendLine("200").setFontSize(AdaptScreenUtils.pt2Px(42f))
                    .append("transactions made")
                    .create()
            }

            twoOutletLeftText.apply {
                SpanUtils.with(this)
                    .appendLine("200").setFontSize(AdaptScreenUtils.pt2Px(42f))
                    .append("transactions made")
                    .create()
            }
            twoOutletMiddleText.apply {
                SpanUtils.with(this)
                    .appendLine("4.5/5.0").setFontSize(AdaptScreenUtils.pt2Px(42f))
                    .append("ratings")
                    .create()
            }

            userProfileTabLayout.apply {
                setTabData(mTableData)
                setOnTabSelectListener(object : OnTabSelectListener {
                    override fun onTabSelect(position: Int) {
                        userProfileSearchLayout.visibility =
                            if (position == 0) View.VISIBLE else View.GONE
                        userProfileRecyclerView.adapter =
                            if (position == 0) mAdapter else mRequestsAdapter
                    }

                    override fun onTabReselect(position: Int) {
                    }

                })
            }

            mList.apply {
                add("1")
                add("1")
                add("1")
                add("1")
            }

            mRequestsList.apply {
                add(MarketPlaceMultiItemBean(MarketPlaceAdapter.TYPE_CONTENT).apply {
                    mContentLeftTopStr = ""
                    mContentRightTopStr = "5 bids"
                    mContentRightMiddleStr = "06:30:00"
                    mContentRightBottomStr = "01/09/2021"
                })
                add(MarketPlaceMultiItemBean(MarketPlaceAdapter.TYPE_CONTENT).apply {
                    mContentLeftTopStr = ""
                    mContentRightTopStr = "5 bids"
                    mContentRightMiddleStr = "06:30:00"
                    mContentRightBottomStr = "01/09/2021"
                })
                add(MarketPlaceMultiItemBean(MarketPlaceAdapter.TYPE_CONTENT).apply {
                    mContentLeftTopStr = ""
                    mContentRightTopStr = "5 bids"
                    mContentRightMiddleStr = "06:30:00"
                    mContentRightBottomStr = "01/09/2021"
                })
                add(MarketPlaceMultiItemBean(MarketPlaceAdapter.TYPE_CONTENT).apply {
                    mContentLeftTopStr = ""
                    mContentRightTopStr = "5 bids"
                    mContentRightMiddleStr = "06:30:00"
                    mContentRightBottomStr = "01/09/2021"
                })
            }

            userProfileRecyclerView.apply {
                layoutManager = QuickLinearLayoutManager(this@UserProfileActivity)
                adapter = mAdapter
            }

            changeOutletText.setOnClickListener {
                mChangeOutDialog.apply {
                    setViewHandlerListener(object : ViewHandlerListener() {
                        override fun convertView(holder: ViewHolder, dialog: BaseLDialog<*>) {
                            holder.setOnClickListener(R.id.store_layout) {
                                storeInfoLayout.visibility = View.VISIBLE
                                orderInfoLayout.visibility = View.GONE
                                ToastUtils.showLong("Info layout is change ,please close this dialog")
                            }
                            holder.setOnClickListener(R.id.order_layout) {
                                storeInfoLayout.visibility = View.GONE
                                orderInfoLayout.visibility = View.VISIBLE
                                ToastUtils.showLong("Info layout is change ,please close this dialog")
                            }
                            holder.setOnClickListener(R.id.select_dialog_close) {
                                dialog.dismiss()
                            }
                        }
                    })
                    show()
                }
            }

        }
    }
}