package src;

import java.sql.*;

public class ComplaintManager {

    private static final String URL = "jdbc:mysql://localhost:3306/complaint_db";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection conn;

    public ComplaintManager() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ================= STUDENT ================= */

    // Student → Add complaint
    public void addComplaint(int studentId, String title, String description) {

        String sql = """
            INSERT INTO complaints
            (title, description, status, student_id, created_date)
            VALUES (?, ?, 'Pending', ?, CURDATE())
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, studentId);
            ps.executeUpdate();
            System.out.println("Complaint submitted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Student → View own complaints (know if closed)
    public void viewStudentComplaints(int studentId) {

        String sql = "SELECT * FROM complaints WHERE student_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nID | Title | Date | Status");
            System.out.println("----------------------------");

            while (rs.next()) {
                String status = rs.getString("status");

                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getDate("created_date") + " | " +
                        status
                );

                if (status.equalsIgnoreCase("Resolved")) {
                    System.out.println("➡️ Complaint CLOSED by admin");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= ADMIN ================= */

    // Admin → View all complaints
    public void viewAllComplaints() {

        String sql = "SELECT * FROM complaints";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nID | Student | Title | Status");
            System.out.println("--------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getInt("student_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Admin → Update status
    public void updateStatus(int id, String status) {

        String sql = "UPDATE complaints SET status=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Status updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Admin → Delete complaint
    public void deleteComplaint(int id) {

        String sql = "DELETE FROM complaints WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Complaint deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
