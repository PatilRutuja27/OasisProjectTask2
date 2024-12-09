import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class UpdatePassword {
    public static void changePassword(int userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        String query = "UPDATE users SET password = ? WHERE id = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            stmt.setString(3, currentPassword);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password updated successfully!");
            } else {
                System.out.println("Incorrect current password.");
            }
        } catch (Exception e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }
}

