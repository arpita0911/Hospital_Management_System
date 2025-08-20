package hospitalmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener{

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1,b2;

    login(){

        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(40,20,100,30);
        namelabel.setFont(new Font("Tahoma",Font.BOLD,16));
        namelabel.setForeground(Color.BLACK);
        add(namelabel);

        JLabel password = new JLabel("Password");
        password.setBounds(40,70,100,30);
        password.setFont(new Font("Tahoma",Font.BOLD,16));
        password.setForeground(Color.BLACK);
        add(password);

        textField = new JTextField();
        textField.setBounds(150,20,200,30);
        textField.setFont(new Font("Tahoma",Font.PLAIN,15));
        textField.setBackground(new Color(215,186,173));
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150,70, 200,30);
        jPasswordField.setFont(new Font("Tahoma",Font.PLAIN,15));
        jPasswordField.setBackground(new Color(215,186,173));
        add(jPasswordField);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/logo.jpg"));
        Image i1 = imageIcon.getImage().getScaledInstance(300,130,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(390,30,300,130);
        add(label);

        b1 = new JButton("Login");
        b1.setBounds(70,140,120,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        add(b1);


        b2 = new JButton("Cancel");
        b2.setBounds(210,140,120,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(82,124,136));
        setSize(750,250);
        setLocation(300,250);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            try{
                connection c = new connection();
                String user = textField.getText();
                char[] passChars = jPasswordField.getPassword();
                String Pass = new String(passChars);


                String q = "select * from login where ID = '"+user+"' and  PW = '"+Pass+"'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()){
                    new reception();
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null,"Invalid");
                }

            }catch (Exception E){
                E.printStackTrace();
            }

        }else {
            System.exit(10);
        }

    }

    public static void main(String[] args) {
        new login();
    }
}



