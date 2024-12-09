import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateProfile {
    public static void updateUser(int userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new full name: ");
        String newFullName = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        String query = "UPDATE users SET full_name = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newFullName);
            stmt.setString(2, newEmail);
            stmt.setInt(3, userId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Profile updated successfully!");
            } else {
                System.out.println("Failed to update profile.");
            }
        } catch (Exception e) {
            System.out.println("Error updating profile: " + e.getMessage());
        }
    }
}
