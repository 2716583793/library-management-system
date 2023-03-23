package com.wang.view.minorframe.manager;

import com.wang.bean.LendInfo;
import com.wang.service.LendInfoService;
import com.wang.service.impl.LendInfoServiceImpl;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询借阅信息界面
 */
public class AllLendInfoFrame extends MinorFrame {

    private JTable table;
    private DefaultTableModel tdm;
    private  JTextField jtf1, jtf2;
    private final LendInfoService lendInfoService = new LendInfoServiceImpl();

    public AllLendInfoFrame() {
        super("借阅信息", 600, 400);
        initFrame();
    }

    private void initFrame(){
        JPanel jp = new JPanel();
        jp.setLayout(null);

        initTable();

        JLabel jl1 = new JLabel("图书名称：");
        JLabel jl2 = new JLabel("借阅人：");
        jtf1 = new JTextField();
        jtf2 = new JTextField();
        JButton jb = new JButton("查询");
        JScrollPane j = new JScrollPane(table);
        jl1.setBounds(40, 30, 70, 20);
        jl2.setBounds(250, 30, 50, 20);
        jtf1.setBounds(110, 32, 130, 20);
        jtf2.setBounds(300, 32, 130, 20);
        jb.setBounds(480, 32, 60, 20);
        j.setBounds(40, 80, 510, 250);
        jp.add(j);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jtf1);
        jp.add(jtf2);
        jp.add(jb);

        jb.addActionListener(this::searchAction);

        setContentPane(jp);
    }

    //查询按钮
    private void searchAction(ActionEvent e) {
        //表格先清空选择
        table.removeRowSelectionInterval(0, table.getRowCount() - 1);
        String bookName = jtf1.getText().trim();
        String userName = jtf2.getText().trim();
        if ("".equals(bookName) && "".equals(userName)) {
            JOptionPane.showMessageDialog(null, "请输入查询信息！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<LendInfo> list = new ArrayList<>();
        if (!"".equals(bookName)) {
            list.addAll(lendInfoService.getLendInfoListByBookName(bookName));
        }
        if (!"".equals(userName)) {
            list.addAll(lendInfoService.getLendInfoListByUserName(userName));
        }
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "未查询到指定结果！");
            return;
        }
        for (LendInfo info : list) {
            for (int j = 0; j < tdm.getRowCount(); j++) {
                if (info.getLendId().equals(table.getValueAt(j, 0))) {
                    table.addRowSelectionInterval(j, j);
                    break;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "共查询到" + table.getSelectedRowCount() + "条结果！");
    }

    //初始化表格
    private void initTable() {
        //创建表格
        String[] columnNames = {"编号", "图书名称", "借阅人", "借阅时间", "归还时间"};
        Object[][] rowType = lendInfoService.getAllLendInfo();
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

        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);

        table.getTableHeader().setReorderingAllowed(false); //不可改变列的位置
    }
}
