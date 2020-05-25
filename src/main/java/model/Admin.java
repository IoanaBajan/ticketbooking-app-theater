package model;

import repository.DBAdultRepository;

public class Admin extends User {
    private  String securityPassPhrase;
    public Admin(String username, String password,String securityPassPhrase) {
        super(username, password);
        this.securityPassPhrase = securityPassPhrase;
    }

    public Admin() {

    }

    public static Admin getInstance() {
        return Admin.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static Admin INSTANCE = new Admin();
    }
}
