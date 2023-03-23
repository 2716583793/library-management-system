package com.wang.view.minorframe;

import com.wang.utils.ImageData;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import java.awt.*;

/**
 * 关于我们界面
 */
public class InfoFrame extends MinorFrame {

    public InfoFrame() {
        super("关于我们",840,590);
        initFrame();
    }

    private void initFrame(){
        JPanel jp = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageData.info.paintIcon(this, g, 0, 0);
            }
        };
        jp.setLayout(null);
        JLabel jl1 = new JLabel("欢迎使用图书管理系统！");
        jl1.setBounds(20,20,600,50);
        jl1.setFont(new Font("方正舒体", Font.PLAIN, 40));
        JLabel jl2 = new JLabel("工作邮箱：xxx");
        jl2.setBounds(20,70,600,50);
        jl2.setFont(new Font("方正舒体", Font.PLAIN, 40));
        jp.add(jl1);
        jp.add(jl2);
        setContentPane(jp);
    }
}
