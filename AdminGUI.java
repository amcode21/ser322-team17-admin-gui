import java.util.Scanner;

public class AdminGUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseQueries queries = new DatabaseQueries(args[0],args[3],args[1],args[2]);
        boolean exit = false;

        while (!exit) {
            // Display the menu
            System.out.println("===== Admin Interface =====");
            System.out.println("1. Insert");
            System.out.println("2. Select");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            // Get user input
            int option = scanner.nextInt();

            // Process the selected option
            switch (option) {
                case 1:
                    insert(scanner, queries);
                    break;
                case 2:
                    select(scanner,queries);
                    break;
                case 3:
                    update(scanner,queries);
                    break;
                case 4:
                    delete(scanner, queries);
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    exit = true;
                    break;
                default:
                    System.out.println(
                        "Invalid option. Please choose between 1 and 5."
                    );
            }

            // New line for better readability
            System.out.println();
        }

        // Close the scanner before exiting
        scanner.close();
    }
    private static void insert(Scanner scanner, DatabaseQueries queries) {
        System.out.println("Please determine what you would like to insert");
        System.out.println("1. User");
        System.out.println("2. Subscription");
        System.out.println("3. Artist");
        System.out.println("4. Album");
        System.out.println("5. Song");
        System.out.println("6. Liked Song");
        System.out.println("7. Playlist");
        System.out.println("8. Playlist Song");
        int insert=scanner.nextInt();
        switch(insert) {
            case 1:
                System.out.println("please input the user id, username, year of joining, and the birthday");
                System.out.println("The birthday is in the format 'yyyy-mm-dd'");
                int u_userID=scanner.nextInt();
                String userName=scanner.next();
                int yearOfJoining=scanner.nextInt();
                String birthday=scanner.next();
                queries.insertUser(u_userID,userName,yearOfJoining,birthday);
                break;
            case 2:
                System.out.println("please input the subscription id, user id, billing details, and the subscription date");
                System.out.println("The subscription date is in the format 'yyyy-mm-dd'");
                int subscriptionID=scanner.nextInt();
                int s_userID=scanner.nextInt();
                String billingDetails=scanner.next();
                String subscriptionDate=scanner.next();
                queries.insertSubscription(subscriptionID,s_userID,billingDetails,subscriptionDate);
                break;
            case 3:
                System.out.println("please input the artist id, name, description, and their verified status");
                System.out.println("The verified status is either true or false");
                int ar_artistID=scanner.nextInt();
                String ar_name=scanner.next();
                String ar_description=scanner.next();
                boolean verified=scanner.nextBoolean();
                queries.insertArtist(ar_artistID,ar_name,ar_description,verified);
                break;
            case 4:
                System.out.println("please input the album id, name, release year, image URL, and the artist ID");
                System.out.println("The birthday is in the format 'yyyy-mm-dd'");
                int al_albumID=scanner.nextInt();
                String al_name=scanner.next();
                int al_releaseYear=scanner.nextInt();
                String imgURL=scanner.next();
                int al_artistID=scanner.nextInt();
                queries.insertAlbum(al_albumID,al_name,al_releaseYear,imgURL, al_artistID);
                break;
                //TODO: fill out for rest of insertions
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                break;
        }
    }
    private static void select(Scanner scanner, DatabaseQueries queries) {
        System.out.println("Please determine what selection script you would like to do");
        System.out.println("1. Select users with a subscription");
        System.out.println("2. Select unsubscribed users");
        System.out.println("3. Select users who joined before a specific year");
        System.out.println("4. Select the subscription date and year of joining for subscribed users");
        System.out.println("5. Select users with a birthday on a specific day");
        System.out.println("6. Get the number of subscribers");
        System.out.println("7. Get the number of subscribers who liked a song after their subscription started");
        System.out.println("8. Select the songs that a user id liked");
        System.out.println("9. Select the songs in a particular album id");
        System.out.println("10. Select the songs that were liked before a specific date");
        System.out.println("11. Select the songs in a specific playlist ID");
        System.out.println("12. Select the songs that an artist id created");
        System.out.println("13. Select the songs that users who started before a specific year liked");
        System.out.println("14. Select all verified artists");
        System.out.println("15. Select albums released in a specific year");
        System.out.println("16. Select the albums made by a specific artist id");
        System.out.println("17. Select the albums released after a specific year");
        System.out.println("18. Select the user that liked a song in a specific playlist id");
        System.out.println("19. Select the artists that have songs in a specific playlist id");
        System.out.println("20. Select the most liked songs");
        int select=scanner.nextInt();
        switch(select) {
            case 1:
                queries.SelectUsersWithSubscription();
                break;
            case 2:
                queries.SelectUsersWithNoSubscription();
                break;
            case 3:
                System.out.println("select which year you want");
                int y3=scanner.nextInt();
                queries.SelectUsersJoinedBeforeYear(y3);
                break;
            case 4:
                queries.compareSubscriptionDateJoinDate();
                break;
            case 5:
                System.out.println("select which day you want, using the format 'yyyy-mm-dd'");
                String d5=scanner.next();
                queries.SelectUsersWithBirthday(d5);
                break;
            case 6:
                queries.getSubscriberCount();
                break;
            case 7:
                queries.getLikedSongPostSubscription();
                break;
            case 8:
                System.out.println("select which user ID you want");
                int uID8=scanner.nextInt();
                queries.SelectSongsLikedByUser(uID8);
                break;
            case 9:
                System.out.println("select which album ID you want");
                int aID9=scanner.nextInt();
                queries.SelectSongsByAlbum(aID9);
                break;
            case 10:
                System.out.println("select which date you want, using the format 'yyyy-mm-dd'");
                String d10=scanner.next();
                queries.SelectSongsLikedBeforeDate(d10);
                break;
                //TODO: fill out for rest of selection queries
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                break;
            case 18:
                break;
            case 19:
                break;
            case 20:
                break;
            default:
                System.out.println("invalid input. Returning to main menu");
                break;
        }
    }
    private static void update(Scanner scanner, DatabaseQueries queries) {
        System.out.println("Please determine what you would like to delete");
        System.out.println("1. User");
        System.out.println("2. Subscription");
        System.out.println("3. Artist");
        System.out.println("4. Album");
        System.out.println("5. Song");
        System.out.println("6. Liked Song");
        System.out.println("7. Playlist");
        System.out.println("8. Playlist Song");
        int update=scanner.nextInt();
        switch(update) {
            case 1:
                System.out.println("please input the user id");
                int u_userID=scanner.nextInt();
                System.out.println("please select what value to update");
                System.out.println("1. Username");
                System.out.println("2. year of joining");
                System.out.println("3. birthday");
                int user=scanner.nextInt();
                switch(user) {
                    case 1:
                        System.out.println("please input the new username");
                        String username=scanner.next();
                        queries.updateUsername(username,u_userID);
                        break;
                    case 2:
                        System.out.println("please input the new year of joining");
                        int yearOfJoining=scanner.nextInt();
                        queries.updateYearofJoining(yearOfJoining,u_userID);
                        break;
                    case 3:
                        System.out.println("please input the new birthday, using the format 'yyyy-mm-dd'");
                        String birthday=scanner.next();
                        queries.updateUserBirthday(birthday,u_userID);
                        break;
                    default:
                        System.out.println("Invalid input. Returning to the main menu");
                        break;
                }
                break;
            case 2:
                System.out.println("please input the subscription id");
                int subscriptionID=scanner.nextInt();
                System.out.println("please select what value to update");
                System.out.println("1. Billing details");
                System.out.println("2. subscription date");
                System.out.println("3. birthday");
                int subscription=scanner.nextInt();
                switch(subscription) {
                    case 1:
                        System.out.println("please input the new billing details");
                        String billingDetails=scanner.next();
                        queries.updateBillingDetails(billingDetails,subscriptionID);
                        break;
                    case 2:
                        System.out.println("please input the new subscription date, using the format 'yyyy-mm-dd'");
                        String subscriptionDate=scanner.next();
                        queries.updateSubscriptionDate(subscriptionDate,subscriptionID);
                        break;
                    default:
                        System.out.println("Invalid input. Returning to the main menu");
                        break;
                }
                break;
            case 3:
                System.out.println("please input the artist id");
                int ar_artistID=scanner.nextInt();
                System.out.println("please select what value to update");
                System.out.println("1. name");
                System.out.println("2. description");
                System.out.println("3. verified status");
                int artist=scanner.nextInt();
                switch(artist) {
                    case 1:
                        System.out.println("please input the new name");
                        String ar_name=scanner.next();
                        queries.updateArtistName(ar_name,ar_artistID);
                        break;
                    case 2:
                        System.out.println("please input the new description");
                        String ar_description=scanner.next();
                        queries.updateArtistDescription(ar_description,ar_artistID);
                        break;
                    case 3:
                        System.out.println("please input the new verified status as either true or false");
                        String verified=scanner.next();
                        queries.updateArtistVerificationStatus(Boolean.parseBoolean(verified),ar_artistID);
                        break;
                    default:
                        System.out.println("Invalid input. Returning to the main menu");
                        break;
                }
                break;
            case 4:
                System.out.println("please input the album id");
                int al_albumID=scanner.nextInt();
                System.out.println("please select what value to update");
                System.out.println("1. name");
                System.out.println("2. release year");
                System.out.println("3. image URL");
                System.out.println("4. artist id");
                int album=scanner.nextInt();
                switch(album) {
                    case 1:
                        System.out.println("please input the new name");
                        String al_name=scanner.next();
                        queries.updateAlbumName(al_name,al_albumID);
                        break;
                    case 2:
                        System.out.println("please input the new release year");
                        int releaseYear=scanner.nextInt();
                        queries.updateAlbumYear(releaseYear,al_albumID);
                        break;
                    case 3:
                        System.out.println("please input the new image URL");
                        String imgURL=scanner.next();
                        queries.updateAlbumImage(imgURL,al_albumID);
                        break;
                    case 4:
                        System.out.println("please input the new artist id");
                        int al_artistID=scanner.nextInt();
                        queries.updateAlbumArtist(al_artistID,al_albumID);
                    default:
                        System.out.println("Invalid input. Returning to the main menu");
                        break;
                }
                break;
            //TODO: fill out for rest of updates
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                break;
        }
    }

    private static void delete(Scanner scanner, DatabaseQueries queries) {
        System.out.println("Please determine what you would like to delete");
        System.out.println("1. User");
        System.out.println("2. Subscription");
        System.out.println("3. Artist");
        System.out.println("4. Album");
        System.out.println("5. Song");
        System.out.println("6. Liked Song");
        System.out.println("7. Playlist");
        System.out.println("8. Playlist Song");
        int insert=scanner.nextInt();
        switch(insert) {
            case 1:
                System.out.println("please input the user id");
                int u_userID=scanner.nextInt();
                queries.deleteUser(u_userID);
                break;
            case 2:
                System.out.println("please input the subscription id");
                int subscriptionID=scanner.nextInt();
                queries.deleteSubscription(subscriptionID);
                break;
            case 3:
                System.out.println("please input the artist id");
                int ar_artistID=scanner.nextInt();
                queries.deleteArtist(ar_artistID);
                break;
            case 4:
                System.out.println("please input the album id");
                int al_albumID=scanner.nextInt();
                queries.deleteAlbum(al_albumID);
                break;
            //TODO: fill out for rest of deletions
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                break;
        }
    }
}
