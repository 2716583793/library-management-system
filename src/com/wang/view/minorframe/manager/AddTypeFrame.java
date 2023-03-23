package com.wang.view.minorframe.manager;

import com.wang.service.TypeService;
import com.wang.service.impl.TypeServiceImpl;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 添加类别界面
 */
public class AddTypeFrame extends MinorFrame {

    private  JTextField jtf;
    private  JTextArea jta;
    private  JButton jb2;
    private final TypeService typeService = new TypeServiceImpl();

    public AddTypeFrame() {
        super("图书类别添加", 500, 370);
        initFrame();
    }

    private void initFrame(){
        JPanel jp = new JPanel();
        jp.setLayout(null);

        JLabel jl1 = new JLabel("图书类别名称：");
        JLabel jl2 = new JLabel("图书类别描述：");
        jtf = new JTextField();
        jta = new JTextArea(20, 20);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane();
        JButton jb1 = new JButton("添加");
        jb2 = new JButton("重置");
        jl1.setBounds(100, 40, 100, 20);
        jl2.setBounds(100, 80, 100, 20);
        jtf.setBounds(200, 40, 200, 20);
        jsp.setBounds(100, 110, 300, 120);
        jb1.setBounds(150, 270, 60, 30);
        jb2.setBounds(290, 270, 60, 30);
        jsp.setViewportView(jta);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jtf);
        jp.add(jsp);
        jp.add(jb1);
        jp.add(jb2);

        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    jta.grabFocus();
                }
            }
        });

        jb1.addActionListener(this::addTypeAction);
        jb2.addActionListener(this::resetTestAction);

        setContentPane(jp);
    }

    //添加类别按钮
    private void addTypeAction(ActionEvent e) {
        String typeName = jtf.getText().trim();
        String typeDesc = jta.getText().trim();
        if ("".equals(typeName)) {
            JOptionPane.showMessageDialog(null, "类别名称不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        boolean isSuccess = typeService.addType(new com.wang.bean.Type(null, typeName, typeDesc));
        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "添加成功！");
        } else {
            JOptionPane.showMessageDialog(null, "类别名称已存在！", "错误", JOptionPane.ERROR_MESSAGE);
        }
        jb2.doClick();
    }

    //重置按钮
    private void resetTestAction(ActionEvent e) {
        jtf.setText("");
        jta.setText("");
    }
}
