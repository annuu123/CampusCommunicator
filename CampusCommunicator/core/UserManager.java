package CampusCommunicator.core;

import CampusCommunicator.models.User;

class Node {
    User user;
    Node next;

    Node(User user) {
        this.user = user;
        this.next = null;
    }
}

public class UserManager {
    private Node head;

    public void addUser(User user) {
        Node newNode = new Node(user);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
        System.out.println("✅ User added: " + user);
    }

    public void listUsers() {
        Node temp = head;
        System.out.println("\n📋 List of Users:");
        while (temp != null) {
            System.out.println(temp.user);
            temp = temp.next;
        }
    }

    // 🔤 Sort the linked list alphabetically by user name
    public void sortUsersByName() {
        if (head == null || head.next == null) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.user.getName().compareTo(current.next.user.getName()) > 0) {
                    // Swap users
                    User tempUser = current.user;
                    current.user = current.next.user;
                    current.next.user = tempUser;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);

        System.out.println("\n🔤 Users sorted by name:");
        listUsers();
    }
    //Search user by name:
    public void searchUserByName(String name) {
        Node temp = head;
        boolean found = false;
        
        while (temp != null) {
            if (temp.user.getName().equalsIgnoreCase(name)) {
                System.out.println("\n🔎 User found:");
                System.out.println(temp.user);
                found = true;
                break;
            }
            temp = temp.next;
        }
        
        if (!found) {
            System.out.println("\n❌ User not found with name: " + name);
        }
    }
    
}
