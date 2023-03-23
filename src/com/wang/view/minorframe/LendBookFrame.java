package com.wang.view.minorframe;

import com.wang.bean.Book;
import com.wang.bean.LendInfo;
import com.wang.service.BookService;
import com.wang.service.LendInfoService;
import com.wang.service.TypeService;
import com.wang.service.impl.BookServiceImpl;
import com.wang.service.impl.LendInfoServiceImpl;
import com.wang.service.impl.TypeServiceImpl;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.*;
import java.util.List;

/**
 * 借阅图书界面
 */
public class LendBookFrame extends MinorFrame {

    private JTable table;
    private DefaultTableModel tdm;
    private final BookService bookService = new BookServiceImpl();
    private final TypeService typeService = new TypeServiceImpl();
    private final LendInfoService lendInfoService = new LendInfoServiceImpl();
    private  JRadioButton jrb1, jrb2;
    private  JComboBox<String> jcb1, jcb2;
    private  JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
    private  JTextArea jta;

    public LendBookFrame(String username) {
        super("图书借阅", 800, 650);
        initFrame(username);
    }

    private void initFrame(String username){
        JPanel jp = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(120, 330, getWidth() - 50, 330);
                g.drawLine(50, 580, getWidth() - 50, 580);
                g.drawLine(50, 330, 50, 580);
                g.drawLine(getWidth() - 50, 330, getWidth() - 50, 580);
                g.drawLine(120, 30, getWidth() - 50, 30);
                g.drawLine(50, 100, getWidth() - 50, 100);
                g.drawLine(50, 30, 50, 100);
                g.drawLine(getWidth() - 50, 30, getWidth() - 50, 100);
            }
        };
        jp.setLayout(null);

        initTable();

        JLabel jl1 = new JLabel("搜索条件");
        JLabel jl2 = new JLabel("图书名称：");
        JLabel jl3 = new JLabel("图书作者：");
        JLabel jl4 = new JLabel("图书类别：");
        JLabel jl5 = new JLabel("表单操作");
        JLabel jl6 = new JLabel("图书名称：");
        JLabel jl7 = new JLabel("图书作者：");
        JLabel jl8 = new JLabel("作者性别：");
        JLabel jl9 = new JLabel("图书价格：");
        JLabel jl10 = new JLabel("图书库存：");
        JLabel jl11 = new JLabel("图书类别：");
        JLabel jl12 = new JLabel("图书描述：");
        jtf1 = new JTextField();
        jtf2 = new JTextField();
        jtf3 = new JTextField();
        jtf4 = new JTextField();
        jtf5 = new JTextField();
        jtf6 = new JTextField();
        jta = new JTextArea(20, 20);
        jta.setLineWrap(true);
        JScrollPane j = new JScrollPane();
        jrb1 = new JRadioButton("男");
        jrb2 = new JRadioButton("女");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        jrb1.setSelected(true);
        jcb1 = new JComboBox<>();
        jcb2 = new JComboBox<>();
        JButton jb1 = new JButton("查询");
        JButton jb2 = new JButton("借阅");
        JScrollPane jsp = new JScrollPane(table);

        jtf3.setEnabled(false);
        jtf4.setEnabled(false);
        jtf5.setEnabled(false);
        jtf6.setEnabled(false);
        jta.setEnabled(false);
        jcb2.setEnabled(false);
        jrb1.setEnabled(false);
        jrb2.setEnabled(false);

        jl1.setBounds(57, 20, 70, 20);
        jl2.setBounds(60, 55, 70, 20);
        jl3.setBounds(237, 55, 70, 20);
        jl4.setBounds(385, 55, 70, 20);
        jl5.setBounds(57, 320, 70, 20);
        jtf1.setBounds(125, 57, 100, 20);
        jtf2.setBounds(302, 57, 70, 20);
        jb1.setBounds(657, 42, 60, 20);
        jb2.setBounds(657, 72, 60, 20);
        jsp.setBounds(50, 125, 686, 180);
        jcb1.setBounds(455, 57, 180, 20);
        jcb2.setBounds(510, 397, 180, 20);
        jl6.setBounds(100, 355, 70, 20);
        jl7.setBounds(310, 355, 70, 20);
        jl8.setBounds(520, 355, 70, 20);
        jtf3.setBounds(170, 357, 130, 20);
        jtf4.setBounds(380, 357, 130, 20);
        jrb1.setBounds(590, 355, 40, 20);
        jrb2.setBounds(640, 355, 40, 20);
        jl9.setBounds(100, 395, 70, 20);
        jl10.setBounds(270, 395, 70, 20);
        jl11.setBounds(440, 395, 70, 20);
        jtf5.setBounds(170, 397, 90, 20);
        jtf6.setBounds(340, 397, 90, 20);
        jl12.setBounds(100, 438, 70, 20);
        j.setBounds(170, 440, 522, 120);

        fillBookTypeName();

        j.setViewportView(jta);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jl3);
        jp.add(jl4);
        jp.add(jl5);
        jp.add(jl6);
        jp.add(jl7);
        jp.add(jl8);
        jp.add(jl9);
        jp.add(jl10);
        jp.add(jl11);
        jp.add(jl12);
        jp.add(jtf1);
        jp.add(jtf2);
        jp.add(jtf3);
        jp.add(jtf4);
        jp.add(jtf5);
        jp.add(jtf6);
        jp.add(j);
        jp.add(jrb1);
        jp.add(jrb2);
        jp.add(jcb1);
        jp.add(jcb2);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jsp);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bookMousePressed();
            }
        });

        jb1.addActionListener(this::searchAction);
        jb2.addActionListener(actionEvent ->lendAction(username));

        setContentPane(jp);
    }

    //查询按钮
    private void searchAction(ActionEvent e) {
        //表格先清空选择
        table.removeRowSelectionInterval(0, table.getRowCount() - 1);
        //模糊查找
        String strOfName = jtf1.getText().trim();
        String strOfAuthor = jtf2.getText().trim();
        //
        String strOfType = (String) jcb1.getSelectedItem();
        if ("".equals(strOfName) && "".equals(strOfAuthor) && "请选择……".equals(strOfType)) {
            JOptionPane.showMessageDialog(null, "请输入查询信息！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<Book> list = new ArrayList<>();
        if (!"".equals(strOfName)) {
            list.addAll(bookService.getBookListByBookName(strOfName));
        }
        if (!"".equals(strOfAuthor)) {
            list.addAll(bookService.getBookListByAuthor(strOfAuthor));
        }
        if (!"".equals(strOfType)) {
            list.addAll(bookService.getBookListByTypeName(strOfType));
        }
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "未查询到指定结果！");
            return;
        }
        for (Book book : list) {
            for (int j = 0; j < tdm.getRowCount(); j++) {
                //将查询到的book根据书名得到表中行数选择得到的行数
                if (book.getBookId().equals(table.getValueAt(j, 0))) {
                    table.addRowSelectionInterval(j, j);
                    break;
                }
            }
        }
        //随机展出一个查询到的book对象的信息
        Book book = list.get((int) ((Math.random() * 100) % list.size()));
        jtf3.setText(book.getBookName());
        jtf4.setText(book.getAuthor());
        jtf5.setText(String.valueOf(book.getPrice()));
        jtf6.setText(String.valueOf(book.getBookNum()));
        jta.setText(book.getBookDesc());
        if ("男".equals(book.getGender())) {
            jrb1.setSelected(true);
        } else {
            jrb2.setSelected(true);
        }
        jcb2.setSelectedItem(book.getTypeName());
        JOptionPane.showMessageDialog(null, "共查询到" + table.getSelectedRowCount() + "条结果！");
    }

    //借阅按钮
    private void lendAction(String username) {
        if (table.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(null, "不支持同时借阅多本图书！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //获取选中行
        int row = table.getSelectedRow();
        if (row == - 1) { //没有选择
            JOptionPane.showMessageDialog(null, "请选择待借阅图书！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Integer.parseInt(String.valueOf(table.getValueAt(row, 5))) == 0) {
            JOptionPane.showMessageDialog(null, "该书无库存！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int isFlag = JOptionPane.showConfirmDialog(null, "是否借阅？");
        if (isFlag > 0) {
            return;
        }
        String bookName = (String) table.getValueAt(row, 1);
        //执行借阅
        bookService.lendBook(bookName); //库存减一
        //填入借阅信息
        lendInfoService.lendBook(new LendInfo(null, bookName, username, new Date(System.currentTimeMillis()), "未归还"));
        //更新数量
        Long newNum = (Long) table.getValueAt(row, 5) - 1;
        table.setValueAt(newNum, row, 5);
        jtf6.setText(String.valueOf(newNum));
        JOptionPane.showMessageDialog(null, "借阅成功！");
    }

    //监听鼠标点击事件
    private void bookMousePressed() {
        int row = table.getSelectedRow();
        jtf3.setText(String.valueOf(table.getValueAt(row, 1)));
        jtf4.setText(String.valueOf(table.getValueAt(row, 2)));
        jtf5.setText(String.valueOf(table.getValueAt(row, 4)));
        jtf6.setText(String.valueOf(table.getValueAt(row, 5)));
        String gender = String.valueOf(table.getValueAt(row, 3));
        if ("男".equals(gender)) {
            jrb1.setSelected(true);
        } else {
            jrb2.setSelected(true);
        }
        String typeName = String.valueOf(table.getValueAt(row, 6));
        jcb2.setSelectedItem(typeName);
        jta.setText(String.valueOf(table.getValueAt(row, 7)));
    }

    //初始化表格
    private void initTable() {
        //创建表格
        String[] columnNames = {"编号", "图书名称", "作者", "作者性别", "图书价格","图书库存", "图书类别", "图书描述"};
        Object[][] rowType = bookService.getAllBookToArray();
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
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(180);

        table.getTableHeader().setReorderingAllowed(false); //不可改变列的位置
    }

    //初始化类别下拉菜单
    private void fillBookTypeName() {
        jcb1.addItem("请选择……");
        jcb2.addItem("请选择……");
        List<com.wang.bean.Type> types = typeService.getAllTypeToList();
        for (com.wang.bean.Type type : types) {
            jcb1.addItem(type.getTypeName());
            jcb2.addItem(type.getTypeName());
        }
    }
}
