package com.jiayang.quickandroid.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.iappsasia.industry_android.base.createViewBindingForFragment
import com.iappsasia.industry_android.widget.ldialog.LDialog
import com.jiayang.quickandroid.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author ：张 奎
 * @date ：2020-04-28 17：20
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment(), CoroutineScope by MainScope() {

    private var mBinding: VB? = null
    private var mLoadingDialog : LDialog? = null
    val mLoadingView : LDialog get() = mLoadingDialog!!

    val mBindingView :VB get() = mBinding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = createViewBindingForFragment(layoutInflater,container)
        return if (mBinding != null) {
            mBinding!!.root.apply { (parent as? ViewGroup)?.removeView(this) }
        } else super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitLoadDialogView()
        initFragment(view, savedInstanceState)
    }
    private fun onInitLoadDialogView() {
        mLoadingDialog = LDialog.init(childFragmentManager)
            .setLayoutRes(R.layout.common_loading_layout)
            .setGravity(Gravity.CENTER)
            .setWidthScale(0.55f)
            .setCancelableOutside(false)
    }

    abstract fun initFragment(view: View, savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        cancel()
        mBinding = null
        mLoadingDialog = null
    }
}