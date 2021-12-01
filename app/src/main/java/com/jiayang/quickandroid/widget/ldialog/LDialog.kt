package com.iappsasia.industry_android.widget.ldialog

import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import com.iappsasia.industry_android.widget.ldialog.base.BaseLDialog
import com.iappsasia.industry_android.widget.ldialog.base.ViewHandlerListener

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-25 16：33
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class LDialog : BaseLDialog<LDialog>() {

    override fun layoutRes(): Int = 0

    override fun layoutView(): View? = null

    override fun viewHandler(): ViewHandlerListener? {
        return null
    }

    fun setLayoutRes(@LayoutRes layoutRes: Int): LDialog {
        baseParams.layoutRes = layoutRes
        return this
    }

    fun setLayoutView(view: View): LDialog {
        baseParams.view = view
        return this
    }

    fun setViewHandlerListener(viewHandlerListener: ViewHandlerListener): LDialog {
        this@LDialog.viewHandlerListener = viewHandlerListener
        return this
    }

    companion object {
        fun init(fragmentManager: FragmentManager): LDialog {
            val dialog = LDialog()
            dialog.setFragmentManager(fragmentManager)
            return dialog
        }
    }
}