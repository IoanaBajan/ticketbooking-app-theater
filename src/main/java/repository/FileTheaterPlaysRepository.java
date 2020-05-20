package repository;

import exceptions.InexistentFileException;
import model.Adult;
import model.Concert;
import model.TheaterPlay;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class FileTheaterPlaysRepository implements TheaterPlaysRepository{
    private final String file = "TheaterPlays.csv";

    @Override
    public void addPlay(TheaterPlay play) {
        try(PrintWriter out = new PrintWriter(new FileWriter(file,true))){
            out.println(play.getName() + "," + play.getDate().toString()+ "," + play.getActors() + "," +play.getDirector()+","+play.getMaxNumberSeats());
        }catch(IOException e ){
            e.printStackTrace();
        }
    }
    @Override
    public Optional<TheaterPlay> findPlay(String name) {
        Path path = Paths.get(file);
        TheaterPlay play = new TheaterPlay();
        try{
            if(!Files.exists(path)){
                throw new InexistentFileException();
            }
            var list = Files.readAllLines(path);
            for (String s:list) {
                String[] attr = s.split(",");
                if (attr[0].equals(name)) {
                    play.setName(attr[0]);
                    play.setDate(attr[1]);
                    play.setActors(attr[2]);
                    play.setDirector(attr[3]);
                    play.setMaxNumberSeats(Integer.parseInt(attr[4]));

                    break;
                }
            }
            System.out.println(play.toString());
            return Optional.of(play);
        }catch (IOException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public ArrayList<TheaterPlay> getPlays(){
        Path path = Paths.get(file);
        ArrayList<TheaterPlay> plays = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                TheaterPlay play = new TheaterPlay();
                play.setName(attr[0]);
                play.setDate(attr[1]);
                play.setActors(attr[2]);
                play.setDirector(attr[3]);
                play.setMaxNumberSeats(Integer.parseInt(attr[4]));

                plays.add(play);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plays;
    }
    @Override
    public void showPlays() {
        ArrayList<TheaterPlay> p = getPlays();
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).toString());
        }
    }

}
