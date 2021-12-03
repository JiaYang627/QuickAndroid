package com.jiayang.quickandroid.ui.profile

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.blankj.utilcode.util.*
import com.iappsasia.industry_android.base.LCoilEngine
import com.jiayang.quickandroid.R
import com.jiayang.quickandroid.base.BaseLazyFragment
import com.jiayang.quickandroid.databinding.FragmentProfileBinding
import com.jiayang.quickandroid.ui.main.MainActivity
import com.permissionx.guolindev.PermissionX
import com.yalantis.ucrop.UCrop
import dagger.hilt.android.AndroidEntryPoint
import top.limuyang2.photolibrary.LPhotoHelper
import top.limuyang2.photolibrary.util.LPPImageType

/**
 * @author ：张 奎
 * @date ：2021-11-17 15：41
 * 邮箱   ：JiaYang627@163.com
 */
@AndroidEntryPoint
class ProfileFragment : BaseLazyFragment<FragmentProfileBinding>() {

    companion object{
        val CHOOSE_PHOTO_REQUEST = 100
    }
    // use hilt
    private val mViewModel : ProfileViewModel by viewModels()

    override fun initLazyFragment() {
        mBindingView.apply {
            includeTitleBarLayout.let {
                BarUtils.addMarginTopEqualStatusBarHeight(it.commonTitleBarRoot)
                it.titleBarCenterTitleText.text = "Profile"
                it.titleBarRightBellImg.let {
                    it.visibility = View.VISIBLE
                    it.load(R.drawable.icon_bell_white)
                }
            }

            permissionText.apply {
                setOnClickListener {
                    onCheckCameraPermission()
                }
            }

            chooseImage.apply {
                setOnClickListener {
                    onCheckPermission()
                }
            }

            testToken.apply {
                setOnClickListener {
                    (activity as MainActivity).mMainViewModel.searchPlace()
                }
            }

            normalImage.apply {
                load(R.drawable.ic_launcher_background)
            }
            circleImage.apply {
                load(R.drawable.ic_launcher_background){
                    transformations(CircleCropTransformation())
                }
            }
            roundedImage.apply {
                load(R.drawable.ic_launcher_background){
                    transformations(RoundedCornersTransformation(10f))
                }
            }
            blurlImage.apply {
                load(R.drawable.ic_launcher_background){
                    transformations(BlurTransformation(context,20f))
                }
            }
            graylImage.apply {
                load(R.drawable.ic_launcher_background){
                    transformations(GrayscaleTransformation())
                }
            }




        }
    }

    private fun onCheckCameraPermission() {
        PermissionX.init(this)
            .permissions(Manifest.permission.CAMERA)
            .explainReasonBeforeRequest()
            .onExplainRequestReason { scope, deniedList ->
                val message = "Industry needs following permissions to continue"
                scope.showRequestReasonDialog(deniedList, message, "I See", "No")
            }
            .onForwardToSettings { scope, deniedList ->
                val message = "You following permissions to continue"
                scope.showForwardToSettingsDialog(deniedList, message, "I See", "No")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    ToastUtils.showLong("测试相机的权限已获取")
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                CHOOSE_PHOTO_REQUEST -> {
                    val selectedPhotos = LPhotoHelper.getSelectedPhotos(data)
                    if (selectedPhotos.size == 1) {

                        val mFilePath = PathUtils.getInternalAppCachePath() + "/${System.currentTimeMillis()}.jpg"
                        val createOrExistsFile =
                            FileUtils.createOrExistsFile(mFilePath)
                        if (createOrExistsFile) {
                            val file2Uri =
                                Uri.fromFile(FileUtils.getFileByPath(mFilePath))
                            UCrop.of(selectedPhotos[0], file2Uri)
                                .withAspectRatio(1f, 1f)
                                .withMaxResultSize(800, 800)
                                .start(activity as MainActivity)
                        }
                    }
                }
                UCrop.REQUEST_CROP -> {
                    data?.let { it ->
                        val output = UCrop.getOutput(it)
                        mBindingView.apply {
                            normalImage.apply {
                                load(output)
                            }
                            circleImage.apply {
                                load(output){
                                    transformations(CircleCropTransformation())
                                }
                            }
                            roundedImage.apply {
                                load(output){
                                    transformations(RoundedCornersTransformation(10f))
                                }
                            }
                            blurlImage.apply {
                                load(output){
                                    transformations(BlurTransformation(context,20f))
                                }
                            }
                            graylImage.apply {
                                load(output){
                                    transformations(GrayscaleTransformation())
                                }
                            }
                        }
                        return@let

//                        downloadImage(output, onSuccess = {
//                            // 保存到相册
//                            ImageUtils.save2Album(it, Bitmap.CompressFormat.JPEG)
//
//                        })
                    }
                }
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            data?.let {
                val output: Throwable? = UCrop.getError(it)
                LogUtils.eTag("JiaYang", output.toString())
            }
        }
    }


    private fun onCheckPermission() {
        val requestList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            arrayListOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
        } else {
            arrayListOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        PermissionX.init(this)
            .permissions(requestList)
            .explainReasonBeforeRequest()
            .onExplainRequestReason { scope, deniedList ->
                val message = "Industry needs following permissions to continue"
                scope.showRequestReasonDialog(deniedList, message, "I See", "No")
            }
            .onForwardToSettings { scope, deniedList ->
                val message = "You following permissions to continue"
                scope.showForwardToSettingsDialog(deniedList, message, "I See", "No")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    LPhotoHelper.Builder()
                        .maxChooseCount(1)
                        .columnsNumber(3)
                        .imageType(LPPImageType.ofAll())
                        .imageEngine(LCoilEngine())
                        .build()
                        .start(this, CHOOSE_PHOTO_REQUEST)
                }
            }

    }

}