package CampusCommunicator.chat;

import CampusCommunicator.models.Message;
import java.util.ArrayList;
import java.util.List;

public class ChatBST {

    private class Node {
        Message message;
        Node left, right;

        Node(Message message) {
            this.message = message;
        }
    }

    private Node root;

    public void addMessage(Message message) {
        root = insert(root, message);
    }

    private Node insert(Node root, Message message) {
        if (root == null) {
            return new Node(message);
        }
        if (message.getTimestamp().compareTo(root.message.getTimestamp()) < 0) {
            root.left = insert(root.left, message);
        } else {
            root.right = insert(root.right, message);
        }
        return root;
    }

    public void showMessages() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.message);
            inOrderTraversal(root.right);
        }
    }

    // ✅ New: Get all messages in sorted order
    public List<Message> getAllMessagesInOrder() {
        List<Message> messages = new ArrayList<>();
        inOrderToList(root, messages);
        return messages;
    }

    private void inOrderToList(Node node, List<Message> messages) {
        if (node != null) {
            inOrderToList(node.left, messages);
            messages.add(node.message);
            inOrderToList(node.right, messages);
        }
    }

    // ✅ New: Search for messages by sender
    public void searchMessageBySender(String sender) {
        List<Message> messages = getAllMessagesInOrder();
        boolean found = false;
        for (Message m : messages) {
            if (m.getSender().equalsIgnoreCase(sender)) {
                System.out.println("\n🔎 Found message from " + sender + ": " + m);
                found = true;
            }
        }
        if (!found) {
            System.out.println("\n❌ No message found from sender: " + sender);
        }
    }
    public void searchMessages(String keyword) {
        System.out.println("🔍 Searching for messages containing: " + keyword);
        searchMessagesRecursive(root, keyword);
    }
    
    private void searchMessagesRecursive(Node node, String keyword) {
        if (node != null) {
            searchMessagesRecursive(node.left, keyword);
            if (node.message.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(node.message);
            }
            searchMessagesRecursive(node.right, keyword);
        }
    }
    
}
