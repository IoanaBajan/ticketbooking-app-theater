package repository;

import exceptions.InexistentFileException;
import model.TheaterPlay;
import service.AuditService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileTheaterPlaysRepository implements TheaterPlaysRepository{
    private final String file = "TheaterPlays.csv";

    @Override
    public void addPlay(TheaterPlay play) {
        try(PrintWriter out = new PrintWriter(new FileWriter(file,true))){

            out.println(play.getName() + "," +play.getActors() + "," +play.getDirector()+","+play.getMaxNumberSeats() + "," + play.getDate().toString() + "," + play.getEndDate().toString()  + "," + play.getNrRepresentations());
        }catch(IOException e ){
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("added "+ play.getName(),t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public TheaterPlay findPlay(String name) {
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
                    play.setActors(attr[1]);
                    play.setDirector(attr[2]);
                    play.setMaxNumberSeats(Integer.parseInt(attr[3]));
                    play.setDate(attr[4]);
                    play.setEndDate(attr[5]);
                    play.setNrRepresentations(Integer.parseInt(attr[6]));

                    break;
                }
            }
            System.out.println(play.toString());
            return play;
        }catch (IOException e){
            e.printStackTrace();
        }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("searched for " + name,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletePlay(String name) {
        TheaterPlay p = findPlay(name);

    }

    @Override
    public void updatePlay(String name, String choice, String newValue) {
        TheaterPlay play = findPlay(name);
        File inFile = new File("TheaterPlays.csv");
        File tempFile = new File("TheaterPlaystemp.csv");
        if(play!=null){

                try{
                    Path path = Paths.get(file);

                    if (!Files.exists(path)) {
                        throw new InexistentFileException();
                    }
                    var list = Files.readAllLines(path);
                    for (String s : list) {
                        String[] attr = s.split(",");
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        try(PrintWriter out_temp = new PrintWriter(new FileWriter(tempFile))){
                            if(choice.equals("actors")) {

                                if (!attr[1].equals(newValue)) {
                                    out_temp.println(play.getName() + "," + newValue + "," + play.getDirector() + "," +
                                            play.getMaxNumberSeats() + "," + play.getDate().toString() + "," +
                                            play.getEndDate().toString() + "," + play.getNrRepresentations());
                                } else {
                                    out_temp.println(s);
                                }
                                out_temp.flush();
                            }else if(choice.equals("startDate")){
                                if (!attr[4].equals(newValue)) {
                                    out_temp.println(play.getName() + "," + play.getActors() + "," + play.getDirector()
                                            + "," + play.getMaxNumberSeats() + "," + newValue
                                            + "," + play.getEndDate().toString() + "," + play.getNrRepresentations());
                                } else {
                                    out_temp.println(s);
                                }
                                out_temp.flush();
                            }else if(choice.equals("endDate")){
                                if (!attr[5].equals(newValue)) {
                                    out_temp.println(play.getName() + "," + play.getActors() + "," + play.getDirector()
                                            + "," + play.getMaxNumberSeats() + "," + play.getStartDate().toString()
                                            + "," +newValue + "," + play.getNrRepresentations());
                                } else {
                                    out_temp.println(s);
                                }
                                out_temp.flush();
                            }
                            }catch(IOException e ){
                                e.printStackTrace();
                            }
                        br.close();

                    }
                    if (!inFile.delete()) {
                        System.out.println("Could not delete file");
                        return;
                    }

                    if (!tempFile.renameTo(inFile))
                        System.out.println("Could not rename file");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("updated " + name,t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                play.setActors(attr[1]);
                play.setDirector(attr[2]);
                play.setMaxNumberSeats(Integer.parseInt(attr[3]));
                play.setDate(attr[4]);
                play.setEndDate(attr[5]);
                play.setNrRepresentations(Integer.parseInt(attr[6]));

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
        Thread t = new Thread();
        t.start();
        try {
            AuditService.getInstance().addToAuditFile("show all plays ",t.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
