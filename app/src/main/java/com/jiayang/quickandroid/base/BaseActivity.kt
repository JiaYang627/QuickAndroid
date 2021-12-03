package com.jiayang.quickandroid.base

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import cn.jiayang.kotlinstudyjetpack.base.ActivityStackManager
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LanguageUtils
import com.iappsasia.industry_android.base.createViewBindingForAct
import com.iappsasia.industry_android.widget.ldialog.LDialog
import com.jiayang.quickandroid.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author ：张 奎
 * @date ：2020-04-28 14：27
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), CoroutineScope by MainScope(){

    private var mBinding : VB?= null
    val mBindingView :VB get() = mBinding!!

    private var mLoadingDialog : LDialog? = null
    val mLoadingView : LDialog get() = mLoadingDialog!!

    override fun attachBaseContext(newBase: Context?) {
        LanguageUtils.attachBaseContext(newBase)
        super.attachBaseContext(newBase)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 使用的 ViewBinding 没有使用 DataBinding
        // 反射机制 虽说反射会影响性能  早期使用 ButterKnife  也会影响性能 不是依旧使用了 ？
        mBinding= createViewBindingForAct(layoutInflater)
        setContentView(mBinding!!.root)
        BarUtils.setStatusBarColor(this,resources.getColor(R.color.common_blue))
        if (!BarUtils.isStatusBarVisible(this)) {
            BarUtils.setStatusBarVisibility(this,true)
        }
        onInitLoadDialogView()
        initActivity(savedInstanceState)
        ActivityStackManager.addCurrentAct(this)
    }

    private fun onInitLoadDialogView() {
        mLoadingDialog = LDialog.init(supportFragmentManager)
            .setLayoutRes(R.layout.common_loading_layout)
            .setGravity(Gravity.CENTER)
            .setWidthScale(0.55f)
            .setCancelableOutside(false)
    }

    abstract fun initActivity(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()

        ActivityStackManager.removeActivity(this)
        cancel()
        mBinding = null
        mLoadingDialog = null
    }

    inline fun<reified T :ViewModel> setViewModel(block :() -> ViewModelProvider.NewInstanceFactory) :T{
         return ViewModelProvider(this, block()).get(T::class.java)
    }

    override fun getResources(): Resources = AdaptScreenUtils.adaptWidth(super.getResources(), 1080)

    fun startActivity(
        clazz: Class<*>,
        mCurrentFinish: Boolean? = false,
        mBundle: Bundle? = null
    ) {

        val intent = Intent(this, clazz)
        if (mBundle != null) {
            intent.putExtras(mBundle)
        }
        this.startActivity(intent)
        if (mCurrentFinish!!) {
            finish()
        }
    }



}