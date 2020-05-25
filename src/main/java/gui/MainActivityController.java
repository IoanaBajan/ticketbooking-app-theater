package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Concert;
import model.Event;
import model.TheaterPlay;
import repository.ConcertRepository;
import repository.TheaterPlaysRepository;
import service.SortService;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class MainActivityController implements Initializable {
    public static final String PASSPHRASE = "123LetMeIn";
    @FXML
    private TextFlow tf_flow;
    @FXML
    private TextFlow flow;
    @FXML
    private TextField passphrase;
    @FXML
    private AnchorPane anchPane;
    @FXML
    private TextFlow dialogPane;
    @FXML
    void buy(MouseEvent event){
        Parent view2 = null;
        try {
            view2 = FXMLLoader.load(getClass().getResource("/buy_ticket.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(view2));
        stage.setTitle("Buy Tickets");
        stage.show();

    }
    @FXML
    void showPlays(MouseEvent event) {
        TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.build(TheaterPlaysRepository.Type.DB);
        String value = " ";
        for (TheaterPlay t : theaterPlaysRepository.getPlays()) {
            String play = t.toString();
            value += play + "\n" + " \n";
        }
        Text text = new Text(value);
        text.setFill(Color.WHITE);
        text.setFont(new Font("Verdana", 15));

        tf_flow.getChildren().clear();
        tf_flow.setLineSpacing(15);
        tf_flow.getChildren().add(text);

        Button sort = new Button("Sort plays by date");
        tf_flow.getChildren().add(sort);
        sort.setOnMouseClicked(ev ->sortPlays());

        Button sort2 = new Button("Sort plays by name");
        tf_flow.getChildren().add(sort2);
        sort2.setOnMouseClicked(ev ->sortPlaysByName());

    }
    @FXML
    void showConcerts(MouseEvent event) {
        ConcertRepository concertRepository = ConcertRepository.build(ConcertRepository.Type.DB);
        String value = " ";
        for (Concert t : concertRepository.getConcerts()) {
            String concert = t.toString();
            value += concert + "\n" + " \n";
        }
        Text text = new Text(value);
        text.setFill(Color.WHITE);
        text.setFont(new Font("Verdana", 15));

        tf_flow.getChildren().clear();
        tf_flow.setLineSpacing(15);
        tf_flow.getChildren().add(text);


        Button sort = new Button("Sort concerts by date");
        tf_flow.getChildren().add(sort);
        sort.setOnMouseClicked(ev ->sortConcerts());


    }
    @FXML
    void button(MouseEvent event) {
        if((passphrase.getText()).equals(PASSPHRASE)){
        anchPane.getChildren().clear();


        Button addEvent = new Button("Add a new theater play");
        Pane buttonPane = new Pane();
        GridPane infoPane = new GridPane();
        infoPane.setVgap(50);
        infoPane.setHgap(30);
        anchPane.getChildren().add(buttonPane);
        buttonPane.getChildren().addAll(addEvent,infoPane);
        infoPane.setLayoutY(80);
        infoPane.setLayoutX(80);
        addEvent.setOnMouseClicked(ev -> {
            Label name = new Label("Event name");
            TextField add_name = new TextField();
            name.setFont(new Font("Verdana", 20));
            name.setStyle("{textFill: WHITE}");

            infoPane.getChildren().add(name);
            infoPane.getChildren().add(add_name);
            GridPane.setConstraints(name, 0, 0);
            GridPane.setConstraints(add_name, 1, 0);

            Label date1 = new Label("Start date");
            DatePicker add_date1 = new DatePicker();
            date1.setFont(new Font("Verdana", 20));
            date1.setStyle("{textFill: WHITE}");

            infoPane.getChildren().add(date1);
            infoPane.getChildren().add(add_date1);
            GridPane.setConstraints(date1, 0, 1);
            GridPane.setConstraints(add_date1, 1, 1);

            Label date2 = new Label("End date");
            DatePicker add_date2 = new DatePicker();
            date2.setFont(new Font("Verdana", 20));
            date2.setStyle("{textFill: WHITE}");

            infoPane.getChildren().add(date2);
            infoPane.getChildren().add(add_date2);
            GridPane.setConstraints(date2, 2, 1);
            GridPane.setConstraints(add_date2, 3, 1);

            Label maxNumberSeats = new Label("Maximum Number Of Seats");
            TextField add_maxNumberSeats = new TextField();
            maxNumberSeats.setFont(new Font("Verdana", 20));
            maxNumberSeats.setStyle("{textFill: WHITE}");

            infoPane.getChildren().add(maxNumberSeats);
            infoPane.getChildren().add(add_maxNumberSeats);
            GridPane.setConstraints(maxNumberSeats, 0, 2);
            GridPane.setConstraints(add_maxNumberSeats, 1, 2);


            Label director = new Label("Play's Director");
            TextField add_director = new TextField();
            director.setFont(new Font("Verdana", 20));
            director.setStyle("{textFill: WHITE}");

            infoPane.getChildren().add(director);
            infoPane.getChildren().add(add_director);
            GridPane.setConstraints(director, 0, 3);
            GridPane.setConstraints(add_director, 1, 3);


            Label actors = new Label("Play's Actors");
            TextField add_actors = new TextField();
            actors.setFont(new Font("Verdana", 20));
            actors.setStyle("{textFill: WHITE}");

            infoPane.getChildren().add(actors);
            infoPane.getChildren().add(add_actors);
            GridPane.setConstraints(actors, 0, 4);
            GridPane.setConstraints(add_actors, 1, 4);

            Label representations = new Label("Nr Representations");
            TextField add_representations = new TextField();
            representations.setFont(new Font("Verdana", 20));
            representations.setStyle("{textFill: WHITE}");

            infoPane.getChildren().add(representations);
            infoPane.getChildren().add(add_representations);
            GridPane.setConstraints(representations, 0, 5);
            GridPane.setConstraints(add_representations, 1, 5);


            Button add = new Button("add");
            infoPane.getChildren().add(add);
            GridPane.setConstraints(add, 0, 6);
            add.setOnMouseClicked(  x -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String attr_name = add_name.getText();
                String attr_date1 = add_date1.getValue().toString();
                String attr_date2 = add_date2.getValue().toString();
                int attr_seats = parseInt(add_maxNumberSeats.getText());
                String attr_dir = add_director.getText();
                String attr_act = add_actors.getText();
                int attr_repres = parseInt(add_representations.getText());
                TheaterPlay e = new TheaterPlay(attr_date1,attr_name,attr_seats,attr_dir,attr_act,attr_date2,attr_repres);
                TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.build(TheaterPlaysRepository.Type.DB);
                theaterPlaysRepository.addPlay(e);
                Text text = new Text("Theater Play Added Succesfully");
                text.setFont(new Font("Verdana", 15));
                text.setStyle("{textFill: WHITE}");
                dialogPane.getChildren().add(text);

            });
        });

        }else {
            flow.setVisible(true);
            Text text = new Text("SORRY,THAT'S NOT THE PASSPHRASE");
            text.setFill(Color.WHITE);
            text.setFont(new Font("Verdana", 20));
            flow.getChildren().add(text);
        }
    }


    void sortPlays(){
        SortService i = new SortService();
        String value = " ";
        for (Event t : i.sortPlays()) {
            String play = t.toString();
            value += play + "\n" + " \n";
        }
        Text text = new Text(value);
        text.setFill(Color.WHITE);
        text.setFont(new Font("Verdana", 15));

        tf_flow.getChildren().clear();
        tf_flow.setLineSpacing(15);
        tf_flow.getChildren().add(text);

    }
    void sortConcerts(){
        SortService i = new SortService();
        String value = " ";
        for (Event t : i.sortConcerts()) {
            String concert = t.toString();
            value += concert + "\n" + " \n";
        }

        Text text = new Text(value);
        text.setFill(Color.WHITE);
        text.setFont(new Font("Verdana", 15));

        tf_flow.getChildren().clear();
        tf_flow.setLineSpacing(15);
        tf_flow.getChildren().add(text);

    }
    void sortPlaysByName(){
        SortService i = new SortService();
        String value = " ";
        for (Event t : i.sortPlaysByName()) {
            String plays = t.toString();
            value += plays + "\n" + " \n";
        }

        Text text = new Text(value);
        text.setFill(Color.WHITE);
        text.setFont(new Font("Verdana", 15));

        tf_flow.getChildren().clear();
        tf_flow.setLineSpacing(15);
        tf_flow.getChildren().add(text);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}