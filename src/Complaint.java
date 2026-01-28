package src;

import java.time.LocalDate;

public class Complaint {

    private int id;
    private int studentId;
    private String title;
    private String description;
    private String status;
    private LocalDate createdDate;

    public Complaint(int studentId, String title, String description) {
        this.studentId = studentId;
        this.title = title;
        this.description = description;
        this.status = "Pending";
        this.createdDate = LocalDate.now();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

