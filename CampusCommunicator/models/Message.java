package CampusCommunicator.models;

public class Message {
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = java.time.LocalDateTime.now().toString();
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

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + sender + " ➔ " + receiver + ": " + content;
    }
}
