package CampusCommunicator.core;

import CampusCommunicator.models.User;
import CampusCommunicator.users.UserManager;
import CampusCommunicator.notice.NoticeManager;
import CampusCommunicator.chat.ChatManager;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static UserManager userManager;
    private static final String CREDENTIAL_FILE = "CampusCommunicator/core/data.csv";
    private static final NoticeManager noticeManager = new NoticeManager();


    public static void main(String[] args) {
        while (true) {
            userManager = new UserManager("CampusCommunicator/core/users.csv");
            System.out.println("\n=== Welcome to Campus Communicator ===");
            System.out.print("Enter Gmail: ");
            String email = scanner.nextLine().trim();

            Console console = System.console();
            String password;
            if (console != null) {
                char[] passwordChars = console.readPassword("Enter Password: ");
                password = new String(passwordChars);
            } else {
                System.out.print("Enter Password: ");
                password = scanner.nextLine().trim();
            }

            if (!isAuthenticated(email, password)) {
                System.out.println("Authentication failed!");
                continue;  // Go back to login
            }

            User currentUser = userManager.getUserByEmail(email);
            if (currentUser == null) {
                System.out.println("User profile not found in users.csv.");
                continue;  // Go back to login
            }

            System.out.println("Logged in as: " + currentUser.getName() + " (Role: " + currentUser.getRole() + ")");

            switch (currentUser.getRole().toLowerCase()) {
                case "admin" -> runAdminPanel();
                case "faculty" -> runFacultyPanel(currentUser);
                case "student" -> runStudentPanel(currentUser);
                default -> System.out.println("Unrecognized role. Access denied.");
            }

            // After logout
            System.out.println("You have been logged out.\n");
        }
    }
    

    private static boolean isAuthenticated(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIAL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.strip().split(",");
                if (parts.length == 2) {
                    if (parts[0].equalsIgnoreCase(email) && parts[1].equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading credentials file: " + e.getMessage());
        }
        return false;
    }

    // Admin Panel
    private static void runAdminPanel() {
        while (true) {
            System.out.println("\n=== Admin User Management Panel ===");
            System.out.println("1. Add User");
            System.out.println("2. Delete User by ID");
            System.out.println("3. Update User Role");
            System.out.println("4. List All Users");
            System.out.println("5. Get Users by Role");
            System.out.println("6. Get Users by Subject");
            System.out.println("7. Get User by ID");
            System.out.println("8. Notice Board Management");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addUser();
                case 2 -> deleteUser();
                case 3 -> updateUserRole();
                case 4 -> listAllUsers();
                case 5 -> listUsersByRole();
                case 6 -> listUsersBySubject();
                case 7 -> getUserById();
                case 8 -> manageNotices();
                case 0 -> {
                    System.out.println("Exiting admin panel...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // Faculty Panel
    private static void runFacultyPanel(User faculty) {
        while (true) {
            System.out.println("\n=== Faculty Panel ===");
            System.out.println("1. View Notices");
            System.out.println("2. Post Homework");
            System.out.println("3. Send/Receive Messages");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewNotices();
                case 2 -> postHomework(faculty);
                case 3 -> manageMessages(faculty);
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // Student Panel
    private static void runStudentPanel(User student) {
        while (true) {
            System.out.println("\n=== Student Panel ===");
            System.out.println("1. View Notices");
            System.out.println("2. View Homework");
            System.out.println("3. Send/Receive Messages");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewNotices();
                case 2 -> viewHomework(student); // View homework for specific student
                case 3 -> manageMessages(student);
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // Manage Messages (both for faculty and student)
    private static void manageMessages(User user) {
        ChatManager chatManager = new ChatManager(user);
        chatManager.displayChatOptions();
    }

    // View Notices (common for both faculty and students)
    private static void viewNotices() {
        noticeManager.displayNotices();
    }

    // Post Homework (only for faculty)
    private static void postHomework(User faculty) {
        System.out.print("Enter subject you teach: ");
        String subject = scanner.nextLine();

        System.out.print("Enter homework description: ");
        String homework = scanner.nextLine();

        // Store homework, this should be visible only to students with the same subject
        
        noticeManager.addHomework(subject, homework);
        System.out.println("Homework posted successfully!");
    }

    // View Homework (only for students)
    private static void viewHomework(User student) {
        System.out.println("Fetching homework for subject(s) you are enrolled in...");
        
        // Get the subject(s) that the student is enrolled in (assuming the `subject` is available in the `student` object)
        String studentSubject = student.getSubject();  // Fetch student's subject
        
    
        noticeManager.displayHomeworkForStudent(studentSubject);
    }

    // Add User Logic
    private static void addUser() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        System.out.print("Enter Subject: ");
        String subject = scanner.nextLine();

        User newUser = new User(id, name, email, role, subject);
        userManager.addUser(newUser);
        System.out.println("User added successfully.");
    }

    // Delete User Logic
    private static void deleteUser() {
        System.out.print("Enter ID of user to delete: ");
        String id = scanner.nextLine();
        userManager.deleteUserById(id);
        System.out.println("User deleted if ID existed.");
    }

    // Update User Role Logic
    private static void updateUserRole() {
        System.out.print("Enter ID of user to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter new role: ");
        String newRole = scanner.nextLine();
        userManager.updateUserRole(id, newRole);
        System.out.println("Role updated if ID existed.");
    }

    // List All Users Logic
    private static void listAllUsers() {
        List<User> users = userManager.getUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(System.out::println);
        }
    }

    // List Users by Role Logic
    private static void listUsersByRole() {
        System.out.print("Enter role to filter: ");
        String role = scanner.nextLine();
        List<User> users = userManager.getUsersByRole(role);
        if (users.isEmpty()) {
            System.out.println("No users found with role: " + role);
        } else {
            users.forEach(System.out::println);
        }
    }

    // List Users by Subject Logic
    private static void listUsersBySubject() {
        System.out.print("Enter subject to filter: ");
        String subject = scanner.nextLine();
        List<User> users = userManager.getUsersBySubject(subject);
        if (users.isEmpty()) {
            System.out.println("No users found with subject: " + subject);
        } else {
            users.forEach(System.out::println);
        }
    }

    // Get User by ID Logic
    private static void getUserById() {
        System.out.print("Enter ID to search: ");
        String id = scanner.nextLine();
        User user = userManager.getUserById(id);
        if (user == null) {
            System.out.println("No user found with ID: " + id);
        } else {
            System.out.println(user);
        }
    }



    private static void displayNotices() {
        noticeManager.displayNotices();
    }

    public static void manageNotices() {
        Scanner scanner = new Scanner(System.in);
        // Assuming NoticeManager is initialized like this
    
        System.out.println("Choose an option:");
        System.out.println("1. Add a notice");
        System.out.println("2. View all notices");
        int option = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
    
        switch (option) {
            case 1:
                // Add a new notice
                System.out.print("Enter notice title: ");
                String title = scanner.nextLine();
                System.out.print("Enter notice message: ");
                String message = scanner.nextLine();
                noticeManager.addNotice(title, message);  // Assuming addNotice() takes two parameters
                break;
    
            case 2:
                // View all notices
                noticeManager.displayNotices();  // Assuming you have a method to list all notices
                break;
    
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    

    // Helper methods for other required logic...
}
