package CampusCommunicator.core;

import CampusCommunicator.models.User;
import CampusCommunicator.models.Notice;
import CampusCommunicator.models.Message;
import CampusCommunicator.notice.NoticeBoard;
import CampusCommunicator.chat.ChatBST;
import CampusCommunicator.chat.MessageQueue;
import CampusCommunicator.core.NotificationFeed;
import CampusCommunicator.core.TeamManager;

public class Main {
    public static void main(String[] args) {
        // USERS
        UserManager userManager = new UserManager();
        User u1 = new User("Apurva", "apurva@uni.edu", "student");
        User u2 = new User("Abhijeet", "abhi@uni.edu", "faculty");
        User u3 = new User("Anubhav", "anu@uni.edu", "faculty");
        User u4 = new User("Aniket", "aniket@uni.edu", "faculty");
        User u5 = new User("Gunita", "gun@uni.edu", "student");

        userManager.addUser(u1);
        userManager.addUser(u2);
        userManager.addUser(u3);
        userManager.addUser(u4);
        userManager.addUser(u5);

        userManager.sortUsersByName();
        userManager.searchUserByName("Anubhav");
        userManager.searchUserByName("Alice"); // Try a name that doesn't exist
        userManager.listUsers();

        // NOTICES
        NoticeBoard noticeBoard = new NoticeBoard();
        Notice n1 = new Notice("Exam Schedule", "Mid-semester exams start from 15th.", "Dr. Smith");
        Notice n2 = new Notice("Holiday Notice", "University closed on Friday.", "Admin");

        noticeBoard.postNotice(n1);
        noticeBoard.postNotice(n2);
        noticeBoard.showAllNotices();

        // CHAT SYSTEM
        System.out.println("\n--- Chat Messages ---");
        ChatBST chat = new ChatBST();
        chat.addMessage(new Message("John", "Smith", "Hey, are you attending the lecture?"));
        chat.addMessage(new Message("Smith", "John", "Yes, I'm on my way!"));
        chat.addMessage(new Message("John", "Smith", "Cool, see you there."));
        chat.showMessages();
        chat.searchMessages("lecture");
        chat.searchMessageBySender("John");

        // QUEUE
        System.out.println("\n--- Real-time Chat Queue ---");
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.sendMessage(new Message("Alice", "Bob", "Can you share the notes?"));
        messageQueue.sendMessage(new Message("Bob", "Alice", "Sure, give me a minute."));
        messageQueue.sendMessage(new Message("Alice", "Bob", "Thanks!"));
        System.out.println("\n--- Receiving Messages ---");
        messageQueue.receiveMessages();

        // NOTICE SEARCH
        System.out.println("\n--- Notice Search ---");
        noticeBoard.searchNotices("exam");

        // NOTIFICATION FEED
        NotificationFeed feed = new NotificationFeed();
        feed.push("User John registered.");
        feed.push("Notice posted: Exam Schedule");
        feed.push("New message from Smith.");
        feed.displayFeed();

        // ✅ TEAM PORTAL - 
        System.out.println("\n========== Team Portal ==========");
        TeamManager teamManager = new TeamManager();
        teamManager.listTeams();
        teamManager.displayTeam("Team B"); // change to A / C to test others

        System.out.println("\n--- Filter Notices by Author ---");
        noticeBoard.filterNoticesByAuthor("Dr. Smith");
        noticeBoard.filterNoticesByAuthor("Random Guy"); // test with no result

    }

}
