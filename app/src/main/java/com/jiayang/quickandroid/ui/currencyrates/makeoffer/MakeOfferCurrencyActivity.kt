package com.jiayang.quickandroid.ui.currencyrates.makeoffer

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import coil.load
import com.blankj.utilcode.util.BarUtils
import com.jiayang.quickandroid.base.Constant
import com.jiayang.quickandroid.ui.profile.viewprofile.UserProfileActivity
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseActivity
import com.jiayang.quickandroid.databinding.ActivityMakeOfferCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-28 14：10
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
@AndroidEntryPoint
class MakeOfferCurrencyActivity : BaseActivity<ActivityMakeOfferCurrencyBinding>() {

//    private val mViewModel: MakeOfferCurrencyViewModel by viewModels {
//        MakeOfferCurrencyFactory(MakeOfferCurrencyRepository())
//    }
    // use hilt
private val mViewModel: MakeOfferCurrencyViewModel by viewModels()
    private val mTopViewGone: Boolean by lazy {
        intent.extras?.getBoolean(Constant.MAKE_OFFER_TOP_GONE) ?: false
    }

    override fun initActivity(savedInstanceState: Bundle?) {


        mBindingView.apply {
            includeTitleBarLayout.apply {
                BarUtils.addMarginTopEqualStatusBarHeight(commonTitleBarRoot)
                titleBarCenterTitleText.text = "Make Offer"
                titleBarLeftBackImg.apply {
                    load(R.drawable.icon_back_white)
                    setOnClickListener {
                        this@MakeOfferCurrencyActivity.finish()
                    }
                }
            }

            viewProfileTextView.apply {
                setOnClickListener {
                    startActivity(UserProfileActivity::class.java)
                }
                visibility = if (mTopViewGone) View.GONE else View.VISIBLE
            }
            currencyMakeOfferTopLeftText.apply {
                visibility = if (mTopViewGone) View.GONE else View.VISIBLE
            }

        }
    }
}