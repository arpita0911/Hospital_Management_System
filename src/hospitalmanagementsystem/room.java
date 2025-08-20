package hospitalmanagementsystem;


import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class room extends JFrame {

    JTable table;

    room(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,860,360);
        panel.setBackground(new Color(82,124,136));
        panel.setLayout(null);

        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/room.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(210,210,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600,80,210,210);
        panel.add(label);

        table = new JTable();
        table.setBounds(10,40,500,230);
        table.setBackground(new Color(82,124,136));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Tahoma",Font.PLAIN,12));
        table.setShowGrid(true);
        table.setGridColor(Color.WHITE); 
        panel.add(table);

        try{

            connection c = new connection();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Room No");
        label1.setBounds(12,15,80,15);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        label1.setForeground(Color.WHITE);

        panel.add(label1);

        JLabel label2 = new JLabel("Availability");
        label2.setBounds(140,15,80,15);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        JLabel label3 = new JLabel("Price");
        label3.setBounds(290,15,80,15);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JLabel label4 = new JLabel("Bed Type");
        label4.setBounds(400,15,80,15);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JButton back = new JButton("BACK");
        back.setBounds(220, 290, 120, 30); 
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setUndecorated(true);
        setSize(870,370);
        setLayout(null);
        setLocation(375,80);
        setVisible(true);

    }
    public static void main(String[] args) {
        new room();
    }
}
