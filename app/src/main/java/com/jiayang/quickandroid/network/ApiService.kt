package com.jiayang.quickandroid.network

import com.iappsasia.industry_android.network.BaseResultData
import retrofit2.http.*

/**
 * @author ：张 奎
 * @date ：2020-04-29 15：43
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
interface ApiService {


    @POST("account_service/admin/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("panel_type") panel_type: String = "panel_type"
    ): BaseResultData<String>

}