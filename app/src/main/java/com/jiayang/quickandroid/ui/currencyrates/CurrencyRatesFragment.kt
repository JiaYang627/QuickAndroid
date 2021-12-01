package com.jiayang.quickandroid.ui.currencyrates

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import coil.load
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SpanUtils
import com.flyco.tablayout.listener.OnTabSelectListener
import com.iappsasia.industry_android.ui.currencyrates.CurrencyRatesAdapter
import com.iappsasia.industry_android.ui.currencyrates.CurrencyRatesRepository
import com.iappsasia.industry_android.ui.currencyrates.CurrencyRatesViewModel
import com.jiayang.quickandroid.ui.currencyrates.makeoffer.MakeOfferCurrencyActivity
import com.iappsasia.industry_android.widget.recyclerview.QuickLinearLayoutManager
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseLazyFragment
import com.jiayang.quickandroid.databinding.FragmentCurrencyRatesBinding

/**
 * @author ：张 奎
 * @date ：2021-11-17 14：57
 * 邮箱   ：JiaYang627@163.com
 */
class CurrencyRatesFragment : BaseLazyFragment<FragmentCurrencyRatesBinding>() {

    private val mViewModel: CurrencyRatesViewModel by viewModels {
        CurrencyRatesFactory(CurrencyRatesRepository())
    }

    private val mList: MutableList<String> = mutableListOf()
    private val  mAdapter by lazy {
        CurrencyRatesAdapter(mList)
    }


    private val mTableData = arrayOf("Digital", "Cash")

    override fun initLazyFragment() {
        mBindingView.apply {
            includeTitleBarLayout.let {
                BarUtils.addMarginTopEqualStatusBarHeight(it.commonTitleBarRoot)
                it.titleBarCenterTitleText.text = "CurrencyRates"
                it.titleBarRightBellImg.let {
                    it.visibility = View.VISIBLE
                    it.load(R.drawable.icon_bell_white)
                }
            }
            includeCurrencyTopLayout.apply {

                currencyTopSegmentTablayout.apply {
                    setTabData(mTableData)
                    for ((index, value) in mTableData.withIndex()) {
                        val titleView = getTitleView(index)
                        titleView.typeface =
                            ResourcesCompat.getFont(context, R.font.poppins_regular)
                    }
                    setOnTabSelectListener(object :OnTabSelectListener{
                        override fun onTabSelect(position: Int) {
                            mAdapter.setDistanceVisible(position == 1)
                            currencyTopShowDistanceLayout.visibility = if (position == 1) View.VISIBLE else View.GONE
                            currencyTopShowRightArrow.visibility = if (position == 1) View.GONE else View.VISIBLE
                            currencyTopDigitalLeftImg.load(if (position == 0) R.drawable.icon_digital_white else R.drawable.icon_digital_blue)
                            currencyTopCashRightImg.load(if (position == 0) R.drawable.icon_cash_blue else R.drawable.icon_cash_white)
                        }

                        override fun onTabReselect(position: Int) {
                        }

                    })
                }

                currencyTopShowLeftText.apply {
                    SpanUtils.with(this)
                        .appendLine("SGD >").setFontSize(AdaptScreenUtils.pt2Px(55f))
                        .append("Mohamed Yoonu..")
                        .append("\n")
                        .append("Jurong Point")
                        .create()

                }
            }

            mList.apply {
                add("1")
                add("1")
                add("1")
                add("1")
                add("1")
                add("1")
            }
            currencyRecyclerView.apply {
                layoutManager = QuickLinearLayoutManager(activity as AppCompatActivity)
                adapter = mAdapter.apply {
                    setOnItemClickListener { adapter, view, position ->
                        startActivity(Intent(activity, MakeOfferCurrencyActivity::class.java))
                    }
                }
            }

        }
    }
}