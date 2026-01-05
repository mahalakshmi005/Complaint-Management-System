package src;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ComplaintManager manager = new ComplaintManager();

        while (true) {

            System.out.println("\n1. Add Complaint");
            System.out.println("2. View Complaints");
            System.out.println("3. Update Status");
            System.out.println("4. Delete Complaint");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1 -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    manager.addComplaint(title, desc);
                }

                case 2 -> manager.viewComplaints();

                case 3 -> {
                    System.out.print("Complaint ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Status (Open/In Progress/Closed): ");
                    String status = sc.nextLine();
                    manager.updateStatus(id, status);
                }

                case 4 -> {
                    System.out.print("Complaint ID: ");
                    int deleteId = sc.nextInt();
                    manager.deleteComplaint(deleteId);
                }

                case 5 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
