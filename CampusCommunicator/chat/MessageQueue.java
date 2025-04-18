package CampusCommunicator.chat;

import CampusCommunicator.models.Message;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<Message> queue = new LinkedList<>();

    public void sendMessage(Message message) {
        queue.add(message);
        System.out.println("Sent: " + message);
    }

    public void receiveMessages() {
        while (!queue.isEmpty()) {
            Message message = queue.poll();
            System.out.println("Received: " + message);
        }
    }

    public boolean hasMessages() {
        return !queue.isEmpty();
    }
}
