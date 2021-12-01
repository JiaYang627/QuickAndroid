package com.iappsasia.industry_android.base

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import coil.load
import top.limuyang2.photolibrary.engine.LImageEngine
import top.limuyang2.photolibrary.util.LPPImageType

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-12-01 09：12
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
class LCoilEngine :LImageEngine {
    override fun load(
        context: Context,
        imageView: ImageView,
        uri: Uri?,
        imageType: LPPImageType?,
        placeholderRes: Int,
        resizeX: Int,
        resizeY: Int
    ) {
        imageView.load(uri){
            placeholder(placeholderRes)
        }
    }

    // pauseOnScroll = true  ,must overwrite
    override fun pause(context: Context) {
    }
    // pauseOnScroll = true  ,must overwrite
    override fun resume(context: Context) {
    }
}