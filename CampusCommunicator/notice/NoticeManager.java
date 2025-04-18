import CampusCommunicator.notice.NoticeBoard;
import CampusCommunicator.models.Notice;
import java.util.Scanner;

public class NoticeManager {
    private NoticeBoard noticeBoard;
    private Scanner scanner;

    public NoticeManager() {
        this.noticeBoard = new NoticeBoard();
        this.scanner = new Scanner(System.in);
    }

    // Display menu to the user
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Notice Board Menu ---");
            System.out.println("1. Post Notice");
            System.out.println("2. Show All Notices");
            System.out.println("3. Exit");
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
        
        // Post the notice to the NoticeBoard
        noticeBoard.postNotice(notice);
    }
}
