package service;

import model.Client;
import repository.ClientRepository;

import java.util.Optional;

public class LoginService {

    public boolean login(Client client) {
        ClientRepository clientRepository = ClientRepository.getInstance();
        Optional<Client> c = clientRepository.findUserByUsername(client.getUsername());

        if(c.isPresent()) {
            Client clt = c.get();
            return clt.getPassword().equals(client.getPassword());
        }

        return false;
    }

    public void register(Client client) {
        ClientRepository clientRepository = ClientRepository.getInstance();
        clientRepository.addClient(client);
    }

    public static LoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static LoginService INSTANCE = new LoginService();
    }
}
