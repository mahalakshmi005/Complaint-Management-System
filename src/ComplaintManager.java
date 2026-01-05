package src;

import java.sql.*;

public class ComplaintManager {

    private static final String URL =
            "jdbc:mysql://localhost:3306/complaint_db";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection conn;

    public ComplaintManager() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            System.out.println(e.getMessage());
        }
    }

    private boolean isConnected() {
        return conn != null;
    }

    // 1️⃣ Add Complaint
    public void addComplaint(String title, String description) {
        if (!isConnected()) return;

        String sql = "INSERT INTO complaints (title, description) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.executeUpdate();
            System.out.println("Complaint added successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 2️⃣ View Complaints
    public void viewComplaints() {
        if (!isConnected()) return;

        String sql = "SELECT * FROM complaints";

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("description") + " | " +
                        rs.getString("status")
                );
            }

            if (!hasData) {
                System.out.println("No complaints found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3️⃣ Update Status ✅ FIXED
    public void updateStatus(int id, String status) {
        if (!isConnected()) return;

        String sql = "UPDATE complaints SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Status updated successfully!");
            } else {
                System.out.println("Complaint ID not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 4️⃣ Delete Complaint ✅ FIXED
    public void deleteComplaint(int deleteId) {
        if (!isConnected()) return;

        String sql = "DELETE FROM complaints WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deleteId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Complaint deleted successfully!");
            } else {
                System.out.println("Complaint ID not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

