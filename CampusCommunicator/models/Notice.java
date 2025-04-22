package CampusCommunicator.models;

public class Notice {
    private String title;
    private String content;
    private String author;  // Add an 'author' field

    // Constructor to initialize the notice
    public Notice(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Getter for author (this is the method you need)
    public String getAuthor() {
        return author;
    }

    // Override toString() for display
    @Override
    public String toString() {
        return "Title: " + title + "\nContent: " + content + "\nPosted By: " + author;
    }
}
