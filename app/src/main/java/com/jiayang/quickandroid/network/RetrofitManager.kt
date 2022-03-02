package cn.jiayang.kotlinstudyjetpack.network

import com.jiayang.quickandroid.network.ApiService
import com.jiayang.quickandroid.network.CommonHeaderInterceptor
import com.jiayang.quickandroid.network.TokenInterceptor
import com.jiayang.quickandroid.BuildConfig
import com.jiayang.quickandroid.base.genderMoshiBuild
import com.jiayang.quickandroid.network.HttpHeaders
import com.jiayang.quickandroid.utils.HttpsUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author ：张 奎
 * @date ：2020-04-29 15：36
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
object RetrofitManager {


//    private const val BASE_URL ="https://api.caiyunapp.com/"
    private const val BASE_URL ="http://13.229.238.191/"

    private val retrofit =Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(genderMoshiBuild()))
        .client(genericOkClient())
        .build()


    private fun genericOkClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {

                    // 如果是 json 格式内容则打印 json
                    if ((message.startsWith("{") && message.endsWith("}")) ||
                        (message.startsWith("[") && message.endsWith("]"))
                    )
                        com.blankj.utilcode.util.LogUtils.e(message)
//                    else
//                        com.blankj.utilcode.util.LogUtils.e(message)
                }
            })

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        //全局设置请求头
        //全局设置请求头
        val headers = HttpHeaders()
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
        //headers.put("Accept-Encoding", "gzip, deflate") //如果加这一句 log会乱码
        headers.put("Connection", "keep-alive")
        headers.put("Accept", "*/*")

        val sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null)


        return OkHttpClient.Builder()
            .connectTimeout(30_000L, TimeUnit.MILLISECONDS)
            .readTimeout(30_000, TimeUnit.MILLISECONDS)
            .writeTimeout(30_000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(CommonHeaderInterceptor(headers))
            .addInterceptor(TokenInterceptor())
            .sslSocketFactory(sslSocketFactory.sSLSocketFactory,sslSocketFactory.trustManager)
            .build()
    }


    fun <T> create(service: Class<T>) :T = retrofit.create(service)

    inline fun <reified T> createService() :T = create(T::class.java)

    val apiService : ApiService = retrofit.create(ApiService::class.java)

}