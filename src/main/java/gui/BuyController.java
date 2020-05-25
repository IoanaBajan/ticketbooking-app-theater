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
import repository.*;
import service.BuyService;
import service.PlaceOrderService;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;


public class BuyController implements Initializable {
    @FXML
    private Pane payment;
    @FXML
    private TextFlow message;
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
    private int price;
    @FXML
    void price(MouseEvent event){
        String ev_name = eventName.getText();
        String user = username.getText();
        StudentRepository S = StudentRepository.build(StudentRepository.Type.DB);
        AdultRepository A = AdultRepository.build(AdultRepository.Type.DB);
        ChildRepository C = ChildRepository.build(ChildRepository.Type.DB);
        TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.build(TheaterPlaysRepository.Type.DB);
        ConcertRepository concertRepository = ConcertRepository.build(ConcertRepository.Type.DB);
        User client = new Client();
        if(S.findUserByName(user).isPresent()) {
            Optional<Student> s = S.findUserByName(user);
            client = new User(s.get().getUsername(),s.get().getPassword());

        }else if(A.findUserByName(user).isPresent()){
            Optional<Adult> a = A.findUserByName(user);
            client = new User(a.get().getUsername(),a.get().getPassword());

        } else if(C.findUserByName(user).isPresent()){
            Optional<Child> c = C.findUserByName(user);
            client = new User(c.get().getUsername(),c.get().getPassword());
        }

        if(theaterPlaysRepository.findPlay(ev_name)!=null){
            TheaterPlay even = theaterPlaysRepository.findPlay(ev_name);
            calcPrice(client,even);

        }else if(concertRepository.findConcert(ev_name).isPresent()){
            Optional<Concert> even = concertRepository.findConcert(ev_name);
            calcPrice(client,even.get());
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
            grid.getChildren().add(add_name);
            GridPane.setConstraints(add_name, 0, 1);

            Label cvv = new Label("CVV");
            cvv.setFont(new Font("Verdana", 12));
            cvv.setTextFill(Color.WHITE);

            grid.getChildren().add(cvv);
            GridPane.setConstraints(cvv, 0, 2);

            TextField cvv_add = new TextField();
            grid.getChildren().add(cvv_add);
            GridPane.setConstraints(cvv_add, 0, 3);
            Button b = new Button("add");
            grid.getChildren().add(b);
            GridPane.setConstraints(b, 1, 3);
            b.setOnMouseClicked(ev-> {
                String name = add_name.getText();
                String CVV = cvv_add.getText();
                payMethod = name + " " + CVV;
            });
        }
    }
    void calcPrice(User client, Event event){
        BuyService buy = new BuyService();
        PlaceOrderService p = new PlaceOrderService();
        int seat = getSeats();
        LocalDate ev_date = eventDate.getValue();
        TextFlow t = new TextFlow();
        System.out.println(ev_date.toString() + event.getName());
        if( !DBOrdersRepository.getInstance().isSeatAvailable(seat,ev_date.toString(),event.getName())) {
            message.getChildren().add(new Text("seat is not available"));
            paneSeats.getChildren().add(t);
        }else {
            message.getChildren().clear();
            price = buy.showPrice(client, event, seat);
            System.out.println(price);
            TheaterPlaysRepository theaterPlaysRepository = TheaterPlaysRepository.build(TheaterPlaysRepository.Type.DB);
            ConcertRepository concertRepository = ConcertRepository.build(ConcertRepository.Type.DB);

            if (seat > 68) {
                Text text = new Text("seat not available");
                flow.getChildren().clear();
                flow.setLineSpacing(15);
                flow.getChildren().add(text);
                text.setFill(Color.WHITE);
                text.setFont(new Font("Verdana", 20));
            } else {
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
            Order o = new Order(getPayMethod(), ev_date, seat, price, event.getName(), client.getUsername());
            if (theaterPlaysRepository.findPlay(event.getName()) != null) {
                TheaterPlay play = theaterPlaysRepository.findPlay(event.getName());
                if (p.isOrderValid(o, play)) {

                    DBOrdersRepository.getInstance().addOrder(o);
                } else {
                    System.out.println("show not available");
                    flow.getChildren().clear();
                    Text text = new Text(" show not available on that day");
                    flow.setLineSpacing(8);
                    flow.getChildren().add(text);
                    text.setFill(Color.WHITE);
                    text.setFont(new Font("Verdana", 10));
                }
            } else if (concertRepository.findConcert(event.getName()).isPresent()) {
                Concert concert = concertRepository.findConcert(event.getName()).get();
                if (p.isOrderValid(o, concert)) {
                    DBOrdersRepository.getInstance().addOrder(o);
                } else {
                    System.out.println("show not available");
                    flow.getChildren().clear();
                    Text text = new Text(" show not available on that day");
                    flow.setLineSpacing(8);
                    flow.getChildren().add(text);
                    text.setFill(Color.WHITE);
                    text.setFont(new Font("Verdana", 10));
                }
            }
        }
    }
    void setSeats(int seat){
         this.s = seat;
        System.out.println(s);
    }
     private int getSeats(){
        return s;
     }
      private String getPayMethod(){
        return payMethod;
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
                        option[0] = t1;
                        System.out.println("OPTION " + option[0]);
                        if (option[0].equals("cash")) {
                            cardDetail("cash");
                        } else if (option[0].equals("card")) {
                            cardDetail("card");
                        }else payment.getChildren().clear();
                    }
                });

    }

}
