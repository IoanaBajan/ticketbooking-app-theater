package model;

import repository.StudentRepository;
import service.BuyService;
import service.InformationService;
//import service.LoginService;

public class Theater {
    public void showInformationAdmin() {
////        LoginService service = new LoginService();
//        Student s1 = new Student("yolo123","locked","Ioana",20,21564);
////        service.register(c);
    }
//    public void showInformationUser() {
//        InformationService s = new InformationService();
//        s.showClients();
//    }
//    public void showInformationEvent() {
//        InformationService s = new InformationService();
//        s.showEvents();
//    }
    public void showPrice( Object x,Object y,int seat) {

        BuyService s = new BuyService();
        s.showPrice(x,y,seat);

    }

}
