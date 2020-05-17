package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.LoginService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    void login(MouseEvent event){
        String username = tf_username.getText();
        String password = tf_password.getText();

        LoginService L= new LoginService();
        if(L.loginDB(username,password)) {
            System.out.println("Login Reusit");
            Parent view2 = null;
            try {
                view2 = FXMLLoader.load(getClass().getResource("/main_activity.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(view2,1200,700));
            stage.setTitle("Home");
            stage.show();

        }
        else System.out.println("login nereusit");
    }
    @FXML
    void register(MouseEvent event){
        System.out.println("clicked");
        Parent view2 = null;
        try {
            view2 = FXMLLoader.load(getClass().getResource("/register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(view2));
        stage.setTitle("Register");
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
