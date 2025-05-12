package CampusCommunicator.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageQueue {
    private static MessageQueue instance;
    private List<ChatMessage> messages;
    private static final String FILE_PATH = "messages.dat";

    private MessageQueue() {
        messages = loadMessagesFromFile();
    }

    public static MessageQueue getInstance() {
        if (instance == null) {
            instance = new MessageQueue();
        }
        return instance;
    }

    public void addMessage(ChatMessage message) {
        messages.add(message);
        saveMessagesToFile();
    }

    public void displayMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages.");
        } else {
            for (ChatMessage message : messages) {
                message.displayMessage();
            }
        }
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    // ✅ KEEP ONLY THIS ONE
    public List<ChatMessage> getMessagesForUser(String email) {
        List<ChatMessage> userMessages = new ArrayList<>();
        for (ChatMessage message : messages) {
            if (message.getReceiver().equalsIgnoreCase(email)) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    private void saveMessagesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(messages);
        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    private List<ChatMessage> loadMessagesFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<ChatMessage>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading messages: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}