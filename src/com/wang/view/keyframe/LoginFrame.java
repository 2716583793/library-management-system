package com.wang.view.keyframe;

import com.wang.bean.Admin;
import com.wang.bean.User;
import com.wang.service.AdminService;
import com.wang.service.UserService;
import com.wang.service.impl.AdminServiceImpl;
import com.wang.service.impl.UserServiceImpl;
import com.wang.utils.ImageData;
import com.wang.view.baseview.KeyFrame;
import com.wang.view.minorframe.RegisterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 登录界面
 */
public class LoginFrame extends KeyFrame {

    private JTextField jtf;
    private JPasswordField jpf;
    private JRadioButton jrb1;
    private JRadioButton jrb2;
    private JCheckBox checkBox;
    private JButton jb1;
    private JButton jb3;
    private final UserService userService = new UserServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    public LoginFrame() {
        super("图书管理系统", 600, 430);
        initFrame();
    }

    private void initFrame() {
        JPanel jp = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageData.bg.paintIcon(this, g, 0, 0);
            }
        };
        jp.setLayout(null);

        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        jl1.setText(" 用户名：");
        jl1.setIcon(ImageData.un);
        jl2.setText(" 密    码：");
        jl2.setIcon(ImageData.pw);
        jl1.setBounds(140, 75, 135, 25);
        jl2.setBounds(140, 135, 135, 25);
        jl1.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jl2.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jtf = new JTextField();
        jpf = new JPasswordField();
        jtf.setBounds(280, 78, 200, 25);
        jtf.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jpf.setBounds(280, 138, 200, 25);
        jpf.setFont(new Font("方正舒体", Font.PLAIN, 25));
        jpf.setEchoChar('\u2605');
        checkBox = new JCheckBox("显示密码");
        checkBox.setBounds(360, 200, 100, 25);
        checkBox.setFont(new Font("方正舒体", Font.PLAIN, 15));
        jrb1 = new JRadioButton("用户");
        jrb2 = new JRadioButton("管理员");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        jrb1.setSelected(true);
        jrb1.setBounds(160, 200, 80, 25);
        jrb2.setBounds(260, 200, 80, 25);
        jrb1.setFont(new Font("方正舒体", Font.PLAIN, 15));
        jrb2.setFont(new Font("方正舒体", Font.PLAIN, 15));
        jb1 = new JButton("登录");
        JButton jb2 = new JButton("注册");
        jb3 = new JButton("重置");
        jb1.setBounds(135, 260, 90, 45);
        jb2.setBounds(265, 260, 90, 45);
        jb3.setBounds(395, 260, 90, 45);
        jb1.setFont(new Font("方正舒体", Font.PLAIN, 20));
        jb2.setFont(new Font("方正舒体", Font.PLAIN, 20));
        jb3.setFont(new Font("方正舒体", Font.PLAIN, 20));
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jtf);
        jp.add(jpf);
        jp.add(checkBox);
        jp.add(jrb1);
        jp.add(jrb2);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);

        //回车事件
        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    jpf.grabFocus(); //聚焦下一个文本框
                }
            }
        });

        jpf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    jb1.doClick(); //点击登录按钮
                }
            }
        });

        checkBox.addItemListener(this :: appearAction);
        jb1.addActionListener(this :: loginAction);
        jb2.addActionListener(this :: registerAction);
        jb3.addActionListener(this :: resetAction);

        setContentPane(jp);
    }

    //密码显示事件
    private void appearAction(ItemEvent e) {
        if (checkBox.isSelected()) {
            jpf.setEchoChar((char) 0); //显示密码
        } else {
            jpf.setEchoChar('\u2605'); //显示掩码
        }
    }

    //登录
    private void loginAction(ActionEvent e) {
        //得到文本框输入
        String username = jtf.getText().trim();
        String password = String.valueOf(jpf.getPassword()).trim();
        //文本框判空
        if ("".equals(username)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("".equals(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //进行账户登录
        boolean isSuccess;
        if (jrb1.isSelected()) { //用户登录
            User user = new User(username, password); //用户对象
            isSuccess = userService.login(user);
            if (isSuccess) { //登录验证
                //登录成功开启主界面，传入用户账户名
                new MainFrame(user.getUsername()).setVisible(true);
                dispose(); //释放本窗口
            }
        } else { //管理员登录
            Admin admin = new Admin(username, password); //管理员对象
            isSuccess = adminService.login(admin);
            if (isSuccess) { //登录验证
                //登录成功开启主界面，传入管理员账户名
                new MainFrame("root").setVisible(true);
                dispose(); //释放本窗口
            }
        }
        if (!isSuccess) { //登录失败
            JOptionPane.showMessageDialog(null, "用户名或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
            jb3.doClick(); //重置文本框
        }
    }

    //注册
    private void registerAction(ActionEvent e) {
        if (jrb2.isSelected()) { //选择管理员则无法注册
            JOptionPane.showMessageDialog(null, "无法注册管理员！");
            return;
        }
        //选择用户注册
        setEnabled(false); //无法选中登录窗口
        JFrame This = this;
        JFrame frame = new RegisterFrame(); //进入注册窗口
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) { //注册结束时关闭注册窗口
                This.setEnabled(true);
                requestFocus(); //激活登录窗口
            }
        });
    }

    //重置
    private void resetAction(ActionEvent e) {
        //重置文本框
        jtf.setText("");
        jpf.setText("");
    }
}
