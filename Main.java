import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Examination System");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = Login.authenticate(username, password);
        if (user == null) {
            System.out.println("Invalid login. Exiting...");
            return;
        }

        System.out.println("Welcome, " + user.getFullName() + "!");
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Start Exam");
            System.out.println("2. Update Profile");
            System.out.println("3. Change Password");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> McqSelection.startExam(user.getId());
                case 2 -> UpdateProfile.updateUser(user.getId());
                case 3 -> UpdatePassword.changePassword(user.getId());
                case 4 -> Logout.closeSession(user.getId());
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        System.out.println("Thank you for using the Online Examination System!");
    }
}

