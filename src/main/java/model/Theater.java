package model;

import repository.StudentRepository;
import service.BuyService;
import service.InformationService;
//import service.LoginService;

public class Theater {
    public void showInformationAdmin() {
    }
    public void showPrice( Object x,Object y,int seat) {

        BuyService s = new BuyService();
        s.showPrice(x,y,seat);

    }

}
