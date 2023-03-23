package com.wang.view.minorframe;

import com.wang.bean.User;
import com.wang.service.UserService;
import com.wang.service.impl.UserServiceImpl;
import com.wang.utils.ImageData;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 用户注册界面
 */
public class RegisterFrame extends MinorFrame {

    private  JTextField jtf;
    private  JPasswordField jpf1, jpf2;
    private  JButton jb1, jb3;
    private final UserService userService = new UserServiceImpl();

    public RegisterFrame() {
        super("注册框", 500, 380);
        initFrame();
    }

    private void initFrame(){
        JPanel jp = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageData.bg.paintIcon(this, g, 0, 0);
            }
        };
        jp.setLayout(null);

        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        jl1.setText(" 用  户  名：");
        jl1.setIcon(ImageData.un);
        jl2.setText(" 密        码：");
        jl2.setIcon(ImageData.pw);
        jl3.setText(" 确认密码：");
        jl3.setIcon(ImageData.pw);
        jl1.setBounds(60, 45, 165, 25);
        jl2.setBounds(60, 100, 165, 25);
        jl3.setBounds(60, 155, 165, 25);
        jl1.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jl2.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jl3.setFont(new Font("方正舒体", Font.PLAIN, 25));


        jtf = new JTextField();
        jpf1 = new JPasswordField();
        jpf2 = new JPasswordField();
        jtf.setBounds(225, 48, 200, 25);
        jtf.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jpf1.setBounds(225, 103, 200, 25);
        jpf1.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jpf1.setEchoChar('\u2605');
        jpf2.setBounds(225, 158, 200, 25);
        jpf2.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jpf2.setEchoChar('\u2605');

        jb1 = new JButton("确认");
        JButton jb2 = new JButton("取消");
        jb3 = new JButton("重置");
        jb1.setBounds(70, 230, 80, 40);
        jb1.setFont(new Font("方正舒体", Font.PLAIN, 20));
        jb2.setBounds(200, 230, 80, 40);
        jb2.setFont(new Font("方正舒体", Font.PLAIN, 20));
        jb3.setBounds(330, 230, 80, 40);
        jb3.setFont(new Font("方正舒体", Font.PLAIN, 20));

        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    jpf1.grabFocus();
                }
            }
        });

        jpf1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    jpf2.grabFocus();
                }
            }
        });

        jpf2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    jb1.doClick();
                }
            }
        });

        jp.add(jl1);
        jp.add(jl2);
        jp.add(jl3);
        jp.add(jtf);
        jp.add(jpf1);
        jp.add(jpf2);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);

        jb1.addActionListener(this::registerAction);
        jb2.addActionListener(this::cancelAction);
        jb3.addActionListener(this::resetAction);

        setContentPane(jp);
    }

    //注册按钮
    private void registerAction(ActionEvent e) {
        String username = jtf.getText().trim();
        String password1 = String.valueOf(jpf1.getPassword()).trim();
        String password2 = String.valueOf(jpf2.getPassword()).trim();
        if ("".equals(username)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if ("".equals(password1)) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if ("".equals(password2)) {
            JOptionPane.showMessageDialog(null, "请确认你的密码！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致！", "警告", JOptionPane.WARNING_MESSAGE);
            jb3.doClick();
            return;
        }

        if ("root".equals(username)) {
            JOptionPane.showMessageDialog(null, "用户名已存在！", "错误", JOptionPane.ERROR_MESSAGE);
            jb3.doClick();
            return;
        }

        User user = new User(username, password1);

        boolean isSuccess = userService.registerUser(user);
        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "注册成功！");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "用户名已存在！", "错误", JOptionPane.ERROR_MESSAGE);
            jb3.doClick();
        }
    }

    //取消按钮
    private void cancelAction(ActionEvent e) {
        this.dispose();
    }

    //重置按钮
    private void resetAction(ActionEvent e) {
        jtf.setText("");
        jpf1.setText("");
        jpf2.setText("");
    }
}
