// NoticeBoard.java
package CampusCommunicator.notice;

import CampusCommunicator.models.Notice;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class NoticeBoard {
    private Queue<Notice> notices;

    public NoticeBoard() {
        notices = new LinkedList<>();
    }

    public void postNotice(Notice notice) {
        notices.add(notice);
        System.out.println("Notice posted: " + notice.getTitle());
    }

    public void showAllNotices() {
        if (notices.isEmpty()) {
            System.out.println("No notices available.");
            return;
        }

        System.out.println("\n--- All Notices ---");
        for (Notice n : notices) {
            System.out.println(n);
        }
    }

    public void searchNotices(String keyword) {
        System.out.println("Search Results for: " + keyword);
        boolean found = false;
        for (Notice notice : notices) {
            if (notice.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                notice.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(notice);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No notices found matching the keyword.");
        }
    }

    public void filterNoticesByAuthor(String author) {
        System.out.println("\n📌 Notices by " + author + ":");
        boolean found = false;
        for (Notice notice : notices) {
            if (notice.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(notice);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No notices found by this author.");
        }
    }
    public List<Notice> searchByKeyword(String keyword) {
    return notices.stream()
        .filter(n -> n.getTitle().toLowerCase().contains(keyword.toLowerCase()) || 
                     n.getContent().toLowerCase().contains(keyword.toLowerCase()))
        .collect(Collectors.toList());
}

public List<Notice> searchByAuthor(String author) {
    return notices.stream()
        .filter(n -> n.getPostedBy().equalsIgnoreCase(author))
        .collect(Collectors.toList());
}

}
