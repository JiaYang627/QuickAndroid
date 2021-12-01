package com.jiayang.quickandroid.ui.login

import android.os.Bundle
import android.view.KeyEvent
import com.blankj.utilcode.util.BarUtils
import com.jiayang.quickandroid.base.Constant
import com.iappsasia.industry_android.base.delayLaunch
import com.jiayang.quickandroid.base.BaseActivity
import com.jiayang.quickandroid.databinding.ActivitySplashBinding
import com.jiayang.quickandroid.ui.main.MainActivity
import com.jiayang.quickandroid.utils.decodeString

/**
 * @author ：张 奎
 * @date ：2021-11-23 20：00
 * 邮箱   ：JiaYang627@163.com
 */
class SplashActivity: BaseActivity<ActivitySplashBinding>() {
    override fun initActivity(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarVisibility(this,false)
        delayLaunch(2000) {
            block={
                val decodeString = decodeString(Constant.USER_INFO_TOKEN, "")
                startActivity(if (decodeString.isEmpty()) LoginActivity::class.java else MainActivity::class.java ,true)
            }
        }


    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_BACK)
            true
        else super.onKeyDown(keyCode, event)
    }

}