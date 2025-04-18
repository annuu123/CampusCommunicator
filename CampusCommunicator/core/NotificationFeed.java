package CampusCommunicator.core;

public class NotificationFeed {

    private static class Node {
        String notification;
        Node next;

        Node(String notification) {
            this.notification = notification;
            this.next = null;
        }
    }

    private Node head;

    // Add a new notification to the beginning of the feed
    public void push(String notification) {
        Node newNode = new Node(notification);
        newNode.next = head;
        head = newNode;
    }

    // Display the notification feed (latest first)
    public void displayFeed() {
        System.out.println("\n--- Notification Feed ---");
        Node temp = head;
        while (temp != null) {
            System.out.println("🔔 " + temp.notification);
            temp = temp.next;
        }
    }
}
