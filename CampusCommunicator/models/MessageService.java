package CampusCommunicator.models;

import java.util.*;

public class MessageService {
    // Map to store messages for each user
    private Map<String, List<Message>> messageStore = new HashMap<>();

    // Method to send a message
    public void sendMessage(String sender, String recipient, String content) {
        Message message = new Message(sender, recipient, content);
        messageStore.computeIfAbsent(recipient, k -> new ArrayList<>()).add(message);
    }

    // Method to get messages for a specific user
    public List<Message> getMessages(String user) {
        return messageStore.getOrDefault(user, new ArrayList<>());
    }

    // ✅ Add your two search methods below:

    // Search messages received by a user from a specific sender
    public List<Message> searchMessagesBySender(String receiver, String sender) {
        List<Message> userMessages = getMessages(receiver);
        List<Message> result = new ArrayList<>();
        for (Message msg : userMessages) {
            if (msg.getSender().equalsIgnoreCase(sender)) {
                result.add(msg);
            }
        }
        return result;
    }

    // Search messages received by a user that contain a keyword
    public List<Message> searchMessagesByKeyword(String receiver, String keyword) {
        List<Message> userMessages = getMessages(receiver);
        List<Message> result = new ArrayList<>();
        for (Message msg : userMessages) {
            if (msg.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(msg);
            }
        }
        return result;
    }
}
