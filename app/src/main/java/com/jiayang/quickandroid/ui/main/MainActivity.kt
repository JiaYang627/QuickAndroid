package com.jiayang.quickandroid.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.blankj.utilcode.util.ToastUtils
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.iappsasia.industry_android.base.CommonLiveBusEvent
import com.jiayang.quickandroid.ui.currencyrates.CurrencyRatesFragment
import com.jiayang.quickandroid.ui.marketplace.MarketPlaceFragment
import com.jiayang.quickandroid.ui.watchlist.WatchListFragment
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseActivity
import com.jiayang.quickandroid.base.BaseFragmentPagerAdapter
import com.jiayang.quickandroid.base.initUIStateObserver
import com.jiayang.quickandroid.databinding.ActivityMainBinding
import com.jiayang.quickandroid.entity.TabEntity
import com.jiayang.quickandroid.ui.login.LoginActivity
import com.jiayang.quickandroid.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
//    val mMainViewModel: MainViewModel by viewModels {
//        MainViewModelFactory(MainRepository())
//    }
    // use hilt
    val mMainViewModel : MainViewModel by viewModels()

    private val mBottomTitle by lazy {
        arrayListOf("WatchList", "MarketPlace", "Currency Rate", "Profile")
    }
    private val mIconUnselectIds = intArrayOf(
        R.drawable.icon_tab_home_n,
        R.drawable.icon_tab_markept_n,
        R.drawable.icon_tab_currency_n,
        R.drawable.icon_tab_profile_n
    )
    private val mIconSelectIds = intArrayOf(
        R.drawable.icon_tab_home_p,
        R.drawable.icon_tab_markept_p,
        R.drawable.icon_tab_currency_p,
        R.drawable.icon_tab_profile_p
    )
    private val mTabEntities = arrayListOf<CustomTabEntity>()

    private val mViewPagerAdapter by lazy {
        BaseFragmentPagerAdapter(
            supportFragmentManager,
            arrayListOf(
                WatchListFragment(),
                MarketPlaceFragment(),
                CurrencyRatesFragment(),
                ProfileFragment()
            ),
            lifecycle
        )
    }
    private var mCurrentSelectPosition: Int = 0;

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CheckResult")
    override fun initActivity(savedInstanceState: Bundle?) {
        // 使用 flow
//        initUIStateObserver(mMainViewModel)

        mBindingView.apply {
            viewPager.apply {
                adapter = mViewPagerAdapter
                offscreenPageLimit = mViewPagerAdapter.itemCount
                isUserInputEnabled = false
            }

            for ((index, value) in mBottomTitle.withIndex()) {
                mTabEntities.add(TabEntity(value, mIconSelectIds[index], mIconUnselectIds[index]))
            }

            tablayout.apply {
                setTabData(mTabEntities)
                for ((index, value) in mBottomTitle.withIndex()) {
                    val titleView = getTitleView(index)
                    titleView.typeface =
                        ResourcesCompat.getFont(this@MainActivity, R.font.poppins_regular)
                }
                setOnTabSelectListener(object : OnTabSelectListener {
                    override fun onTabSelect(position: Int) {
                        mCurrentSelectPosition = position
                        viewPager.setCurrentItem(mCurrentSelectPosition, false)
                    }
                    override fun onTabReselect(position: Int) {}

                })
            }

            LiveEventBus.get(CommonLiveBusEvent::class.java)
                .observeForever {
                    if (it.mEventType == CommonLiveBusEvent.token_is_invalid) {
                        val intent = Intent(this@MainActivity, LoginActivity::class.java)
                            .apply {
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                        startActivity(intent)
                    }
                }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("position", mCurrentSelectPosition)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mCurrentSelectPosition = savedInstanceState.getInt("position")
        if (mTabEntities.size > 0) {
            mBindingView.apply {
                viewPager.currentItem = mCurrentSelectPosition
                tablayout.currentTab = mCurrentSelectPosition

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ToastUtils.showLong("MainAct onActivityResult")
        mViewPagerAdapter.createFragment(3).onActivityResult(requestCode,resultCode,data)
    }
}