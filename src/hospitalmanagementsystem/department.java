package hospitalmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class department extends JFrame {
    department() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 390);
        panel.setLayout(null);
        panel.setBackground(new Color(82, 124, 136));
        add(panel);

        JTable table = new JTable();
        table.setBackground(new Color(82, 124, 136));
        table.setForeground(Color.white);
        table.setShowGrid(true);
        table.setGridColor(Color.WHITE);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 40, 680, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        try {
            connection c = new connection();
            String q = "select * from department";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }


        JLabel deptLabel = new JLabel("Enter Department:");
        deptLabel.setBounds(50, 255, 120, 20);
        deptLabel.setForeground(Color.white);
        panel.add(deptLabel);

        JTextField deptField = new JTextField();
        deptField.setBounds(50, 275, 200, 25);
        panel.add(deptField);

        JLabel phoneLabel = new JLabel("Enter Phone Number:");
        phoneLabel.setBounds(270, 255, 150, 20);
        phoneLabel.setForeground(Color.white);
        panel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(270, 275, 200, 25);
        panel.add(phoneField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(490, 275, 100, 25);
        addBtn.setBackground(Color.black);
        addBtn.setForeground(Color.white);
        panel.add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dept = deptField.getText().trim();
                String phone = phoneField.getText().trim();

                if (dept.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }

                try {
                    connection c = new connection();
                    String query = "INSERT INTO department VALUES('" + dept + "','" + phone + "')";
                    c.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Department Added");

                    deptField.setText("");
                    phoneField.setText("");

                    ResultSet rs = c.statement.executeQuery("SELECT * FROM department");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton b1 = new JButton("BACK");
        b1.setBounds(250, 325, 120, 30);
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(700, 400);
        setLayout(null);
        setLocation(420, 80);
        setVisible(true);
    }

    public static void main(String[] args) {
        new department();
    }
}
