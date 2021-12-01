package com.iappsasia.industry_android.widget.ldialog.base

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable

/**
 * @author ：Tom Zhang - Android Developer
 * @date ：2021-11-25 16：37
 * 邮箱   ：JiaYang627@163.com / Tom@iappsasia.com
 */
abstract class OnDialogDismissListener : DialogInterface.OnDismissListener, Parcelable {

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    constructor()

    protected constructor(source: Parcel)

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnDialogDismissListener> = object : Parcelable.Creator<OnDialogDismissListener> {
            override fun createFromParcel(source: Parcel): OnDialogDismissListener {
                return object : OnDialogDismissListener(source) {
                    override fun onDismiss(dialog: DialogInterface) {

                    }
                }
            }

            override fun newArray(size: Int): Array<OnDialogDismissListener?> {
                return arrayOfNulls(size)
            }
        }
    }
}