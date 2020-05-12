package service;

import model.Client;
import model.User;
import repository.StudentRepository;

import java.util.Optional;

public class LoginService {

    public boolean login(User user) {
        StudentRepository studentRepository = StudentRepository.getInstance();
////        Optional<Client> c = studentRepository.findUserByUsername(client.getUsername());
//
//        if(c.isPresent()) {
//            Client clt = c.get();
//            return clt.getPassword().equals(client.getPassword());
//        }

        return false;
    }
//
//    public void register(Object client) {
//        ClientRepository clientRepository = ClientRepository.getInstance();
////        if(cl)
//        clientRepository.addClient(client);
//    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}
