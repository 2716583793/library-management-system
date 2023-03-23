package com.wang.view.minorframe.manager;

import com.wang.service.TypeService;
import com.wang.service.impl.TypeServiceImpl;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 类别管理界面
 */
public class TypeManagerFrame extends MinorFrame {

    private JTextField jtf1, jtf2, jtf3;
    private JTextArea jta;
    private JTable table;
    private DefaultTableModel tdm;
    private final TypeService typeService = new TypeServiceImpl();

    public TypeManagerFrame() {
        super("图书类别管理", 600, 500);
        initFrame();
    }

    private void initFrame() {
        JPanel jp = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(110, 250, getWidth() - 50, 250);
                g.drawLine(50, 420, getWidth() - 50, 420);
                g.drawLine(50, 250, 50, 420);
                g.drawLine(getWidth() - 50, 250, getWidth() - 50, 420);
            }
        };
        jp.setLayout(null);

        initTable();

        JLabel jl1 = new JLabel("图书类别名称：");
        JLabel jl2 = new JLabel("表单操作");
        JLabel jl3 = new JLabel("编号：");
        JLabel jl4 = new JLabel("图书类别名称：");
        JLabel jl5 = new JLabel("描述：");
        JButton jb1 = new JButton("查询");
        JButton jb2 = new JButton("修改");
        JButton jb3 = new JButton("删除");
        JScrollPane j = new JScrollPane(table);

        jtf1 = new JTextField();
        jtf2 = new JTextField();
        jtf3 = new JTextField();
        jta = new JTextArea(20, 20);
//        jtf2.setFont(new Font("宋体", Font.BOLD, 15));
//        jtf3.setFont(new Font("宋体", Font.BOLD, 15));
//        jta.setFont(new Font("宋体", Font.BOLD, 15));
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane();

        jtf2.setEnabled(false);
        jtf3.setEnabled(false);
        jta.setEnabled(false);

        jl1.setBounds(100, 30, 90, 20);
        jl2.setBounds(60, 240, 50, 20);
        jl3.setBounds(80, 270, 40, 20);
        jl4.setBounds(230, 270, 90, 20);
        jl5.setBounds(80, 315, 40, 20);
        jtf1.setBounds(190, 32, 170, 20);
        jtf2.setBounds(120, 272, 90, 20);
        jtf3.setBounds(320, 272, 170, 20);
        jsp.setBounds(120, 315, 370, 50);
        jb1.setBounds(420, 30, 60, 20);
        jb2.setBounds(185, 380, 60, 20);
        jb3.setBounds(335, 380, 60, 20);
        j.setBounds(50, 70, 490, 150);

        jsp.setViewportView(jta);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jl3);
        jp.add(jl4);
        jp.add(jl5);
        jp.add(jtf1);
        jp.add(jtf2);
        jp.add(jtf3);
        jp.add(jsp);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(j);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                typeMousePressed();
            }
        });

        jb1.addActionListener(this::searchAction);
        jb2.addActionListener(this::updateAction);
        jb3.addActionListener(this::deleteAction);

        setContentPane(jp);
    }

    //查询按钮
    private void searchAction(ActionEvent e) {
        //表格先清空选择
        table.removeRowSelectionInterval(0, table.getRowCount() - 1);
        //得到搜索文本
        String strOfName = jtf1.getText().trim();
        if ("".equals(strOfName)) {
            JOptionPane.showMessageDialog(null, "请输入查询信息！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<com.wang.bean.Type> types = typeService.getTypeListByTypeName(strOfName);
        if (types.size() == 0) {
            JOptionPane.showMessageDialog(null, "未查询到指定结果！");
            return;
        }
        for (com.wang.bean.Type type : types) {
            for (int j = 0; j < tdm.getRowCount(); j++) {
                if (type.getTypeId().equals(table.getValueAt(j, 0))) {
                    table.addRowSelectionInterval(j, j);
                    break;
                }
            }
        }
        jtf3.setEnabled(true);
        jta.setEnabled(true);
        com.wang.bean.Type type = types.get((int) ((Math.random() * 100) % types.size()));
        jtf2.setText(String.valueOf(type.getTypeId()));
        jtf3.setText(type.getTypeName());
        jta.setText(type.getTypeDesc());
        int count = table.getSelectedRowCount();
        JOptionPane.showMessageDialog(null, "共查询到" + count + "条结果！");
    }

    //修改按钮
    private void updateAction(ActionEvent e) {
        if (table.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(null, "不支持同时修改多行数据！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //获取选中行
        int row = table.getSelectedRow();
        if (row == -1) { //没有选择
            JOptionPane.showMessageDialog(null, "请选择待修改行！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //获取该行的类型id
        int typeId = (int) table.getValueAt(row, 0);
        //获取文本中类型的新信息
        String newTypeName = jtf3.getText().trim();
        String newTypeDesc = jta.getText().trim();
        //类型名不能为空
        if ("".equals(newTypeName)) {
            JOptionPane.showMessageDialog(null, "类别名不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int isFlag = JOptionPane.showConfirmDialog(null, "是否修改？");
        if (isFlag > 0) {
            return;
        }
        //通过id获取原信息
        com.wang.bean.Type oldType = typeService.getTypeById(typeId);
        //将新信息进行包装
        com.wang.bean.Type newType = new com.wang.bean.Type(typeId, newTypeName, newTypeDesc);

        if (typeService.isExistsType(newTypeName) && !oldType.getTypeName().equals(newTypeName)) { //名称存在且已更改
            JOptionPane.showMessageDialog(null, "类别名已存在！", "错误", JOptionPane.ERROR_MESSAGE);
            //填回原信息
            jtf3.setText(oldType.getTypeName());
            jta.setText(oldType.getTypeDesc());
            return;
        }
        //进行替换
        typeService.changeTypeById(newType);
        //替换成功将新信息显示在表中
        table.setValueAt(newTypeName, row, 1);
        table.setValueAt(newTypeDesc, row, 2);
        JOptionPane.showMessageDialog(null, "修改成功！");
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
        //获取该行的类型id
        int typeId = (int) table.getValueAt(row, 0);
        //通过id进行删除
        typeService.dropType(typeId);
        //删除表中指定行
        tdm.removeRow(row);
        resetValue(); //清空文本框
        jtf3.setEnabled(false);
        jta.setEnabled(false);
        JOptionPane.showMessageDialog(null, "删除成功！");
    }

    //监听鼠标点击事件
    private void typeMousePressed() {
        int row = table.getSelectedRow();
        jtf2.setText(String.valueOf(table.getValueAt(row, 0)));
        jtf3.setText(String.valueOf(table.getValueAt(row, 1)));
        jta.setText(String.valueOf(table.getValueAt(row, 2)));
        jtf3.setEnabled(true);
        jta.setEnabled(true);
    }

    //重置按钮
    private void resetValue() {
        jtf1.setText("");
        jtf2.setText("");
        jtf3.setText("");
        jta.setText("");
    }

    //初始化表格
    private void initTable() {
        //创建表格
        String[] columnNames = {"编号", "图书类别名称", "图书类别描述"};
        Object[][] rowType = typeService.getAllTypeToArray();
        tdm = new DefaultTableModel(rowType, columnNames);
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

        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(260);

        table.getTableHeader().setReorderingAllowed(false); //不可改变列的位置
    }
}
