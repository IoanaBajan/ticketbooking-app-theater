package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Login extends Application {


    @Override
    public void start(Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Ticket Booking App");
        assert root != null;
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
