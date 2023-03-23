package com.wang.view.keyframe;

import com.wang.bean.Admin;
import com.wang.bean.User;
import com.wang.service.AdminService;
import com.wang.service.UserService;
import com.wang.service.impl.AdminServiceImpl;
import com.wang.service.impl.UserServiceImpl;
import com.wang.utils.ImageData;
import com.wang.view.baseview.KeyFrame;
import com.wang.view.minorframe.LendBookFrame;
import com.wang.view.minorframe.OwnLendInfoFrame;
import com.wang.view.minorframe.manager.*;
import com.wang.view.minorframe.InfoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 主界面
 */
public class MainFrame extends KeyFrame {

    private final UserService userService = new UserServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    public MainFrame(final String username) {
        super("主界面", 900, 700);
        initFrame(username);
    }

    private void initFrame(String username){
        JPanel jp = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageData.main.paintIcon(this, g, 0, 0);
            }
        };
        jp.setLayout(null);

        JMenuBar jmb = new JMenuBar();
        JMenu jm0 = new JMenu("用户名：" + username);
        JMenu jm1 = new JMenu("用户操作");
        JMenu jm3 = new JMenu("关于我们");
        JMenu jm4 = new JMenu("更多");
        jmb.add(jm0);
        jmb.add(jm1);
        jmb.add(jm3);
        jmb.add(jm4);
        JMenu jm11 = new JMenu("借阅图书");
        JMenu jm12 = new JMenu("当前账户");
        JMenuItem jmi13 = new JMenuItem("安全退出");
        JMenuItem jmi31 = new JMenuItem("关于我们");
        JMenuItem jmi41 = new JMenuItem("更多");
        jm1.add(jm11);
        jm1.add(jm12);
        jm1.add(jmi13);
        jm3.add(jmi31);
        jm4.add(jmi41);
        JMenuItem jmi121 = new JMenuItem("借阅图书");
        JMenuItem jmi122 = new JMenuItem("借阅信息");
        JMenuItem jmi123 = new JMenuItem("归还信息");
        JMenuItem jmi124 = new JMenuItem("修改密码");
        JMenuItem jmi125 = new JMenuItem("退出登录");
        jm11.add(jmi121);
        jm11.add(jmi122);
        jm11.add(jmi123);
        jm12.add(jmi124);
        jm12.add(jmi125);
        if (!"root".equals(username)) {
            JMenuItem jmi126 = new JMenuItem("注销账户");
            jm12.add(jmi126);
            jmi126.addActionListener(e -> cancelAction(username));
        }
        jmi121.addActionListener(actionEvent -> lendBookAction(username));
        jmi122.addActionListener(actionEvent -> ownLendInfoAction(username,true));
        jmi123.addActionListener(actionEvent -> ownLendInfoAction(username,false));
        jmi124.addActionListener(actionEvent -> changeAction(username));
        jmi125.addActionListener(this::logoutAction);
        jmi13.addActionListener(this::exitAction);
        jmi31.addActionListener(this::infoAction);
        jmi41.addActionListener(this::moreAction);

        //管理员特权
        if ("root".equals(username)) {
            JMenu jm2 = new JMenu("管理员操作");
            jmb.add(jm2);
            JMenu jm21 = new JMenu("图书管理");
            JMenuItem jmi22 = new JMenuItem("用户管理");
            JMenuItem jmi23 = new JMenuItem("用户借阅信息");
            jm2.add(jm21);
            jm2.add(jmi22);
            jm2.add(jmi23);
            JMenuItem jmi211 = new JMenuItem("图书添加");
            JMenuItem jmi212 = new JMenuItem("图书管理");
            JMenuItem jmi213 = new JMenuItem("类别添加");
            JMenuItem jmi214 = new JMenuItem("类别管理");
            jm21.add(jmi211);
            jm21.add(jmi212);
            jm21.add(jmi213);
            jm21.add(jmi214);
            jmi22.addActionListener(this::userManagerAction);
            jmi23.addActionListener(this::allLendInfoAction);
            jmi211.addActionListener(this::addBookAction);
            jmi212.addActionListener(this::bookManagerAction);
            jmi213.addActionListener(this::addTypeAction);
            jmi214.addActionListener(this::typeManagerAction);
        }
        setJMenuBar(jmb);
        setContentPane(jp);
    }

    //查询个人借阅信息与归还图书
    private void ownLendInfoAction(String username,boolean isFlag) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new OwnLendInfoFrame(username,isFlag);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }

    //查询所有借阅信息
    private void allLendInfoAction(ActionEvent e) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new AllLendInfoFrame();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }

    //借阅图书
    private void lendBookAction(String username) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new LendBookFrame(username);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }

    //登出账户
    private void logoutAction(ActionEvent e) {
        int i = quitAction(e);
        if (i == 0) { //回到登录界面
            new LoginFrame().setVisible(true);
        }
    }

    //注销账户
    private void cancelAction(String username) {
        //输入框获取密码验证
        String password = JOptionPane.showInputDialog(null, "请输入密码验证！");
        //输入框判空
        if (password == null) {
            return;
        }
        if ("".equals(password.trim())) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //进行登录验证
        User user = new User(username, password);
        if (!userService.login(user)) {
            JOptionPane.showMessageDialog(null, "密码输入错误！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //验证成功，询问是否注销账户
        int isFlag = JOptionPane.showConfirmDialog(null, "是否确定注销账户？");
        if (isFlag == 0) {
            //进行账户注销
            userService.cancelUser(user);
            dispose();
        }
        new LoginFrame().setVisible(true);
    }

    //修改密码
    private void changeAction(String username) {
        //输入框获取新密码
        String newPassword = JOptionPane.showInputDialog(null, "请输入新密码！");
        //输入框判空
        if (newPassword == null) {
            return;
        }
        if ("".equals(newPassword.trim())) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //进行修改密码
        if ("root".equals(username)) {
            Admin newAdmin = new Admin("root", newPassword);
            adminService.changePassword(newAdmin);
        } else {
            User newUser = new User(username, newPassword);
            userService.changePassword(newUser);
        }
        JOptionPane.showMessageDialog(null, "修改成功！");
    }

    //退出程序
    private int quitAction(ActionEvent e) {
        int i = JOptionPane.showConfirmDialog(null, "是否退出？");
        if (i == 0) {
            dispose();
        }
        return i;
    }

    //退出程序
    private void exitAction(ActionEvent e) {
        System.exit(2);
    }

    //用户管理
    private void userManagerAction(ActionEvent e) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new UserManagerFrame();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }

    //关于我们
    private void infoAction(ActionEvent e) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new InfoFrame();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }

    //更多
    private void moreAction(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "敬请期待！");
    }

    //添加图书信息
    private void addBookAction(ActionEvent e) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new AddBookFrame();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }

    //图书信息管理
    private void bookManagerAction(ActionEvent e) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new BookManagerFrame();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }

    //添加图书类别
    private void addTypeAction(ActionEvent e) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new AddTypeFrame();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }

    //图书类别管理
    private void typeManagerAction(ActionEvent e) {
        setEnabled(false);
        JFrame This = this;
        JFrame frame = new TypeManagerFrame();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                This.setEnabled(true);
                requestFocus();
            }
        });
    }
}
