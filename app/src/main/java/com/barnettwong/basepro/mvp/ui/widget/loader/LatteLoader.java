package com.barnettwong.basepro.mvp.ui.widget.loader;


import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.barnettwong.basepro.R;
import com.barnettwong.basepro.mvp.ui.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * 加载框的管理类
 */
public class LatteLoader {
    //样式大小比例
    private static final int LOADER_SIZE_SCALE = 8;
    //样式的偏移量
    private static final int LOADER_OFFSET_SCALE = 10;
    //默认样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallSpinFadeLoaderIndicator.name(); //LoaderStyle.BallSpinFadeLoaderIndicator


    public static AppCompatDialog getLoading(Context context) {
        // 创建加载框来承载起来, 创建一个dialog 来是灰色 透明
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        //拿到我的封装好的IndicatorView
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(DEFAULT_LOADER, context);
        //设置上去
        dialog.setContentView(avLoadingIndicatorView);
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        return dialog;
    }


    public static void showLoading(AppCompatDialog dialog) {
        if (null != dialog) {
            dialog.show();
        }
    }

    /**
     * 关闭loading
     */
    public static void dismissLoading(AppCompatDialog dialog) {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.cancel();
            }
        }
    }

}
