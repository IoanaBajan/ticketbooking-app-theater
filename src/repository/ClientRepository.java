package repository;
import model.Child;
import model.Client;
import model.Student;

import java.util.ArrayList;
import java.util.Optional;

public class ClientRepository {
    public ArrayList<Client> getClients() {
        return clients;
    }

    private ArrayList<Client> clients = new ArrayList<> ();


    private ClientRepository() {
        this.clients.add(new Client("Alexandra","qwerty"));
        this.clients.add(new Client("Matei","12345"));
        this.clients.add( new Client("Silvia","09876"));
    }

    public void addClient(Client c) {
        clients.add(c);
    }
//    public void addChild(Child c) {
//        clients.add(c);
//    }
    public void addClient(Student c) {
        clients.add(c);
    }

    public Optional<Client> findUserByUsername(String username) {
        for (Client c : clients) {
            if (c != null) {
                if (username.equals(c.getUsername())) {
                    return Optional.of(c);
                }
            }
        }

        return Optional.empty();
    }

    public static ClientRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static ClientRepository INSTANCE = new ClientRepository();
    }
}
