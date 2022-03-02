package com.jiayang.quickandroid.entity

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-12-22 15：43
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
sealed class UIState {

    class LoadingAni(val isShow : Boolean ,val message : String? = null) : UIState()
    class ShowMessage(val message : String? = "No Message") : UIState()

}