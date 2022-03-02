package com.jiayang.quickandroid.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.jiayang.quickandroid.R;

/**
 * @author ：张 奎
 * @date ：2018-12-12 13：50
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class CommonDialog {
    protected Activity m_Context;
    private Dialog dialog;

    public CommonDialog(Activity context) {
        this.m_Context = context;
        initDialog();
    }

    private void initDialog() {
        dialog = new Dialog(m_Context, R.style.commonDialog);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.dialoganimationstyle); // 添加动画
    }

    /**
     * 设置点击外部 是否取消弹框
     * @param cancel
     */
    public void setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
    }

    public void setBackOutside(boolean cancel) {
        dialog.setCancelable(cancel);
    }

    protected final Dialog getDialog() {
        if (dialog == null)
            initDialog();
        return dialog;
    }

    public final boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }

    public final void dismissDialog() {
        if (isShowing()) dialog.dismiss();
    }

    public void setContentView(View contentView) {
        int width = ScreenUtils.getScreenWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width * 3 / 4, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setContentView(contentView, params);
    }

    public void show() {
        dialog.show();
    }
}
