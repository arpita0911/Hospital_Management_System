package hospitalmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class all_patient_info extends JFrame {
    all_patient_info(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,880,540);
        panel.setBackground(new Color(82,124,136));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10,40,900,400);
        table.setBackground(new Color(82,124,136));
        table.setForeground(Color.white);
        table.setFont(new Font("Tahoma",Font.BOLD,12));
        table.setShowGrid(true);
        table.setGridColor(Color.WHITE); 
        panel.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 860, 400); 
        scrollPane.getViewport().setBackground(new Color(82,124,136)); 
        panel.add(scrollPane);

        try{
            connection c = new connection();
            String q = "select * from Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        JButton button = new JButton("BACK");
        button.setBounds(350,460,120,30);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(890,550);
        setLayout(null);
        setLocation(350,80);
        setVisible(true);

    }
    public static void main(String[] args) {
        new all_patient_info();
    }
}
