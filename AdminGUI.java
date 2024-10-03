import java.util.Scanner;

public class AdminGUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // DatabaseQueries queries = new DatabaseQueries();
        boolean exit = false;

        while (!exit) {
            // Display the menu
            System.out.println("===== Admin Interface =====");
            System.out.println("1. View all users with a subscription");
            System.out.println("2. View users who are not subscribed");
            System.out.println("3. View users who joined before 2024");
            System.out.println("4. View number of subscribers");
            System.out.println("5. View most liked songs (Top 5)");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            // Get user input
            int option = scanner.nextInt();

            // Process the selected option
            switch (option) {
                case 1:
                    System.out.println(
                        "Executing: View all users with a subscription"
                    );
                    // queries.viewUsersWithSubscription();
                    break;
                case 2:
                    System.out.println(
                        "Executing: View users who are not subscribed"
                    );
                    // queries.viewUsersWithoutSubscription();
                    break;
                case 3:
                    System.out.println(
                        "Executing: View users who joined before 2024"
                    );
                    // queries.viewUsersJoinedBefore2024();
                    break;
                case 4:
                    System.out.println("Executing: View number of subscribers");
                    // queries.viewNumberOfSubscribers();
                    break;
                case 5:
                    System.out.println(
                        "Executing: View most liked songs (Top 5)"
                    );
                    // queries.viewMostLikedSongs();
                    break;
                case 6:
                    System.out.println("Exiting the application...");
                    exit = true;
                    break;
                default:
                    System.out.println(
                        "Invalid option. Please choose between 1 and 6."
                    );
            }

            // New line for better readability
            System.out.println();
        }

        // Close the scanner before exiting
        scanner.close();
    }
}
