package service;

import repository.DBStudentRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import static java.lang.System.currentTimeMillis;

public class AuditService {
    Timestamp time;
    public void addToAuditFile(String filePath, String action, String threadName) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);

        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        time = new Timestamp(currentTimeMillis());
        printWriter.println(action + "," + time + "," + threadName);

        printWriter.flush();
        printWriter.close();
    }
    private static class SingletonHolder {
        private static DBStudentRepository INSTANCE = new DBStudentRepository();
    }
}
