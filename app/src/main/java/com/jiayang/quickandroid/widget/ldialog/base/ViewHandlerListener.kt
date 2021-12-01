package com.iappsasia.industry_android.widget.ldialog.base

import android.os.Parcel
import android.os.Parcelable

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-25 16：37
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
abstract class ViewHandlerListener : Parcelable {

    abstract fun convertView(holder: ViewHolder, dialog: BaseLDialog<*>)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
    }

    constructor()

    protected constructor(source: Parcel)

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ViewHandlerListener> = object : Parcelable.Creator<ViewHandlerListener> {
            override fun createFromParcel(source: Parcel): ViewHandlerListener {
                return object : ViewHandlerListener(source) {
                    override fun convertView(holder: ViewHolder, dialog: BaseLDialog<*>) {

                    }
                }
            }

            override fun newArray(size: Int): Array<ViewHandlerListener?> {
                return arrayOfNulls(size)
            }
        }
    }
}