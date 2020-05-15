package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Client;
import model.User;
import service.LoginService;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class RegisterController implements Initializable {
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_first_name;
    @FXML
    private TextField tf_age;

    @FXML
    void register(MouseEvent event) {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String first_name = tf_first_name.getText();
        Integer age = parseInt(tf_age.getText());

        Client client = new Client(username,password,first_name,age);
        LoginService L = new LoginService();
        L.register(client);
        System.out.println("registered " + client.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

