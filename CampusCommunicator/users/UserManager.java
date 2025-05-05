package CampusCommunicator.users;

import java.io.*;
import java.util.*;

import CampusCommunicator.models.User;

public class UserManager {
    private List<User> users;
    private final String filePath;

    public UserManager(String filePath) {
        this.filePath = filePath;
        this.users = new ArrayList<>();
        loadUsersFromCSV();
    }

    private void loadUsersFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromCSV(line);
                if (user != null) users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error reading users.csv: " + e.getMessage());
        }
    }

    public void saveUsersToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing users.csv: " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersToCSV();
    }

    public void deleteUserById(String id) {
        users.removeIf(u -> u.getId().equals(id));
        saveUsersToCSV();
    }

    public void updateUserRole(String id, String newRole) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setRole(newRole);
                break;
            }
        }
        saveUsersToCSV();
    }

    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsersByRole(String role) {
        List<User> filtered = new ArrayList<>();
        for (User user : users) {
            if (user.getRole().equalsIgnoreCase(role)) {
                filtered.add(user);
            }
        }
        return filtered;
    }

    public List<User> getUsersBySubject(String subject) {
        List<User> filtered = new ArrayList<>();
        for (User user : users) {
            if (user.getSubject().equalsIgnoreCase(subject)) {
                filtered.add(user);
            }
        }
        return filtered;
    }
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
    
}
