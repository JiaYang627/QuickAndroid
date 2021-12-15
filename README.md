# QuickAndroid
> 写在前面：搭建此项目 目的为了学习Kotlin 以及相关第三方库(用到的协程都是最基础的，没有使用Flow等),公司项目一直为Java ,没有Kotlin 实战的机会,并且一直在开发h5 php 抽不开身系统的学习Kotlin
> 碰巧另一项目组需要开发新App,这才有了Kotlin实战的机会,搭建了一个简单的基础框架用于后续开发快速使用。
> 此项目前3个页面为此次项目第一版UI，最后一个页面 `Profile` 演示了 请求权限、请求相册、模拟Token失效、Coil的使用
### 项目基础架构

* 网络 ：Retrofit + Okhttp + Coroutines
* UI绑定 ：[ViewBinding](https://developer.android.com/topic/libraries/view-binding)
* 数据解析 ：[Moshi](https://github.com/square/moshi)
* 消息通知 ：[LifeEventBus](https://github.com/JeremyLiao/LiveEventBus)
* 图片加载 ：[coil](https://github.com/coil-kt/coil)
* 屏幕适配  : [屏幕适配终结者](https://www.jianshu.com/p/7da141e682c7)
* Permission : [PermissionX](https://github.com/guolindev/PermissionX)
* ImageCrop : [UCrop](https://github.com/Yalantis/uCrop)
* ImageChoose : [LPhotoPicker](https://github.com/limuyang2/LPhotoPicker)
* Data storage : [MMKV](https://github.com/Tencent/MMKV)
* 依赖注入 : [Hilt](https://developer.android.com/training/dependency-injection)

#### 注意
项目中用了自定义阴影布局，如果未运行 可能xml中不显示具体效果，运行后在看即可查看效果

运行项目后 Login页面无需输入账号密码，点击登陆按钮即可跳转首页

项目使用了[BlankJ](https://github.com/Blankj) 的 `AdaptScreenUtils`,遂项目中 xml 开发都是使用pt为单位，且前3个Fragment UI 均为公司项目第一版UI
按照`AdaptScreenUtils`的使用，在BaseActivity 中对宽度进行了配置,所以，在查看布局xml的时候 在布局 split 页面下，
勾选创建的Virtual Devices,创建Virtual Devices 尺寸参考下图

![Virtual Devices](https://github.com/JiaYang627/QuickAndroid/blob/main/pic/img.png)


#### Template 的使用
项目对应的一键生成页面插件,项目地址 [TinMVVM](https://github.com/JiaYang627/TinMVVM),编译好的插件在此项目 `template`文件夹下,导入AS插件即可使用

说几点使用事项
* 点击`app` 或者 包名 右键 - New - other
* 使用的时候 `Generate Activity` 默认为勾选状态，如果不想生成Act ，需要先去掉 `Generate Activity` 的勾选，
  在勾选 `Generate Fragment`，别问为啥，问就是你在这给我刷bug呢 ？我也想做联动啥的，但是插件代码拿不到值变化监听。
* 填写`Activity Package Name` 或者 `Fragment Package Name` 的时候 ，我测试过二级目录，比如`ui.xxx`，结果发现不会合并，1.0.74 版本修复此问题 但是必须使用/，PS：`ui/xxx`,如果你依旧使用`ui.xxx`我也不拦你。
  创建后 会直接在ui 文件夹下生成对应的文件。
* 生成Act时 会在项目清单文件 AndroidManifest 创建对应Act标签，但是属性有点少，需要自己在编辑添加。
* 插件是给自己使用的，如果你作为开发像用户那样在这里测试插件，那你还做什么开发啊。我也想过如何解决，但是编写插件代码没有方法支持啊。我也很无奈。
  编写插件目的就是为了自己的使用，知道怎么用 何必考虑那么复杂情况呢 ？
  
![TemplateUse](https://github.com/JiaYang627/QuickAndroid/blob/main/pic/TemplateUse.png)

![TemplateDetails](https://github.com/JiaYang627/QuickAndroid/blob/main/pic/TemplateDetails.png)

#### Hilt
03-12-2021 添加Hilt
官方文档请看英语版，切换页面语言为中文 会发现依赖不是最新版，和当前项目依赖不一样。
如果英语版看不懂，推荐 看一遍[郭霖](https://guolin.blog.csdn.net/) 的文章 [带你玩转Hilt和依赖注入](https://blog.csdn.net/guolin_blog/article/details/109787732?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522163850054516780269881756%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=163850054516780269881756&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_v2~rank_v29-1-109787732.pc_v2_rank_blog_default&utm_term=%E6%B3%A8%E5%85%A5&spm=1018.2226.3001.4450)
学习了解一下什么是Hilt ,然后再在官网中查看一些更新的注解 具体为 :[作用域 Scope](https://developer.android.com/training/dependency-injection/hilt-android#component-scopes) 以及最新的 [配合ViewModel使用](https://developer.android.com/training/dependency-injection/hilt-jetpack)


#### 网络配置 --> RetrofitManager

此类下进行统一网络配置，包括Retrofit、OkHttp以及OkHttp涉及到的日志打印配置、头信息等拦截器配置项

#### BuildGradle配置

Project - build.gradle apply 'config.gradle'

app - build.gradle use 'config.gradle'

项目依赖了[BlankJ](https://github.com/Blankj) 的 [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode),基本涵盖了所有相关Utils。

#### 项目分层

* base      : 相关基类配置
* entity    : 实体类
* network   : 网络配置
* ui        : 页面
    * modelfolder   : 模块名文件夹
        * modelAct
        * modelViewModel
        * modelViewModelFactory
* utils     : 自写utils
* widget    : 自定义view

#### LiveEventBus 使用

use `CommonLiveBusEvent`(app - package - base - CommonLiveBusEvent)
Profile 中测试Token 失效，在Retrofit + OKHttp 的 TokenInterceptor 中有展示 ,MainActivity 中接收

```
// post
LiveEventBus.get(CommonLiveBusEvent::class.java)
            .post(CommonLiveBusEvent(CommonLiveBusEvent.market_select_currency,"1"))
                                
// observer
LiveEventBus.get(CommonLiveBusEvent::class.java)
            .observe(this@WatchListFragment){
                    it.mEventObject as String
                    ToastUtils.showLong("收到的消息是${it.mEventObject}")
                }
```

#### Coil - DownImage
use `CoilDownloadImage.kt`(app - package - base - CoilDownloadImage) - `downloadImage` method

#### PermissionX 使用

需要注意 android R(11 version_sdk = 30) 申请读写权限时 使用 `MANAGE_EXTERNAL_STORAGE` ，并注意，这个权限
属于特殊权限，使用PermissionX时不会显示在普通权限列表中

个人建议还是判断一下版本号
```
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
                    ...
                }
            }
```

#### ImageChoose & UCrop
custom Engine - `LCoilEngine`
```
LPhotoHelper.Builder()
            .maxChooseCount(1)
            .columnsNumber(3)
            .imageType(LPPImageType.ofAll())
            .imageEngine(LCoilEngine())
            .build()
            .start(this, CHOOSE_PHOTO_REQUEST)

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
                                .start(this)
                        }
                    }
                }
                UCrop.REQUEST_CROP -> {
                    data?.let { it ->
                        val output = UCrop.getOutput(it)
                        // 下载图片 并保存到相册中
                        downloadImage(output, onSuccess = {
                            ImageUtils.save2Album(it, Bitmap.CompressFormat.JPEG)
                        })
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
```