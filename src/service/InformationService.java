package service;

import repository.ClientRepository;
import repository.EventRepository;

public class InformationService {
    public void showClients(){
        ClientRepository clientRepository = ClientRepository.getInstance();
        for(int i = 0; i < (clientRepository.getClients()).size(); i++)  {
            System.out.println(clientRepository.getClients().get(i).toString());
        }
    }
    public void showEvents() {
        EventRepository eventRepository = EventRepository.getInstance();
        System.out.println(eventRepository.getEvents());
        for (int i = 0; i < (eventRepository.getEvents()).size(); i++) {
            System.out.println(eventRepository.getEvents().get(i).toString());
        }
    }
    public static InformationService getInstance() {
        return InformationService.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static InformationService INSTANCE = new InformationService();
    }
}
