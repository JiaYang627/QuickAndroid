package cn.jiayang.kotlinstudyjetpack.base

import android.app.Activity

/**
 * @author ：张 奎
 * @date ：2020-04-29 09：18
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
object ActivityStackManager {


    private val mActivities = mutableListOf<Activity>()

    @JvmStatic
    fun addCurrentAct(activity: Activity) {

        mActivities.add(activity)
    }


    @JvmStatic
    fun removeActivity(activity: Activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity)
            activity.finish()
        }
    }


    @JvmStatic
    fun getTopActivity() : Activity? =
        if (mActivities.isEmpty()) null else mActivities[mActivities.size - 1]
}