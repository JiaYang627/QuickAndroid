package com.jiayang.quickandroid.base

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import coil.Coil
import coil.request.ImageRequest
import com.blankj.utilcode.util.ImageUtils

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-30 14：18
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */


fun <T : FragmentActivity> T.downloadImage(
    imagePath: Any?,
    onSuccess: (result: Bitmap) -> Unit,
    onError: () -> Unit = {}
) {
    val build = ImageRequest.Builder(this)
        .data(imagePath)
        .target(onSuccess = {
            onSuccess(ImageUtils.drawable2Bitmap(it))
        }, onError = {}
        )
        .build()
    Coil.enqueue(build)
}
fun <T : Fragment> T.downloadImage(
    imagePath: Any?,
    onSuccess: (result: Bitmap) -> Unit,
    onError: () -> Unit = {}
) {
    val build = context?.let {
        ImageRequest.Builder(it)
        .data(imagePath)
        .target(onSuccess = {
            onSuccess(ImageUtils.drawable2Bitmap(it))
        }, onError = {}
        )
        .build()
    }
    build?.let { Coil.enqueue(it) }
}