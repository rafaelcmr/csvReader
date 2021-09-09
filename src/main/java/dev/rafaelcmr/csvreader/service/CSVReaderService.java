package dev.rafaelcmr.csvreader.service;

import dev.rafaelcmr.csvreader.model.User;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVReaderService {

    public List<User> readUsersFromCSV(String fileName){
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                User user = createUser(attributes);
                users.add(user);
                line = br.readLine();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return users;
    }

    private static User createUser(String[] metadata) {
        String id = metadata[0];
        String name = metadata[1];
        String username = metadata[2];
        return new User(id, name, username);
        }


}
