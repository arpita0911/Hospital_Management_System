package hospitalmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ambulance extends JFrame {
    ambulance() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 290);
        panel.setBackground(new Color(82, 124, 136));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10, 40, 900, 150);
        table.setBackground(new Color(82, 124, 136));
        table.setForeground(Color.white);
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        table.setShowGrid(true);
        table.setGridColor(Color.WHITE);
        panel.add(table);

        try {
            connection c = new connection();
            String q = "SELECT * FROM Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Name");
        label1.setBounds(51, 11, 100, 14);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2 = new JLabel("Gender");
        label2.setBounds(254, 11, 100, 14);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        JLabel label3 = new JLabel("Car name");
        label3.setBounds(386, 11, 100, 14);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JLabel label4 = new JLabel("Available");
        label4.setBounds(560, 11, 100, 14);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JLabel label5 = new JLabel("Location");
        label5.setBounds(750, 11, 100, 14);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JButton buttonUpdate = new JButton("UPDATE");
        buttonUpdate.setBounds(200, 210, 120, 30);
        buttonUpdate.setBackground(Color.black);
        buttonUpdate.setForeground(Color.white);
        panel.add(buttonUpdate);

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String Name = table.getValueAt(selectedRow, 0).toString();  
                    String currentAvailability = table.getValueAt(selectedRow, 3).toString(); 

                    String[] availabilityOptions = {"Available", "Unavailable", "On Call"};
                    JComboBox<String> availabilityComboBox = new JComboBox<>(availabilityOptions);
                    availabilityComboBox.setSelectedItem(currentAvailability);  

                    Object[] message = {
                        "Select New Availability:", availabilityComboBox
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Update Availability", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        String newAvailability = (String) availabilityComboBox.getSelectedItem();

                        try {
                            connection c = new connection();
                            String q = "UPDATE Ambulance SET Available = ? WHERE Name = ?";
                            PreparedStatement pst = c.connection.prepareStatement(q);
                            pst.setString(1, newAvailability);
                            pst.setString(2, Name);
                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Availability updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            String refreshQuery = "SELECT * FROM Ambulance";
                            ResultSet rs = c.statement.executeQuery(refreshQuery);
                            table.setModel(DbUtils.resultSetToTableModel(rs));
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error updating availability.", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton buttonBack = new JButton("BACK");
        buttonBack.setBounds(350, 210, 120, 30);
        buttonBack.setBackground(Color.black);
        buttonBack.setForeground(Color.white);
        panel.add(buttonBack);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(900, 300);
        setLayout(null);
        setLocation(350, 80);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ambulance();
    }
}
