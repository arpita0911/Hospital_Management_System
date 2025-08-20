package hospitalmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class employee_info extends JFrame {
    employee_info() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 930, 430); 
        panel.setBackground(new Color(82, 124, 136));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
        Image image = imageIcon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(750, 35, 170, 170);
        panel.add(label);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 34, 710, 215);
        panel.add(scrollPane);

        table.setBackground(new Color(82, 124, 136));
        table.setForeground(Color.white);
        table.setFont(new Font("Tahoma", Font.PLAIN, 11));
        table.setShowGrid(true);
        table.setGridColor(Color.WHITE);

        try {
            connection c = new connection();
            String q = "select * from EMP_INFO";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] inputLabels = {"Name", "Age", "Phone", "Salary", "Gmail", "Aadhar"};
        JTextField[] fields = new JTextField[6];

        int startX = 37;
        int fieldWidth = 100;
        int gap = 120; 
        int yLabel = 325;
        int yField = 345;

        for (int i = 0; i < fields.length; i++) {
            int x = startX + (i * gap);

            JLabel l = new JLabel(inputLabels[i]);
            l.setBounds(x, yLabel, fieldWidth, 15);
            l.setForeground(Color.white);
            l.setFont(new Font("Tahoma", Font.PLAIN, 12));
            panel.add(l);

            fields[i] = new JTextField();
            fields[i].setBounds(x, yField, fieldWidth, 25);
            panel.add(fields[i]);
        }

        JButton addButton = new JButton("Add Info");
        addButton.setBounds(750, 345, 120, 25);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.white);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = fields[0].getText();
                    String age = fields[1].getText();
                    String phone = fields[2].getText();
                    String salary = fields[3].getText();
                    String gmail = fields[4].getText();
                    String aadhar = fields[5].getText();
        
                    if (name.isEmpty() || age.isEmpty() || phone.isEmpty() ||
                        salary.isEmpty() || gmail.isEmpty() || aadhar.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                        return;
                    }
        
                    connection c = new connection();
                    String q = "INSERT INTO EMP_INFO (Name, Age, Phone_Number, Salary, Mail, Aadhaar_Number) VALUES ('"
                    + name + "', '" + age + "', '" + phone + "', '" + salary + "', '" + gmail + "', '" + aadhar + "')";
            
        
                    c.statement.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Employee added successfully!");
        
                    ResultSet resultSet = c.statement.executeQuery("SELECT * FROM EMP_INFO");
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
        
                    for (JTextField field : fields) {
                        field.setText("");
                    }
        
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding employee.");
                }
            }
        });
        

        JButton button = new JButton("BACK");
        button.setBounds(320, 385, 120, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(940, 440); 
        setLocation(330, 80);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new employee_info();
    }
}
