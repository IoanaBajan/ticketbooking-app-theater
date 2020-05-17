package service;

import model.*;
import repository.AdultRepository;
import repository.ChildRepository;
import repository.StudentRepository;

import java.sql.SQLException;
import java.util.Optional;

public class LoginService {

    public LoginService() {
       AdultRepository adultRepository = new AdultRepository();

    }

    public boolean login(User user) {
        StudentRepository studentRepository = StudentRepository.getInstance();
        AdultRepository adultRepository = AdultRepository.getInstance();
        ChildRepository childRepository = ChildRepository.getInstance();
        Optional<Student> o1 = studentRepository.findUserByUsername(user.getUsername());
        Optional<Adult> o2 = adultRepository.findUserByUsername(user.getUsername());
        Optional<Child> o3 = childRepository.findUserByUsername(user.getUsername());

        if(o1.isPresent()) {
            User clt = o1.get();
            return clt.getPassword().equals(user.getPassword());
        }else if(o2.isPresent()){
            User clt = o2.get();
            return clt.getPassword().equals(user.getPassword());
        }else if(o3.isPresent()){
            User clt = o3.get();
            return clt.getPassword().equals(user.getPassword());
        }

        return false;
    }
    public boolean loginDB (String username, String password) {
            StudentRepository S = new StudentRepository();
            AdultRepository A = new AdultRepository();
            ChildRepository C = new ChildRepository();
            if(S.findUserInDB(username)!=null) {
            if ((S.findUserInDB(username).getPassword()).equals(password))
                return true;
            }
            if (A.findUserInDB(username)!=null){
                if((A.findUserInDB(username).getPassword()).equals(password))
                    return true;
                }
            if (C.findUserInDB(username)!=null){
                if((C.findUserInDB(username).getPassword()).equals(password))
                    return true;
            }
            return false;
    }

    public void register(Object client) {
        if(client instanceof Student)
            StudentRepository.addStudent((Student) client);
        else if(client instanceof Adult)
            AdultRepository.addAdult((Adult) client);
        if(client instanceof Child)
            ChildRepository.addChild((Child) client);
    }

    public void registerInDB(Object client) {
        if (client instanceof Student) {
            StudentRepository S = new StudentRepository();
            S.addToDtbase((Student) client);
        } else if (client instanceof Adult) {
            AdultRepository A = new AdultRepository();
            A.addToDtbase((Adult) client);
        } else if (client instanceof Child){
            ChildRepository C = new ChildRepository();
            C.addToDtbase((Child) client);
        }
    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}
