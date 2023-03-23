package com.wang.view.baseview;

/**
 * 功能窗口，关闭释放资源，但不退出程序
 */
public class MinorFrame extends BaseFrame {
    public MinorFrame(String title, int width, int height) {
        super(title, width, height); //传入参数
        //关闭释放本窗口，不退出程序
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
