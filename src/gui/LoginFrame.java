package gui;

import model.Client;
import service.InformationService;
import service.LoginService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();

    private JLabel e1 = new JLabel("Username:");
    private JTextField textField1 = new JTextField(10);
    private JLabel r1 = new JLabel("Choose Username:");
    private JTextField textField3 = new JTextField(10);

    private JLabel e2 = new JLabel("Password:");
    private JPasswordField textField2 = new JPasswordField(10);
    private JLabel r2 = new JLabel("Choose Password:");
    private JPasswordField textField4 = new JPasswordField(10);

    private JButton logInButton = new JButton("Log In");
    private JButton register = new JButton("Register");
    private JButton makeaccount = new JButton("Make account");
    private JButton show = new JButton("Show clients");

    public LoginFrame() {
        setLayout(new GridLayout(3, 1));

        add(p1);
        add(p2);
        add(p3);

        p1.add(e1);
        p1.add(textField1);

        p2.add(e2);
        p2.add(textField2);

        p3.add(logInButton);
        p3.add(register);

        logInButton.addActionListener(ev -> login());
        register.addActionListener(ev -> registerFrame());
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void login() {
        String username = textField1.getText();
        String password = textField2.getText();

        Client client = new Client(username, password);

        LoginService service = LoginService.getInstance();

        if (service.login(client)) {
            JOptionPane.showMessageDialog(null, "Login reusit!");
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Login nereusit!");
            setVisible(false);
            remove(p1);
            remove(p2);
            remove(p3);
        }
    }

    private void registerFrame() {
        setLayout(new GridLayout(3, 1));
//
//        remove(p1);
//        remove(p2);
        add(p3);
        add(p1);
        add(p2);

        p1.add(r1);
        p1.add(textField3);
//        p1.remove(e1);
//        p1.remove(textField1);

//        p2.remove(e2);
//        p2.remove(textField2);
        p2.add(r2);
        p2.add(textField4);

        p3.add(register);
        p3.add(makeaccount);
        p3.add(show);
//        p3.remove(logInButton);


        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void register() {
        String username = textField3.getText();
        String password = textField4.getText();

        Client client = new Client(username, password);
        LoginService service = LoginService.getInstance();
        InformationService service1 = InformationService.getInstance();

        makeaccount.addActionListener(ev -> service.register(client));
//        show.addActionListener(ev ->service1.showClients());
//        setVisible(false);

    }
}