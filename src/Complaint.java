package src;
public class Complaint {
    private final int id;
    private final String title;
    private final String description;
    private String status;

    public Complaint(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = "Open";
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + description + " | " + status;
    }
}
