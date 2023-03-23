package com.wang.view.minorframe.manager;

import com.wang.bean.Book;
import com.wang.service.BookService;
import com.wang.service.TypeService;
import com.wang.service.impl.BookServiceImpl;
import com.wang.service.impl.TypeServiceImpl;
import com.wang.utils.DigitUtils;
import com.wang.view.baseview.MinorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * 添加图书界面
 */
public class AddBookFrame extends MinorFrame {

    private JTextField jtf1, jtf2, jtf3, jtf4;
    private JTextArea jta;
    private JRadioButton jrb1;
    private JButton jb2;
    private final TypeService typeService = new TypeServiceImpl();
    private final BookService bookService = new BookServiceImpl();
    private JComboBox<String> jcb;

    public AddBookFrame() {
        super("图书添加", 700, 500);
        initFrame();
    }

    private void initFrame() {
        JPanel jp = new JPanel();
        jp.setLayout(null);

        JLabel jl1 = new JLabel("图书名称：");
        JLabel jl2 = new JLabel("图书作者：");
        JLabel jl3 = new JLabel("作者性别：");
        JLabel jl4 = new JLabel("图书价格：");
        JLabel jl5 = new JLabel("入库数量：");
        JLabel jl6 = new JLabel("图书类别：");
        JLabel jl7 = new JLabel("图书描述：");
        jtf1 = new JTextField();
        jtf2 = new JTextField();
        jtf3 = new JTextField();
        jtf4 = new JTextField();
        jta = new JTextArea(20, 20);
        jta.setLineWrap(true);
        jrb1 = new JRadioButton("男");
        JRadioButton jrb2 = new JRadioButton("女");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        jrb1.setSelected(true);
        jcb = new JComboBox<>();
        JButton jb1 = new JButton("添加");
        jb2 = new JButton("重置");
        JScrollPane jsp = new JScrollPane();
        jl1.setBounds(90, 40, 70, 20);
        jl2.setBounds(390, 40, 70, 20);
        jl3.setBounds(90, 105, 70, 20);
        jl4.setBounds(390, 105, 70, 20);
        jl5.setBounds(390, 170, 70, 20);
        jl6.setBounds(90, 170, 70, 20);
        jl7.setBounds(90, 235, 70, 20);
        jtf1.setBounds(160, 42, 120, 20);
        jtf2.setBounds(460, 42, 120, 20);
        jtf3.setBounds(460, 107, 120, 20);
        jtf4.setBounds(460, 172, 120, 20);
        jsp.setBounds(160, 235, 420, 100);
        jrb1.setBounds(160, 105, 40, 20);
        jrb2.setBounds(220, 105, 40, 20);
        jcb.setBounds(160, 170, 200, 20);
        jb1.setBounds(200, 380, 60, 30);
        jb2.setBounds(440, 380, 60, 30);

        fillBookTypeName();

        jsp.setViewportView(jta);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jl3);
        jp.add(jl4);
        jp.add(jl5);
        jp.add(jl6);
        jp.add(jl7);
        jp.add(jtf1);
        jp.add(jtf2);
        jp.add(jrb1);
        jp.add(jrb2);
        jp.add(jtf3);
        jp.add(jtf4);
        jp.add(jsp);
        jp.add(jcb);
        jp.add(jb1);
        jp.add(jb2);

        jb1.addActionListener(this::addBookAction);
        jb2.addActionListener(this::resetTestAction);

        setContentPane(jp);
    }

    //添加图书按钮
    private void addBookAction(ActionEvent e) {
        String bookName = jtf1.getText().trim();
        String author = jtf2.getText().trim();
        String price = jtf3.getText().trim();
        String num = jtf4.getText().trim();
        if ("".equals(bookName)) {
            JOptionPane.showMessageDialog(null, "图书名称不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("".equals(author)) {
            JOptionPane.showMessageDialog(null, "作者姓名不能为空！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("".equals(price) || DigitUtils.notBookPrice(price)) {
            JOptionPane.showMessageDialog(null, "图书价格必须为正数！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("".equals(num) || DigitUtils.notBookNum(num)) {
            JOptionPane.showMessageDialog(null, "入库数量必须为正整数！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String typeName = (String) jcb.getSelectedItem();
        if ("请选择……".equals(typeName)) {
            JOptionPane.showMessageDialog(null, "请选择图书类别！", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String gender;
        if (jrb1.isSelected()) {
            gender = "男";
        } else {
            gender = "女";
        }
        String bookDesc = jta.getText().trim();
        int isFlag = JOptionPane.showConfirmDialog(null, "是否添加？");
        if (isFlag > 0) {
            return;
        }
        bookService.addBook(new Book(null, bookName, author, gender, Double.parseDouble(price), Integer.parseInt(num), typeName, bookDesc));
        JOptionPane.showMessageDialog(null, "添加成功！");
        jb2.doClick();
    }

    //重置按钮
    private void resetTestAction(ActionEvent e) {
        jtf1.setText("");
        jtf2.setText("");
        jtf3.setText("");
        jtf4.setText("");
        jta.setText("");
        jcb.setSelectedIndex(0);
    }

    //初始化类别下拉菜单
    private void fillBookTypeName() {
        jcb.addItem("请选择……");
        List<com.wang.bean.Type> types = typeService.getAllTypeToList();
        for (com.wang.bean.Type type : types) {
            jcb.addItem(type.getTypeName());
        }
    }
}
