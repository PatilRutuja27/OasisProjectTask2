public class OnlineExam {
    public static void main(String[] args) {
        int userId = 1; // Example user ID after login

        Runnable onTimeout = () -> System.out.println("Exam ended!");
        ExamTimer.startTimer(10, onTimeout); // Set 10 minutes for the exam

        McqSelection.startExam(userId);
        ExamTimer.stopTimer(); // Stop timer when the exam ends manually
    }
}

