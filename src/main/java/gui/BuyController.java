package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.*;
import repository.AdultRepository;
import repository.ChildRepository;
import repository.StudentRepository;
import repository.TheaterPlaysRepository;
import service.BuyService;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class BuyController implements Initializable {
    @FXML
    private Pane payment;
    @FXML
    private TextFlow flow;
    @FXML
    private Pane paneSeats;
    @FXML
    private TextField seats;
    @FXML
    private DatePicker eventDate;
    @FXML
    private TextField username;
    @FXML
    private TextField eventName;
    private int s;
    private String payMethod = " ";

    @FXML
    void price(MouseEvent event){
        String ev_name = eventName.getText();
        String user = username.getText();
        StudentRepository S = new StudentRepository();
        AdultRepository A = new AdultRepository();
        ChildRepository C = new ChildRepository();

        if(S.findUserInDB(user)!=null && TheaterPlaysRepository.findEventInDB(ev_name)!=null){
            Student client = S.findUserInDB(user);
            TheaterPlay even =TheaterPlaysRepository.findEventInDB(ev_name);
            calcPrice(client,even);

        }else if(A.findUserInDB(user)!=null && TheaterPlaysRepository.findEventInDB(ev_name)!=null){
            Adult client = A.findUserInDB(user);
            TheaterPlay even =TheaterPlaysRepository.findEventInDB(ev_name);
            calcPrice(client,even);

        } else if(C.findUserInDB(user)!=null && TheaterPlaysRepository.findEventInDB(ev_name)!=null){
            Child client = C.findUserInDB(user);
            TheaterPlay even =TheaterPlaysRepository.findEventInDB(ev_name);
            calcPrice(client,even);

        }
    }
    void cardDetail(String method){
        if(method.equals("cash")){
            payMethod = "Cash";
        }else if(method.equals("card")) {
            Label numeCard = new Label("nume card");
            numeCard.setFont(new Font("Verdana", 12));
            numeCard.setTextFill(Color.WHITE);

            GridPane grid = new GridPane();
            grid.setLayoutX(10);
            grid.setLayoutY(23);
            payment.getChildren().add(grid);
            grid.getChildren().add(numeCard);
            GridPane.setConstraints(numeCard, 0, 0);

            TextField add_name = new TextField();
            System.out.println(add_name.getText());
            grid.getChildren().add(add_name);
            GridPane.setConstraints(add_name, 0, 1);

            Label cvv = new Label("CVV");
            cvv.setFont(new Font("Verdana", 12));
            cvv.setTextFill(Color.WHITE);

            grid.getChildren().add(cvv);
            GridPane.setConstraints(cvv, 0, 2);

            TextField cvv_add = new TextField();
            System.out.println(cvv_add.getText());
            grid.getChildren().add(cvv_add);
            GridPane.setConstraints(cvv_add, 0, 3);

            String name =add_name.getText();
            String CVV =cvv_add.getText();
            payMethod = name + " " + CVV;
        }
    }
    void calcPrice(Object client, Object event){
        int seat = getSeats();
//                Integer.parseInt(seats.getText());
        BuyService buy = new BuyService();
        int price = buy.showPrice(client,event,seat);
        if(seat >68){
            Text text = new Text("seat not available");
            flow.getChildren().clear();
            flow.setLineSpacing(15);
            flow.getChildren().add(text);
            text.setFill(Color.WHITE);
            text.setFont(new Font("Verdana", 20));
        }else {
            if (price != -1) {
                flow.getChildren().clear();
                Text text = new Text(price + " lei");
                flow.setLineSpacing(15);
                flow.getChildren().add(text);
                text.setFill(Color.WHITE);
                text.setFont(new Font("Verdana", 20));
            } else {
                flow.getChildren().clear();
                Text text = new Text("the event is soldout");
                flow.setLineSpacing(15);
                flow.getChildren().add(text);
                text.setFill(Color.WHITE);
                text.setFont(new Font("Verdana", 20));
            }
        }
    }
    @FXML
    void placeorder(MouseEvent event){

    }
    void placeOrder(Object client, Object event){
        int seat =getSeats();
        BuyService buy = new BuyService();
        int price = buy.showPrice(client,event,seat);
        System.out.println(price);
        String ev_date = eventDate.getValue().toString();
//        Order o = new Order(getPayMethod(),ev_date,seat,price);

    }
    void setSeats(int text){
     s = text;
        System.out.println(s);
    }
     public int getSeats(){
        return s;
     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i =0; i<7; i++){
            for(int j = 0; j<8; j++){
                Integer text = (j + 1)+(i*10);
                Button b =new Button(text.toString());
                b.setLayoutY(14 + (i*33));
                b.setLayoutX(8+(j*40));
                paneSeats.getChildren().add(b);
                b.setOnMouseClicked(ev->setSeats(text));
            }
        }


        String st[] = {"card", "cash","none"};
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(st));
        payment.getChildren().add(choiceBox);
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
                        if (option[0].equals("cash")) {
                            cardDetail("cash");
                        } else if (option[0].equals("card")) {
                            cardDetail("card");
                        }else payment.getChildren().clear();
                    }
                });

    }

}
