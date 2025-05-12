package CampusCommunicator.chat;

import CampusCommunicator.models.User;
import CampusCommunicator.chat.ChatMessage;
import CampusCommunicator.chat.MessageQueue;

import java.util.List;
import java.util.Scanner;

public class ChatManager {
    private User user;
    private MessageQueue messageQueue;
    private Scanner scanner;

    public ChatManager(User user) {
        this.user = user;
        this.messageQueue = MessageQueue.getInstance();  // ✅ Shared instance
        this.scanner = new Scanner(System.in);
    }
     
    public void displayChatOptions() {
        if (user.getRole().equalsIgnoreCase("faculty")) {
            // Faculty-specific options: Send/View messages from students
            System.out.println("1. View Messages from Students");
            System.out.println("2. Send Message to Students");
        } else if (user.getRole().equalsIgnoreCase("student")) {
            // Student-specific options: Send/View messages from faculty or other students
            System.out.println("1. View Messages from Faculty");
            System.out.println("2. Send Message to Faculty");
            System.out.println("3. Send Message to Other Students");
        } else {
            System.out.println("Unrecognized user role. Access denied.");
            return;
        }

        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                viewMessages();
                break;
            case 2:
                sendMessage();
                break;
            case 3:
                if (user.getRole().equalsIgnoreCase("student")) {
                    sendMessageToOtherStudents();
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // View messages for students
    private void viewMessages() {
        System.out.println("\n--- Your Messages ---");
        List<ChatMessage> msgs = messageQueue.getMessagesForUser(user.getEmail());
        if (msgs.isEmpty()) {
            System.out.println("No messages.");
        } else {
            for (ChatMessage msg : msgs) {
                msg.displayMessage();
            }
        }
    }
    

    // Send a message to a faculty member
    private void sendMessage() {
        System.out.print("Enter recipient's email: ");
        String recipientEmail = scanner.nextLine();
    
        System.out.print("Enter your message: ");
        String messageText = scanner.nextLine();
    
        ChatMessage message = new ChatMessage(user.getEmail(), recipientEmail, messageText);
        messageQueue.addMessage(message);
        System.out.println("Message sent to " + recipientEmail);
    }
    
    // Send a message to another student
    private void sendMessageToOtherStudents() {
        System.out.print("Enter recipient's email: ");
        String recipientEmail = scanner.nextLine();
        System.out.print("Enter message for student: ");
        String messageText = scanner.nextLine();

        // Add logic to send the message to the student
        ChatMessage message = new ChatMessage(user.getEmail(), recipientEmail, messageText);
        messageQueue.addMessage(message);
        System.out.println("Message sent to student.");
    }
}