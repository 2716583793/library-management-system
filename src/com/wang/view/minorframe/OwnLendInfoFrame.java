package com.wang.view.minorframe;

import com.wang.bean.LendInfo;
import com.wang.service.BookService;
import com.wang.service.LendInfoService;
import com.wang.service.impl.BookServiceImpl;
import com.wang.service.impl.LendInfoServiceImpl;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;

/**
 * 个人借阅信息界面
 */
public class OwnLendInfoFrame extends MinorFrame {

    private JTable table;
    private DefaultTableModel tdm;
    private final LendInfoService lendInfoService = new LendInfoServiceImpl();
    private final BookService bookService = new BookServiceImpl();

    public OwnLendInfoFrame(String username, boolean isFlag) {
        super("个人借阅信息", 500, 350);
        initFrame(username,isFlag);
    }

    private void initFrame(String username,boolean isFlag) {
        JPanel jp = new JPanel();
        jp.setLayout(null);

        initTable(username, isFlag);

        JScrollPane j = new JScrollPane(table);
        j.setBounds(30, 30, 425, 220);
        jp.add(j);

        if (isFlag) {
            JButton jb = new JButton("归还");
            jp.add(jb);
            jb.setBounds(360, 260, 60, 20);

            jb.addActionListener(actionEvent -> returnAction(username));
        }

        setContentPane(jp);
    }

    //归还按钮
    private void returnAction(String username) {
        if (table.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(null, "不支持同时归还多本图书！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //获取选中行
        int row = table.getSelectedRow();
        if (row == - 1) { //没有选择
            JOptionPane.showMessageDialog(null, "请选择待归还图书！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int isFlag = JOptionPane.showConfirmDialog(null, "是否归还？");
        if (isFlag > 0) {
            return;
        }
        int lendId = (int) table.getValueAt(row, 0);
        String bookName = (String) table.getValueAt(row, 1);
        Date lendDate = (Date) table.getValueAt(row, 2);
        bookService.returnBook(bookName); //库存加一
        lendInfoService.returnBook(new LendInfo(lendId, bookName, username, lendDate, new Date(System.currentTimeMillis()).toString())); //填入归还日期
        tdm.removeRow(row);
        JOptionPane.showMessageDialog(null, "归还成功！");
    }

    //初始化表格
    private void initTable(String username,boolean isFlag) {
        //创建表格
        String[] columnNames = {"借阅编号", "图书名称", "借阅时间", "归还时间"};
        Object[][] rowType;
        if(isFlag) {
             rowType = lendInfoService.getOwnLendInfo(username);
        } else{
             rowType = lendInfoService.getOwnReturnInfo(username);
        }
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

        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(205);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);

        table.getTableHeader().setReorderingAllowed(false); //不可改变列的位置
    }
}
