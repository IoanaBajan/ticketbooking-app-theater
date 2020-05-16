package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import repository.AdultRepository;
import repository.StudentRepository;
import service.LoginService;

import java.io.IOException;
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
    private AnchorPane anchPane;

    @FXML
    void register(MouseEvent event) {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String first_name = tf_first_name.getText();
        Integer age = parseInt(tf_age.getText());

        anchPane.getChildren().clear();
        GridPane grid = new GridPane();
        grid.setVgap(60);
        grid.setHgap(30);
        grid.setLayoutY(100);
        grid.setLayoutX(100);

        anchPane.getChildren().add(grid);
        Label l = new Label("I am a: ");
//        ObservableList<String> cursors = FXCollections.observableArrayList("Adult", "Student", "Child");
        grid.getChildren().add(l);
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Adult", "Student", "Child");
        grid.getChildren().add(choiceBox);
        GridPane.setConstraints(l, 0, 0);
        GridPane.setConstraints(choiceBox, 1, 0);

        if((choiceBox.getValue().toString()).equals("Adult")){
            Adult client = new Adult(username,password,first_name,age);

            LoginService L = new LoginService();
            L.register(client);

        }else if(choiceBox.getValue().toString().equals("Student")){
            Label stid = new Label("Student id");
            TextField add_name = new TextField();
            stid.setFont(new Font("Verdana", 20));
            int id = parseInt(add_name.getText());
            grid.getChildren().add(stid);
            GridPane.setConstraints(stid, 1, 0);
            Student client = new Student(username,password,first_name,age,id);

            LoginService L = new LoginService();
            L.register(client);
        }else{
            GridPane g = new GridPane();
            Label acc = new Label("Accompanied by an adult?");
            g.getChildren().add(acc);
            GridPane.setConstraints(l, 1, 0);
            String st[] = { "Yes", "No"};
            CheckBox c1 = new CheckBox(st[1]);
            g.getChildren().add(c1);
            GridPane.setConstraints(c1, 1, 1);

            CheckBox c2 = new CheckBox(st[1]);
            g.getChildren().add(c2);
            GridPane.setConstraints(c2, 1, 2);

            if(choiceBox.getValue().equals("Yes")) {
                Label name = new Label("Name");
                TextField add_name = new TextField();
                String user = add_name.getText();
                StudentRepository S = new StudentRepository();
                AdultRepository A = new AdultRepository();
                if(S.findUserByUsername(user).isPresent() || A.findUserByUsername(user).isPresent()){
                    Child client = new Child(username,password,first_name,age,1);


                    LoginService L = new LoginService();
                    L.register(client);
                }else {
                    Parent view2 = null;
                    try {
                        view2 = FXMLLoader.load(getClass().getResource("/login.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(view2));
                    stage.show();

                }
            }

        }

        Parent view2 = null;
        try {
            view2 = FXMLLoader.load(getClass().getResource("/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(view2));
        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

