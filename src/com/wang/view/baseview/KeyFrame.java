package com.wang.view.baseview;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 主要窗口，询问是否关闭
 */
public class KeyFrame extends BaseFrame {
    public KeyFrame(String title, int width, int height) {
        super(title, width, height); //传入参数
        //窗口不执行关闭
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //添加窗口监听
        addWindowListener(new WindowAdapter() {
            //窗口点击关闭时，询问是否退出
            @Override
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "是否退出？");
                if (i == 0) {
                    //选择是，则释放窗口，退出程序
                    dispose();
                    System.exit(1);
                }
            }
        });
    }
}
