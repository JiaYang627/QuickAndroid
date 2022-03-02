package com.jiayang.quickandroid.base

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import cn.jiayang.kotlinstudyjetpack.base.ActivityStackManager
import coil.ImageLoader
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LanguageUtils
import com.bumptech.glide.gifdecoder.GifDecoder
import com.iappsasia.industry_android.widget.ldialog.LDialog
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.databinding.CommonLoadingLayoutBinding
import com.jiayang.quickandroid.widget.CommonDialog
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


    private var mCommonDialog: CommonDialog? = null
    private var mIsFirst = true

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
    override fun onResume() {
        super.onResume()
        if (mIsFirst) {
            onOnceToApi()
            mIsFirst = false
        }
    }

    open fun onOnceToApi() {}

    private fun onInitLoadDialogView() {
        mCommonDialog = CommonDialog(this).apply {
            setContentView(
                CommonLoadingLayoutBinding.inflate(
                    LayoutInflater.from(this@BaseActivity)
                ).apply {
                    // 使用coil 加载 gif ，记得添加coil gif 的依赖
//                    val imageLoader = ImageLoader.Builder(this@BaseActivity).componentRegistry {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                            add(ImageDecoderDecoder(this@BaseActivity))
//                        } else {
//                            add(GifDecoder())
//                        }
//                    }
//                        .build()
//                    loadingImg.load(R.drawable.icon_loading,imageLoader)
                }.root
            )
        }
    }

    abstract fun initActivity(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()

        ActivityStackManager.removeActivity(this)
        cancel()
        mBinding = null
        mCommonDialog = null
    }

    inline fun<reified T :ViewModel> setViewModel(block :() -> ViewModelProvider.NewInstanceFactory) :T{
         return ViewModelProvider(this, block()).get(T::class.java)
    }

    override fun getResources(): Resources = AdaptScreenUtils.adaptWidth(super.getResources(), 1080)

    fun startActivity(
        clazz: Class<*>,
        mCurrentFinish: Boolean? = false,
        mBundle: Bundle.() -> Unit = {}
    ) {

        val intent = Intent(this, clazz).apply {
            val bundle = Bundle().apply(mBundle)
            putExtras(bundle)
        }
        this.startActivity(intent)
        if (mCurrentFinish!!) {
            finish()
        }
    }

    fun showLoading() {
        mCommonDialog?.show()
    }

    fun dismissLoading() {
        mCommonDialog?.dismissDialog()
    }



}