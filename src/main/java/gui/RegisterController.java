package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.*;
import repository.DBAdultRepository;
import repository.DBStudentRepository;
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
    private AnchorPane anchPane;

    @FXML
    void register(MouseEvent event) {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String first_name = tf_first_name.getText();
        Integer age = parseInt(tf_age.getText());

        anchPane.getChildren().clear();
        Pane p = new Pane();
        Pane p2 = new Pane();
        anchPane.getChildren().add(p2);
        anchPane.getChildren().add(p);
        p2.setLayoutX(300);
        p2.setLayoutY(10);
        p.setLayoutX(300);
        p.setLayoutY(90);
        p2.setPrefSize(285,80);
        p.setPrefSize(286,300);
        p.setStyle("-fx-background-color:  #72B69C");
        p2.setStyle("-fx-background-color:  #72B69C");
//        creating a grid where i will place a choice box for the type of client
        GridPane grid = new GridPane();
        grid.setVgap(60);
        grid.setHgap(30);
        grid.setLayoutY(30);
        grid.setLayoutX(30);

        p2.getChildren().add(grid);

        Label l = new Label("I am a: ");
        l.setFont(new Font("Verdana", 20));
        grid.getChildren().add(l);
        GridPane.setConstraints(l, 0, 0);

        String st[] = {"Adult", "Student", "Child", "None"};
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(st));
        grid.getChildren().add(choiceBox);
        GridPane.setConstraints(choiceBox, 1, 0);

//        add change listener for the choice box
        final String[] option = {" "};
        choiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {
                        System.out.println(ov);
                        System.out.println(t);
                        System.out.println(t1);
                        option[0] = t1;
                        System.out.println("OPTION" + option[0]);
                        if (option[0].equals("Adult")) {
//                            createAdult(username, password, first_name, age);
                        } else if (option[0].equals("Student")) {
                            createStudent(username, password, first_name, age);
                        } else if (option[0].equals("Child")) {
                            createChild(username, password, first_name, age);

                        }else anchPane.getChildren().clear();
                    }
                });

    }

//    public void createAdult(String username, String password, String first_name, Integer age) {
//        Adult client = new Adult(username, password, first_name, age);
//
//        LoginService L = new LoginService();
//        L.register(client);
//        System.out.println(client.toString());
//    }

    void createStudent(String username, String password, String first_name, Integer age) {
        System.out.println("select options for student");
        Label stid = new Label("Student id");
        stid.setFont(new Font("Verdana", 20));
        GridPane grid = new GridPane();

        grid.setLayoutY(30);
        grid.setLayoutX(10);

        grid.getChildren().add(stid);
        GridPane.setConstraints(stid, 0, 0);
        Pane p = (Pane) anchPane.getChildren().get(1);
        p.getChildren().clear();
        p.getChildren().add(grid);
        grid.setLayoutY(150);
        grid.setLayoutX(150);

        TextField add_name = new TextField();
        System.out.println(add_name.getText());
        grid.getChildren().add(add_name);
        GridPane.setConstraints(add_name, 0, 1);


        Button add = new Button("register");
        grid.getChildren().add(add);
        GridPane.setConstraints(add, 0, 2);

        add.setOnMouseClicked(ev -> {
            int id = Integer.parseInt(add_name.getText());
            Student client = new Student(username, password, first_name, id);
            LoginService L = new LoginService();
            L.register(client);
            System.out.println(client.toString());
        });
    }

    void createChild(String username, String password, String first_name, Integer age) {
        System.out.println("select options for child");

        GridPane g = new GridPane();
        g.setLayoutX(30);
        g.setLayoutY(10);
        g.setHgap(25);
        g.setVgap(25);

        Pane p = (Pane) anchPane.getChildren().get(1);
        p.getChildren().clear();
        p.getChildren().add(g);
        Label acc = new Label("Accompanied by an adult?");
        acc.setFont(new Font("Verdana", 20));
        g.getChildren().add(acc);
        GridPane.setConstraints(acc, 0, 0);

        String ch[] = {"Yes", "No"};
        CheckBox c1 = new CheckBox(ch[0]);
        g.getChildren().add(c1);
        GridPane.setConstraints(c1, 0, 1);

        CheckBox c2 = new CheckBox(ch[1]);
        g.getChildren().add(c2);
        GridPane.setConstraints(c2, 0, 2);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if (c1.isSelected()) {
                    g.getChildren().clear();
                    System.out.println("selected");
                    Label name = new Label("Name");
                    TextField add_name = new TextField();
                    g.getChildren().addAll(name, add_name);
                    GridPane.setConstraints(name, 1, 2);
                    GridPane.setConstraints(add_name, 1, 3);
                    Button add = new Button("register");
                    g.getChildren().add(add);
                    GridPane.setConstraints(add, 1, 4);

                    add.setOnMouseClicked(ev -> {
                        System.out.println("verify");
                        String user = add_name.getText();
                        DBStudentRepository S = new DBStudentRepository();
                        DBAdultRepository A = new DBAdultRepository();

//                        if ((S.findUserByName(user).isPresent()) || (A.findUserInDB(user)!=null)) {
//                            Child client = new Child(username, password, first_name, age, 1);
//                            LoginService L = new LoginService();
//                            L.register(client);
//                            System.out.println(client.toString());
//                        }
                    });
                }else if (c2.isSelected()) {
                    g.getChildren().clear();
                    Text add = new Text("Sorry, you can't visit us without an adult");
                    add.setFont(new Font("Verdana", 20));
                    g.getChildren().add(add);
                    GridPane.setConstraints(add, 1, 3);

                }
            }
        };
        c1.setOnAction(event);
        c2.setOnAction(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


