package com.jiayang.quickandroid.ui.login

import android.graphics.Color
import android.os.Bundle
import android.text.TextPaint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import coil.load
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SpanUtils
import com.iappsasia.industry_android.ui.login.LoginFactory
import com.iappsasia.industry_android.ui.login.LoginRepository
import com.iappsasia.industry_android.ui.login.LoginViewModel
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseActivity
import com.jiayang.quickandroid.databinding.ActivityLoginBinding
import com.jiayang.quickandroid.ui.main.MainActivity

/**
 * @author ：张 奎
 * @date ：2021-11-23 14：25
 * 邮箱   ：JiaYang627@163.com
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {


    private var mCurrentPasswordIsShow = false

    private val mLoginViewModel: LoginViewModel by viewModels {
        LoginFactory(LoginRepository())
    }

    override fun initActivity(savedInstanceState: Bundle?) {

        BarUtils.setStatusBarColor(this, Color.argb(0, 0, 0, 0),true)
        mBindingView.let { it_ ->

            it_.loginEditPasswordImg.setOnClickListener {
                mCurrentPasswordIsShow = !mCurrentPasswordIsShow
                it as ImageView
                when (mCurrentPasswordIsShow) {
                    true -> {
                        it.load(R.drawable.icon_login_password_visible)
                        it_.loginEditPassword.run {
                            transformationMethod = HideReturnsTransformationMethod.getInstance()
                            setSelection(text.length)
                        }
                    }
                    else -> {
                        it.load(R.drawable.icon_login_password_hidden)
                        it_.loginEditPassword.run {
                            transformationMethod = PasswordTransformationMethod.getInstance()
                            setSelection(text.length)
                        }
                    }
                }
            }


            it_.loginForgetPasswordView.let {
                SpanUtils.with(it)
                    .appendLine("Forget password")
                    .setUnderline()
                    .setClickSpan(object : ClickableSpan() {
                        override fun onClick(widget: View) {
//                            startActivity(LoginForgetActivity::class.java)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.color = Color.BLACK
                        }
                    })
                    .create()
            }
            it_.loginBottomLoginView.setOnClickListener {
                startActivity(MainActivity::class.java,true)
            }


        }
    }
}