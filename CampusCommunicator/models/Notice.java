package CampusCommunicator.models;

import java.time.LocalDateTime;

public class Notice {
    private String title;
    private String content;
    private String postedBy;
    private LocalDateTime timestamp;

    public Notice(String title, String content, String postedBy) {
        this.title = title;
        this.content = content;
        this.postedBy = postedBy;
        this.timestamp = LocalDateTime.now();
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getPostedBy() { return postedBy; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + title + " - " + content + " (By: " + postedBy + ")";
    }
}
