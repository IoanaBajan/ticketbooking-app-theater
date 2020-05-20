package repository;

import model.Adult;
import model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            System.out.println(adult.toString());
            return Optional.of(adult);
        }catch (IOException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
