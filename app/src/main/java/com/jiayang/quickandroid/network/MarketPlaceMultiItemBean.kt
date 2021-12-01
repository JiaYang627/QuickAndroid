package com.jiayang.quickandroid.network

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-26 11：30
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class MarketPlaceMultiItemBean(override val itemType: Int) :MultiItemEntity {


    var mTitleStr: String = ""

    var mContentImgStr :String = ""
    var mContentLeftTopStr :String = ""
    var mContentLeftBottomStr :String = ""
    var mContentRightTopStr :String = ""
    var mContentRightMiddleStr :String = ""
    var mContentRightBottomStr :String = ""

}