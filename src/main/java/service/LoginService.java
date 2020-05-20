package service;

import model.*;
import repository.*;

import java.util.Optional;

public class LoginService {
    private StudentRepository studentRepository;
    private AdultRepository adultRepository;
    private ChildRepository childRepository;
    public LoginService() {
        studentRepository = StudentRepository.build(StudentRepository.Type.DB);
        adultRepository = AdultRepository.build(AdultRepository.Type.DB);
        childRepository = ChildRepository.build(ChildRepository.Type.DB);
    }

    public boolean login(User user) {
        Optional<Student> o1 = studentRepository.findUserByName(user.getUsername());
        Optional<Adult> o2 = adultRepository.findUserByName(user.getUsername());
        Optional<Child> o3 = childRepository.findUserByName(user.getUsername());

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

        Optional <Student> res1 = studentRepository.findUserByName(username);
        if (res1.isPresent()){
            Student u = res1.get();
            if(u.getPassword()!=null && u.getPassword().equals(password)){
                return true;
            }else System.out.println("password and username do not match");
        }else System.out.println("user not registered");
        return false;
    }

//overloading method register
    public void register(Child client) {
        childRepository.addChild(client);

    }
    public void register(Student client) {
        studentRepository.addStudent(client);

    }
    public void deleteClient(Student client) {
        String username = client.getUsername();
        DBStudentRepository.deleteStudent(username);
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
