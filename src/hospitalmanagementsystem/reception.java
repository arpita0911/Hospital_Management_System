package hospitalmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class reception extends JFrame {

    reception() {
        setSize(1950, 1090);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(5, 5, 300, 685);  
        leftPanel.setBackground(new Color(82, 124, 136));
        add(leftPanel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/logo.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(300,130,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label1 = new JLabel(imageIcon1);
        label1.setBounds(5,5,300,130);
        leftPanel.add(label1);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(310, 5, 1620, 830);  
        rightPanel.setBackground(new Color(46, 68, 80));
        add(rightPanel);

        JButton btn1 = createButton("Add New Patient", 20, 150, leftPanel, e -> new new_patient());
        JButton btn2 = createButton("Room", 20, 200, leftPanel, e -> new room());
        JButton btn3 = createButton("Department", 20, 250, leftPanel, e -> new department());
        JButton btn4 = createButton("All Employee Info", 20, 300, leftPanel, e -> new employee_info());
        JButton btn5 = createButton("Patient Info", 20, 350, leftPanel, e -> new all_patient_info());
        JButton btn6 = createButton("Patient Discharge", 20, 400, leftPanel, e -> new patient_discharge());
        JButton btn7 = createButton("Update Patient Details", 20, 450, leftPanel, e -> new update_patient_details());
        JButton btn8 = createButton("Hospital Ambulance", 20, 500, leftPanel, e -> new ambulance());
        JButton btn9 = createButton("Search Room", 20, 550, leftPanel, e -> new search_room());
        JButton btn10 = createButton("Logout", 20, 600, leftPanel, e -> {
            setVisible(false);
            new login();
        });

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, JPanel panel, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 250, 35); 
        button.setBackground(new Color(215, 186, 173));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(action);
        panel.add(button);
        return button;
    }

    public static void main(String[] args) {
        new reception();
    }
}