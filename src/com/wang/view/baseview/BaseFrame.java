package com.wang.view.baseview;

import com.wang.utils.ImageData;

import javax.swing.*;

/**
 * 定义一个基础窗口框架
 */
public class BaseFrame extends JFrame {
    public BaseFrame(String title,int width,int height){
        setTitle(title); //窗口标题
        setSize(width,height); //窗口宽高
        setIconImage(ImageData.bi); //窗口图标
        setResizable(false); //窗口大小不可改变
        setLocationRelativeTo(null); //窗口中央弹出
    }
}
