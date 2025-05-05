package CampusCommunicator.auth;

import CampusCommunicator.models.User;
import java.util.HashMap;
import java.util.Map;

public class AuthManager {
    private Map<String, String> userCredentials; // For demo purposes: username -> password
    private Map<String, User> users; // Stores user information by email
    private User loggedInUser;

    public AuthManager() {
        userCredentials = new HashMap<>();
        users = new HashMap<>();
        loggedInUser = null;
    }

    // Register users (for testing purposes)
    public void registerUser(User user, String password) {
        userCredentials.put(user.getEmail(), password);
        users.put(user.getEmail(), user);
        System.out.println("User registered: " + user.getName());
    }

    // Load users from CSV file
    public void loadUsersFromCSV(String filePath) {
        CSVLoader.loadUsersFromCSV(filePath, this); // Calling the CSVLoader utility
    }

    // Login method with role support
    public boolean login(String email, String password) {
        if (userCredentials.containsKey(email) && userCredentials.get(email).equals(password)) {
            loggedInUser = users.get(email);
            System.out.println("User logged in: " + loggedInUser.getName());
            return true;
        }
        System.out.println("Invalid email or password.");
        return false;
    }

    // Logout method
    public void logout() {
        if (loggedInUser != null) {
            System.out.println("User logged out: " + loggedInUser.getName());
            loggedInUser = null;
        }
    }

    // Get current logged-in user
    public User getLoggedInUser() {
        return loggedInUser;
    }

    // Check if user is logged in
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Get role of logged-in user
    public String getRole() {
        if (loggedInUser != null) {
            return loggedInUser.getRole();
        }
        return "guest"; // default role if not logged in
    }
}
