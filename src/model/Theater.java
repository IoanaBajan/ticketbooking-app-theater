package model;

import service.BuyService;
import service.InformationService;
import service.LoginService;

public class Theater {
    public void showInformationAdmin() {
        LoginService service = new LoginService();
        Client c = new Client("Ioana","locked");
        service.register(c);
        InformationService s = new InformationService();
        s.showClients();
    }
    public void showInformationUser() {
        InformationService s = new InformationService();
        s.showEvents();
    }
    public void showPrice( Object x,Object y,int seat) {

        BuyService s = new BuyService();
        s.showPrice(x,y,seat);

    }

}
