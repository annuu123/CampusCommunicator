package CampusCommunicator.models;

public class Notice {
    private String title;
    private String content;
    private String postedBy; // Author of the notice

    // Constructor
    public Notice(String title, String content, String postedBy) {
        this.title = title;
        this.content = content;
        this.postedBy = postedBy;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPostedBy() {
        return postedBy; // This method returns the author
    }

    public String getAuthor() {
        return postedBy; // This method can also return the author
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
               "Content: " + content + "\n" +
               "Posted by: " + postedBy;
    }
}
