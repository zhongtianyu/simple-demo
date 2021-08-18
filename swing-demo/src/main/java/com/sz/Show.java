package com.sz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

/**
 * swing中为文本框添加键盘监听器的使用方法addKeyListener
 */
public class Show extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JLabel label;
    private JLabel label_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Show frame = new Show();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Show() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 523, 444);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        //正文开始以下斜体样式

        textField = new JTextField(10);//第一个输入框:-----数量:
        textField.setBounds(266, 147, 158, 44);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();//第二个输入框作为总计显示框-----总计
        textField_1.setBounds(266, 241, 158, 44);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        label = new JLabel("数量：");
        label.setFont(new Font("宋体", Font.PLAIN, 24));
        label.setBounds(122, 150, 109, 31);
        contentPane.add(label);

        label_1 = new JLabel("单价3元 总计：");
        label_1.setFont(new Font("宋体", Font.PLAIN, 23));
        label_1.setBounds(64, 244, 188, 31);
        contentPane.add(label_1);

        //为第一个输入框添加键盘监听器
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)//按下某个键时调用此方法
            {
                System.out.println(textField.getText());
            }

            @Override
            public void keyTyped(KeyEvent e)//释放某个键时调用此方法。
            {
                System.out.println(textField.getText());
            }

            @Override
            public void keyReleased(KeyEvent e)//键入某个键时调用此方法，其实是Keypressed和KeyTyped两个时间的集合 应个人要求编写
            {
                if (textField.getText() == null || "".equals(textField.getText().trim()))//此方法是为了解决输入后清空数量，总计不能清零的问题
                {
                    textField_1.setText("");
                } else {
                    int count = Integer.parseInt(textField.getText());//将获取的文本值即string转换为int值方便进行计算
                    String str = String.valueOf(count * 3);//计算完后将结果int型转换成string，数量乘以单价，如果计算过程要求繁琐或者多输入的字符有要求也可以自行拟写一个方法进行调用

                    textField_1.setText(str);//将计算的内容  显示到第二个显示框里textField_1.setText()
                }
            }
        });
    }
}
