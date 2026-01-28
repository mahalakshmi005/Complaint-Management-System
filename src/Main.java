package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ComplaintManager manager = new ComplaintManager();

        System.out.println("=== Complaint Management System ===");
        System.out.print("Login as (student/admin): ");
        String role = sc.nextLine();

        /* ================= STUDENT ================= */
        if (role.equalsIgnoreCase("student")) {

            int studentId = 101; // simulated student id

            while (true) {
                System.out.println("\n1. Add Complaint");
                System.out.println("2. View My Complaints");
                System.out.println("3. Exit");

                int ch = sc.nextInt();
                sc.nextLine();

                if (ch == 1) {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    manager.addComplaint(studentId, title, desc);
                }
                else if (ch == 2) {
                    manager.viewStudentComplaints(studentId);
                }
                else break;
            }
        }

        /* ================= ADMIN ================= */
        else if (role.equalsIgnoreCase("admin")) {

            while (true) {
                System.out.println("\n1. View All Complaints");
                System.out.println("2. Update Status");
                System.out.println("3. Delete Complaint");
                System.out.println("4. Exit");

                int ch = sc.nextInt();

                if (ch == 1)
                    manager.viewAllComplaints();

                else if (ch == 2) {
                    System.out.print("Complaint ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("1. Pending");
                    System.out.println("2. In Progress");
                    System.out.println("3. Resolved");

                    int s = sc.nextInt();
                    sc.nextLine();

                    String status = switch (s) {
                        case 2 -> "In Progress";
                        case 3 -> "Resolved";
                        default -> "Pending";
                    };

                    manager.updateStatus(id, status);
                }

                else if (ch == 3) {
                    System.out.print("Complaint ID: ");
                    manager.deleteComplaint(sc.nextInt());
                }
                else break;
            }
        }

        else {
            System.out.println("Invalid role!");
        }

        sc.close();
    }
}
