// NoticeManager.java
package CampusCommunicator.notice;

import CampusCommunicator.models.Notice;
import java.util.Scanner;

public class NoticeManager {
    private NoticeBoard noticeBoard;
    private NoticeBST noticeBST;
    private Scanner scanner;

    public NoticeManager() {
        this.noticeBoard = new NoticeBoard();
        this.noticeBST = new NoticeBST();  // Creating instance of NoticeBST
        this.scanner = new Scanner(System.in);
    }

    // Display menu to the user
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Notice Board Menu ---");
            System.out.println("1. Post Notice");
            System.out.println("2. Show All Notices");
            System.out.println("3. Delete Notice");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    postNotice();
                    break;
                case 2:
                    noticeBoard.showAllNotices();
                    break;
                case 3:
                    deleteNotice();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to post a new notice
    private void postNotice() {
        System.out.print("Enter the notice title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter the notice content: ");
        String content = scanner.nextLine();
        
        System.out.print("Enter your name (Posted By): ");
        String postedBy = scanner.nextLine();
        
        // Create the new notice with the provided details
        Notice notice = new Notice(title, content, postedBy);
        
        // Post the notice to the NoticeBoard and NoticeBST
        noticeBoard.postNotice(notice);
        noticeBST.insert(notice);
    }

    // Method to delete a notice by title
    private void deleteNotice() {
        System.out.print("Enter the title of the notice to delete: ");
        String title = scanner.nextLine();

        boolean isDeleted = noticeBST.delete(title);
        if (isDeleted) {
            System.out.println("✅ Notice Deleted Successfully.");
        } else {
            System.out.println("❌ Notice with title '" + title + "' not found.");
        }
    }

    // Method to show all notices (sorted)
    public void showAllNotices() {
        System.out.println("📢 All Notices (Sorted by Title):");
        noticeBoard.showAllNotices(); // Show notices from NoticeBoard
    }
}
