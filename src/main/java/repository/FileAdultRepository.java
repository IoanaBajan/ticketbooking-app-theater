package repository;

import exceptions.InexistentFileException;
import model.Adult;
import service.AuditService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

public class FileAdultRepository implements AdultRepository{
    private final String file = "Adults.csv";

    @Override
    public void addAdult(Adult a) {
        try(PrintWriter out = new PrintWriter(new FileWriter(file,true))){
            out.println(a.getUsername() + "," + a.getPassword()+ "," +a.getFirstName());
        }catch(IOException e ){
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile(a.getUsername()+" registered",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Adult> findUserByName(String username) {
        Path path = Paths.get(file);
        Adult adult = new Adult();
        try{
            var list = Files.readAllLines(path);
            for (String s:list) {
                String[] attr = s.split(",");
                if (attr[0].equals(username)) {
                    adult.setUsername(attr[0]);
                    adult.setPassword(attr[1]);
                    adult.setFirstName(attr[2]);
                    break;
                }
            }
            return Optional.of(adult);
        }catch (IOException e){
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("searched for "+ username,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Adult> getAdults() {
        Path path = Paths.get(file);
        ArrayList<Adult> adults = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                Adult adult = new Adult();
                adult.setUsername(attr[0]);
                adult.setPassword(attr[1]);
                adult.setFirstName(attr[2]);
                adults.add(adult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adults;
    }

    public void showAdults() {
        ArrayList<Adult> c = getAdults();
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).toString());
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("printed all adult clients ",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
