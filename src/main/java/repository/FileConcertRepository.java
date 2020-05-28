package repository;

import exceptions.InexistentFileException;
import model.Concert;
import service.AuditService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

public class FileConcertRepository implements ConcertRepository {
    private final String file = "Concerts.csv";

    @Override
    public void addConcert(Concert concert) {
        try(PrintWriter out = new PrintWriter(new FileWriter(file,true))){
            out.println(concert.getName() + "," + concert.getDate().toString()+ "," + concert.getMaxNumberSeats() + ","+concert.getPerformers());
        }catch(IOException e ){
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("added "+ concert.getName(),t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Concert> getConcerts(){
        Path path = Paths.get(file);
        ArrayList<Concert> concerts = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                Concert concert = new Concert();
                concert.setName(attr[0]);
                concert.setDate(attr[1]);
                concert.setMaxNumberSeats(Integer.parseInt(attr[2]));
                concert.setPerformers(attr[3]);

                concerts.add(concert);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return concerts;
    }
    @Override
    public void showConcerts() {
        ArrayList<Concert> c = getConcerts();
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).toString());
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("show all concerts ",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Concert> findConcert(String name) {
        Path path = Paths.get(file);
        Concert concert = new Concert();
        try{
            var list = Files.readAllLines(path);
            for (String s:list) {
                String[] attr = s.split(",");
                if (attr[0].equals(name)) {
                    concert.setName(attr[0]);
                    concert.setDate(attr[1]);
                    concert.setMaxNumberSeats(Integer.parseInt(attr[2]));
                   concert.setPerformers(attr[3]);
                    break;
                }
            }
            System.out.println(concert.toString());
            return Optional.of(concert);
        }catch (IOException e){
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("searched for "+ name,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void deleteConcert(String name) {
        Optional<Concert> c =findConcert(name);
    }
}
