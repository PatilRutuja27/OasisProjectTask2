import java.sql.Connection;
import java.sql.PreparedStatement;

public class Logout {
    public static void closeSession(int userId) {
        String query = "UPDATE exam_sessions SET end_time = NOW() WHERE user_id = ? AND end_time IS NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("You have successfully logged out.");
            } else {
                System.out.println("No active session found.");
            }
        } catch (Exception e) {
            System.out.println("Error during logout: " + e.getMessage());
        }
    }
}

