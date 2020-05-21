package service;

import model.*;
import repository.*;

import java.util.Optional;

public class LoginService {
    private StudentRepository studentRepository;
    private AdultRepository adultRepository;
    private ChildRepository childRepository;
    public LoginService() {
        studentRepository = StudentRepository.build(StudentRepository.Type.FILE);
        adultRepository = AdultRepository.build(AdultRepository.Type.FILE);
        childRepository = ChildRepository.build(ChildRepository.Type.DB);
    }

    public boolean login(User user) {
        Optional <Student> res1 = studentRepository.findUserByName(user.getUsername());
        Optional<Adult> res2 = adultRepository.findUserByName(user.getUsername());
        Optional<Child> res3 = childRepository.findUserByName(user.getUsername());

        if (res2.isPresent()){
            Adult u = res2.get();
            if(u.getPassword()!=null) {
                if(u.getPassword().equals(user.getPassword())){
                return true;
                }
            }
        }
        if (res1.isPresent()){
            Student u = res1.get();
            if(u.getPassword()!=null) {
                if (u.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }
        if (res3.isPresent()) {
            Child u = res3.get();
            if (u.getPassword() != null) {
                if(u.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }


//overloading method register
    public void register(Child client) {
        childRepository.addChild(client);

    }
    public void register(Student client) {
        studentRepository.addStudent(client);

    }


    public void register(Adult client) {
        adultRepository.addAdult(client);

    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}
