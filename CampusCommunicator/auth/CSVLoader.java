package CampusCommunicator.auth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import CampusCommunicator.models.User;
import CampusCommunicator.auth.AuthManager;

public class CSVLoader {
    public static void loadUsersFromCSV(String filePath, AuthManager authManager) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                
                // Make sure there are 5 fields as the User constructor now expects 5 arguments
                if (fields.length == 5) {
                    String id = fields[0];
                    String name = fields[1];
                    String email = fields[2];
                    String role = fields[3];
                    String subject = fields[4];

                    // Create a new User object with the updated constructor
                    User user = new User(id, name, email, role, subject);

                    // Register the user in the auth manager
                    authManager.registerUser(user, email);  // Assuming the email is used as password for demo purposes
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
