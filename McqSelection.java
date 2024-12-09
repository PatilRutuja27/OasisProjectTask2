import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class McqSelection {
    public static void startExam(int userId) {
        String query = "SELECT * FROM questions";
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int questionId = rs.getInt("id");
                String question = rs.getString("question");
                String option1 = rs.getString("option1");
                String option2 = rs.getString("option2");
                String option3 = rs.getString("option3");
                String option4 = rs.getString("option4");

                System.out.println("Q" + questionId + ": " + question);
                System.out.println("1. " + option1);
                System.out.println("2. " + option2);
                System.out.println("3. " + option3);
                System.out.println("4. " + option4);
                System.out.print("Your Answer (1-4): ");
                int selectedOption = scanner.nextInt();

                saveAnswer(userId, questionId, selectedOption);
            }
            System.out.println("Exam completed successfully!");
        } catch (Exception e) {
            System.out.println("Error during the exam: " + e.getMessage());
        }
    }

    private static void saveAnswer(int userId, int questionId, int selectedOption) {
        String query = "INSERT INTO user_answers (user_id, question_id, selected_option) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, questionId);
            stmt.setInt(3, selectedOption);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error saving answer: " + e.getMessage());
        }
    }
}

