package CampusCommunicator.notice;

import java.util.HashMap;
import java.util.Map;

public class NoticeManager {
    private Map<String, String> notices;
    private Map<String, String> homework;

    public NoticeManager() {
        this.notices = new HashMap<>();
        this.homework = new HashMap<>();
    }

    // Display available menu options for the NoticeManager
    public void displayMenu() {
        System.out.println("\nNotice Manager Menu:");
        System.out.println("1. Display Notices");
        System.out.println("2. Add a Notice");
        System.out.println("3. Add Homework");
        System.out.println("4. Display Homework for a Student");
        System.out.println("5. Exit");
        System.out.print("Please choose an option: ");
    }

    // Display notices
    public void displayNotices() {
        if (notices.isEmpty()) {
            System.out.println("No notices available.");
        } else {
            notices.forEach((subject, notice) -> {
                System.out.println("Subject: " + subject + " | Notice: " + notice);
            });
        }
    }

    // Add a new notice
    public void addNotice(String subject, String notice) {
        notices.put(subject, notice);
        System.out.println("Notice added for subject: " + subject);
    }

    // Add homework
    public void addHomework(String subject, String homeworkContent) {
        homework.put(subject, homeworkContent);
        System.out.println("Homework added for subject: " + subject);
    }

    // Display homework for a student
    public void displayHomeworkForStudent(String studentSubject) {
        String homeworkForStudent = homework.get(studentSubject);
        
        if (homeworkForStudent == null) {
            System.out.println("No homework available for " + studentSubject);
        } else {
            System.out.println("Homework for " + studentSubject + ": " + homeworkForStudent);
        }
    }
}
