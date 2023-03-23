package com.wang.view.minorframe.manager;

import com.wang.bean.User;
import com.wang.service.UserService;
import com.wang.service.impl.UserServiceImpl;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 用户管理界面
 */
public class UserManagerFrame extends MinorFrame {

    private JTable table;
    private DefaultTableModel tdm;
    private JTextField jtf1, jtf2;
    private final UserService userService = new UserServiceImpl();

    public UserManagerFrame() {
        super("用户管理界面", 400, 300);
        initFrame();
    }

    private void initFrame() {
        JPanel jp = new JPanel();
        jp.setLayout(null);

        initTable();

        JLabel jl1 = new JLabel("用户名：");
        JLabel jl2 = new JLabel("密    码：");
        jtf1 = new JTextField();
        jtf2 = new JTextField();
        JButton jb = new JButton("删除");
        JScrollPane jsp = new JScrollPane(table);

        jl1.setBounds(50, 165, 50, 20);
        jl2.setBounds(50, 205, 50, 20);
        jtf1.setBounds(110, 167, 100, 20);
        jtf2.setBounds(110, 207, 100, 20);
        jsp.setBounds(20, 20, 350, 120);
        jb.setBounds(280, 207, 60, 20);

        jtf1.setEnabled(false);
        jtf2.setEnabled(false);

        jp.add(jl1);
        jp.add(jl2);
        jp.add(jtf1);
        jp.add(jtf2);
        jp.add(jsp);
        jp.add(jb);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                typeMousePressed();
            }
        });

        jb.addActionListener(this::deleteAction);

        setContentPane(jp);
    }

    //删除按钮
    private void deleteAction(ActionEvent e) {
        if (table.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(null, "不支持同时删除多行数据！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //获取选中行
        int row = table.getSelectedRow();
        if (row == -1) { //没有选择
            JOptionPane.showMessageDialog(null, "请选择待删除行！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int i = JOptionPane.showConfirmDialog(null, "是否删除？");
        if (i > 0) {
            return;
        }
        User user = new User((String) table.getValueAt(row, 0), (String) table.getValueAt(row, 1));
        userService.cancelUser(user);
        tdm.removeRow(row);
        resetValue(); //清空文本框
        JOptionPane.showMessageDialog(null, "删除成功！");
    }

    //监听鼠标点击事件
    private void typeMousePressed() {
        int row = table.getSelectedRow();
        jtf1.setText((String) table.getValueAt(row, 0));
        jtf2.setText((String) table.getValueAt(row, 1));
    }

    //重置按钮
    private void resetValue() {
        jtf1.setText("");
        jtf2.setText("");
    }

    //初始化表格
    private void initTable() {
        String[] columnNames = {"用户名", "密码"};
        Object[][] rowUser = userService.getUserInfos();
        tdm = new DefaultTableModel(rowUser, columnNames);
        table = new JTable(tdm) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //不可编辑，但可以选择
            }
        };
        //
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//单元格渲染器
        dtcr.setHorizontalAlignment(JLabel.CENTER);//居中显示
        table.setDefaultRenderer(Object.class, dtcr);//设置渲染器t

        table.getTableHeader().setReorderingAllowed(false); //不可改变列的位置
    }
}
