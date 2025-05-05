package CampusCommunicator.chat;

import java.io.Serializable;

public class ChatMessage implements Serializable {
    private String sender;
    private String receiver;
    private String content;

    public ChatMessage(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public void displayMessage() {
        System.out.println("From: " + sender + " | To: " + receiver + " | Message: " + content);
    }
}
