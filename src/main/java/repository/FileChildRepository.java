package repository;


import exceptions.InexistentFileException;
import model.Child;
import model.Concert;

import javax.crypto.Cipher;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

public class FileChildRepository implements ChildRepository{
    private final String file = "Children.csv";

    @Override
    public void addChild(Child a) {
        try(PrintWriter out = new PrintWriter(new FileWriter(file,true))){
            out.println(a.getUsername() + "," + a.getPassword()+ "," +a.getFirstName());
        }catch(IOException e ){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Child> findUserByName(String username) {
        Path path = Paths.get(file);
        Child child = new Child();
        try{
            var list = Files.readAllLines(path);
            for (String s:list) {
                String[] attr = s.split(",");
                if (attr[0].equals(username)) {
                    child.setUsername(attr[0]);
                    child.setPassword(attr[1]);
                    child.setFirstName(attr[2]);
                    child.setAccompanied(Integer.parseInt(attr[3]));
                    break;
                }
            }
            System.out.println(child.toString());
            return Optional.of(child);
        }catch (IOException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }
    @Override
    public void showChildren() {
        ArrayList<Child> c = getChildren();
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i).toString());
        }
    }
    public ArrayList<Child> getChildren(){
        Path path = Paths.get(file);
        ArrayList<Child> children = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                Child child = new Child();
                child.setUsername(attr[0]);
                child.setPassword(attr[1]);
                child.setFirstName(attr[2]);
                child.setAccompanied(Integer.parseInt(attr[3]));
                children.add(child);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return children;
    }
}
