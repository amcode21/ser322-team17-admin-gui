import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminGUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseQueries queries = new DatabaseQueries(
            args[0],
            args[3],
            args[1],
            args[2]
        );
        boolean exit = false;

        while (!exit) {
            System.out.println("===== Admin Interface =====");
            System.out.println("1. Insert");
            System.out.println("2. Select");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int option = getIntInput(scanner, 1, 5);

            switch (option) {
                case 1:
                    insert(scanner, queries);
                    break;
                case 2:
                    select(scanner, queries);
                    break;
                case 3:
                    update(scanner, queries);
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

            System.out.println(); // New line for better readability
        }

        scanner.close(); // Close the scanner before exiting
    }

    private static void insert(Scanner scanner, DatabaseQueries queries) {
        System.out.println("Please select what you would like to insert:");
        System.out.println("1. User");
        System.out.println("2. Subscription");
        System.out.println("3. Artist");
        System.out.println("4. Album");
        System.out.println("5. Song");
        System.out.println("6. Liked Song");
        System.out.println("7. Playlist");
        System.out.println("8. Playlist Song");

        int insertOption = getIntInput(scanner, 1, 8);

        switch (insertOption) {
            case 1:
                System.out.println(
                    "Enter user ID, username, year of joining, and birthday (yyyy-mm-dd):"
                );
                int userId = getIntInput(scanner, 1, Integer.MAX_VALUE);
                String userName = getSanitizedStringInput(scanner);
                int yearOfJoining = getIntInput(scanner, 1900, 2100);
                String birthday = getValidatedDateInput(scanner);
                queries.insertUser(userId, userName, yearOfJoining, birthday);
                break;
            case 2:
                System.out.println(
                    "Enter subscription ID, user ID, billing details, and subscription date (yyyy-mm-dd):"
                );
                int subscriptionID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                int userID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                String billingDetails = getSanitizedStringInput(scanner);
                String subscriptionDate = getValidatedDateInput(scanner);
                queries.insertSubscription(
                    subscriptionID,
                    userID,
                    billingDetails,
                    subscriptionDate
                );
                break;
            case 3:
                System.out.println(
                    "Enter artist ID, name, description, and verified status:"
                );
                int artistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                String artistName = getSanitizedStringInput(scanner);
                String artistDescription = getSanitizedStringInput(scanner);
                boolean verified = getBooleanInput(scanner);
                queries.insertArtist(
                    artistID,
                    artistName,
                    artistDescription,
                    verified
                );
                break;
            case 4:
                System.out.println(
                    "Enter album ID, name, release year, image URL, and artist ID:"
                );
                int albumID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                String albumName = getSanitizedStringInput(scanner);
                int releaseYear = getIntInput(scanner, 1900, 2100);
                String imageURL = getSanitizedStringInput(scanner);
                int artistID2 = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.insertAlbum(
                    albumID,
                    albumName,
                    releaseYear,
                    imageURL,
                    artistID2
                );
                break;
            case 5:
                System.out.println(
                    "Enter song ID, artist ID, album ID, song name, release year, category, producer, and credits:"
                );
                int songID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                int songArtistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                int songAlbumID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                String songName = getSanitizedStringInput(scanner);
                int songReleaseYear = getIntInput(scanner, 1900, 2100);
                String category = getSanitizedStringInput(scanner);
                String producer = getSanitizedStringInput(scanner);
                String credits = getSanitizedStringInput(scanner);
                queries.insertSong(
                    songID,
                    songArtistID,
                    songAlbumID,
                    songName,
                    songReleaseYear,
                    category,
                    producer,
                    credits
                );
                break;
            case 6:
                System.out.println(
                    "Enter liked song ID, user ID, song ID, and liked date (yyyy-mm-dd):"
                );
                int likedID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                int likedUserID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                int likedSongID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                String likedDate = getValidatedDateInput(scanner);
                queries.insertLikedSong(
                    likedID,
                    likedUserID,
                    likedSongID,
                    likedDate
                );
                break;
            case 7:
                System.out.println(
                    "Enter playlist ID, creator ID, playlist name, created date (yyyy-mm-dd), published status, and description:"
                );
                int playlistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                int creatorID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                String playlistName = getSanitizedStringInput(scanner);
                String createdDate = getValidatedDateInput(scanner);
                boolean published = getBooleanInput(scanner);
                String description = getSanitizedStringInput(scanner);
                queries.insertPlaylist(
                    playlistID,
                    creatorID,
                    playlistName,
                    createdDate,
                    published,
                    description
                );
                break;
            case 8:
                System.out.println("Enter playlist ID and song ID:");
                int plID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                int plSongID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.insertPlaylistSong(plID, plSongID);
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                break;
        }
    }

    private static void select(Scanner scanner, DatabaseQueries queries) {
        System.out.println("Please select a query option:");
        System.out.println("1. Select users with a subscription");
        System.out.println("2. Select unsubscribed users");
        System.out.println("3. Select users who joined before a specific year");
        System.out.println(
            "4. Select subscription date and year of joining for subscribed users"
        );
        System.out.println(
            "5. Select users with a specific birthday (yyyy-mm-dd)"
        );
        System.out.println("6. Get the number of subscribers");
        System.out.println(
            "7. Get the number of subscribers who liked a song after subscription"
        );
        System.out.println("8. Get the songs that a specific user ID liked");
        System.out.println("9. Get the songs that are in a specific album ID");
        System.out.println(
            "10. Get the songs that were liked before a specific date"
        );
        System.out.println("11. Get the songs in a specific playlist ID");
        System.out.println("12. Get playlists created by a specific user ID");
        System.out.println(
            "13. Get the songs liked by users who joined before a specific year"
        );
        System.out.println("14. Get all verified artists");
        System.out.println("15. Get albums released in a specific year");
        System.out.println("16. Get albums by a specific artist ID");
        System.out.println(
            "17. Get artists with albums released after a specific year"
        );
        System.out.println(
            "18. Get users who liked a song in a specific playlist ID"
        );
        System.out.println(
            "19. Get artists with songs in a specific playlist ID"
        );
        System.out.println("20. Get the top liked songs");

        int selectOption = getIntInput(scanner, 1, 20);

        switch (selectOption) {
            case 1:
                queries.selectUsersWithSubscription();
                break;
            case 2:
                queries.selectUsersWithoutSubscription();
                break;
            case 3:
                System.out.println("Enter the year:");
                int year = getIntInput(scanner, 1900, 2100);
                queries.selectUsersJoinedBeforeYear(year);
                break;
            case 4:
                queries.compareSubscriptionDateJoinDate();
                break;
            case 5:
                System.out.println("Enter the birthday (yyyy-mm-dd):");
                String birthday = getValidatedDateInput(scanner);
                queries.selectUsersWithBirthday(birthday);
                break;
            case 6:
                queries.getSubscriberCount();
                break;
            case 7:
                queries.getLikedSongPostSubscription();
                break;
            case 8:
                System.out.println("Enter the user ID");
                int userID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.selectSongsLikedByUser(userID);
                break;
            case 9:
                System.out.println("Enter the album ID");
                int albumID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.selectSongsByAlbum(albumID);
                break;
            case 10:
                System.out.println("Enter the date");
                String date = getValidatedDateInput(scanner);
                queries.selectSongsLikedBeforeDate(date);
                break;
            case 11:
                System.out.println("Enter the playlist ID:");
                int playlistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.selectSongsInPlaylist(playlistID);
                break;
            case 12:
                System.out.println("Enter the user ID:");
                int creatorID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.selectPlaylistsByUserID(creatorID);
                break;
            case 13:
                System.out.println("Enter the year:");
                int joinedYear = getIntInput(scanner, 1900, 2100);
                queries.selectSongsLikedByUsersBeforeYear(joinedYear);
                break;
            case 14:
                queries.selectVerifiedArtists();
                break;
            case 15:
                System.out.println("Enter the release year:");
                int releaseYear = getIntInput(scanner, 1900, 2100);
                queries.selectAlbumsFromYear(releaseYear);
                break;
            case 16:
                System.out.println("Enter the artist ID:");
                int artistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.selectAlbumsByArtistID(artistID);
                break;
            case 17:
                System.out.println("Enter the year:");
                int yearAfter = getIntInput(scanner, 1900, 2100);
                queries.selectArtistsWithAlbumsAfterYear(yearAfter);
                break;
            case 18:
                System.out.println("Enter the playlist ID:");
                int plID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.selectUsersWhoLikedSongInPlaylist(plID);
                break;
            case 19:
                System.out.println("Enter the playlist ID:");
                int plID2 = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.selectArtistsWithSongsInPlaylist(plID2);
                break;
            case 20:
                System.out.println("Enter the limit for top liked songs:");
                int limit = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.selectTopLikedSongs(limit);
                break;
            default:
                System.out.println("Invalid input. Returning to main menu.");
                break;
        }
    }

    private static void update(Scanner scanner, DatabaseQueries queries) {
        System.out.println("Please select what you would like to update:");
        System.out.println("1. User");
        System.out.println("2. Subscription");
        System.out.println("3. Artist");
        System.out.println("4. Album");
        System.out.println("5. Song");
        System.out.println("6. Playlist");

        int updateOption = getIntInput(scanner, 1, 6);

        switch (updateOption) {
            case 1:
                System.out.println("Enter user ID:");
                int userId = getIntInput(scanner, 1, Integer.MAX_VALUE);
                System.out.println(
                    "Select value to update: 1. Username, 2. Year of joining, 3. Birthday"
                );
                int userField = getIntInput(scanner, 1, 3);
                switch (userField) {
                    case 1:
                        System.out.println("Enter new username:");
                        String username = getSanitizedStringInput(scanner);
                        queries.updateUsername(username, userId);
                        break;
                    case 2:
                        System.out.println("Enter new year of joining:");
                        int yearOfJoining = getIntInput(scanner, 1900, 2100);
                        queries.updateYearofJoining(yearOfJoining, userId);
                        break;
                    case 3:
                        System.out.println("Enter new birthday (yyyy-mm-dd):");
                        String birthday = getValidatedDateInput(scanner);
                        queries.updateUserBirthday(birthday, userId);
                        break;
                    default:
                        System.out.println(
                            "Invalid input. Returning to the main menu."
                        );
                        break;
                }
                break;
            case 2:
                System.out.println("Enter subscription ID:");
                int subscriptionID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                System.out.println(
                    "Select value to update: 1. Billing Details, 2. Subscription Date"
                );
                int subscriptionField = getIntInput(scanner, 1, 2);
                switch (subscriptionField) {
                    case 1:
                        System.out.println("Enter new billing details:");
                        String billingDetails = getSanitizedStringInput(
                            scanner
                        );
                        queries.updateBillingDetails(
                            billingDetails,
                            subscriptionID
                        );
                        break;
                    case 2:
                        System.out.println(
                            "Enter new subscription date (yyyy-mm-dd):"
                        );
                        String subscriptionDate = getValidatedDateInput(
                            scanner
                        );
                        queries.updateSubscriptionDate(
                            subscriptionDate,
                            subscriptionID
                        );
                        break;
                    default:
                        System.out.println(
                            "Invalid input. Returning to the main menu."
                        );
                        break;
                }
                break;
            case 3:
                System.out.println("Enter artist ID:");
                int artistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                System.out.println(
                    "Select value to update: 1. Name, 2. Description, 3. Verified Status"
                );
                int artistField = getIntInput(scanner, 1, 3);
                switch (artistField) {
                    case 1:
                        System.out.println("Enter new name:");
                        String name = getSanitizedStringInput(scanner);
                        queries.updateArtistName(name, artistID);
                        break;
                    case 2:
                        System.out.println("Enter new description:");
                        String description = getSanitizedStringInput(scanner);
                        queries.updateArtistDescription(description, artistID);
                        break;
                    case 3:
                        System.out.println("Enter new verified status:");
                        boolean verifiedStatus = getBooleanInput(scanner);
                        queries.updateArtistVerificationStatus(
                            verifiedStatus,
                            artistID
                        );
                        break;
                    default:
                        System.out.println(
                            "Invalid input. Returning to the main menu."
                        );
                        break;
                }
                break;
            case 4:
                System.out.println("Enter album ID:");
                int albumID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                System.out.println(
                    "Select value to update: 1. Name, 2. Release Year, 3. Image URL, 4. Artist ID"
                );
                int albumField = getIntInput(scanner, 1, 4);
                switch (albumField) {
                    case 1:
                        System.out.println("Enter new name:");
                        String name = getSanitizedStringInput(scanner);
                        queries.updateAlbumName(name, albumID);
                        break;
                    case 2:
                        System.out.println("Enter new release year:");
                        int releaseYear = getIntInput(scanner, 1900, 2100);
                        queries.updateAlbumYear(releaseYear, albumID);
                        break;
                    case 3:
                        System.out.println("Enter new image URL:");
                        String imgURL = getSanitizedStringInput(scanner);
                        queries.updateAlbumImage(imgURL, albumID);
                        break;
                    case 4:
                        System.out.println("Enter new artist ID:");
                        int artistIDForAlbum = getIntInput(
                            scanner,
                            1,
                            Integer.MAX_VALUE
                        );
                        queries.updateAlbumArtist(artistIDForAlbum, albumID);
                        break;
                    default:
                        System.out.println(
                            "Invalid input. Returning to the main menu."
                        );
                        break;
                }
                break;
            case 5:
                System.out.println("Enter song ID:");
                int songID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                System.out.println(
                    "Select value to update: 1. Name, 2. Category, 3. Producer, 4. Credits"
                );
                int songField = getIntInput(scanner, 1, 4);
                switch (songField) {
                    case 1:
                        System.out.println("Enter new name:");
                        String songName = getSanitizedStringInput(scanner);
                        queries.updateSongName(songID, songName);
                        break;
                    case 2:
                        System.out.println("Enter new category:");
                        String category = getSanitizedStringInput(scanner);
                        queries.updateSongCategory(songID, category);
                        break;
                    case 3:
                        System.out.println("Enter new producer:");
                        String producer = getSanitizedStringInput(scanner);
                        queries.updateSongProducer(songID, producer);
                        break;
                    case 4:
                        System.out.println("Enter new credits:");
                        String credits = getSanitizedStringInput(scanner);
                        queries.updateSongCredits(songID, credits);
                        break;
                    default:
                        System.out.println(
                            "Invalid input. Returning to the main menu."
                        );
                        break;
                }
                break;
            case 6:
                System.out.println("Enter playlist ID:");
                int playlistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                System.out.println(
                    "Select value to update: 1. Name, 2. Description, 3. Published Status"
                );
                int playlistField = getIntInput(scanner, 1, 3);
                switch (playlistField) {
                    case 1:
                        System.out.println("Enter new name:");
                        String playlistName = getSanitizedStringInput(scanner);
                        queries.updatePlaylistName(playlistID, playlistName);
                        break;
                    case 2:
                        System.out.println("Enter new description:");
                        String description = getSanitizedStringInput(scanner);
                        queries.updatePlaylistDescription(
                            playlistID,
                            description
                        );
                        break;
                    case 3:
                        System.out.println("Enter new published status:");
                        boolean published = getBooleanInput(scanner);
                        queries.updatePlaylistPublishedStatus(
                            playlistID,
                            published
                        );
                        break;
                    default:
                        System.out.println(
                            "Invalid input. Returning to the main menu."
                        );
                        break;
                }
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                break;
        }
    }

    private static void delete(Scanner scanner, DatabaseQueries queries) {
        System.out.println("Please select what you would like to delete:");
        System.out.println("1. User");
        System.out.println("2. Subscription");
        System.out.println("3. Artist");
        System.out.println("4. Album");
        System.out.println("5. Song");
        System.out.println("6. Playlist");
        System.out.println("7. Liked Song");
        System.out.println("8. Playlist Song");

        int deleteOption = getIntInput(scanner, 1, 8);

        switch (deleteOption) {
            case 1:
                System.out.println("Enter user ID:");
                int userId = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.deleteUser(userId);
                break;
            case 2:
                System.out.println("Enter subscription ID:");
                int subscriptionID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.deleteSubscription(subscriptionID);
                break;
            case 3:
                System.out.println("Enter artist ID:");
                int artistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.deleteArtist(artistID);
                break;
            case 4:
                System.out.println("Enter album ID:");
                int albumID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.deleteAlbum(albumID);
                break;
            case 5:
                System.out.println("Enter song ID:");
                int songID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.deleteSong(songID);
                break;
            case 6:
                System.out.println("Enter playlist ID:");
                int playlistID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.deletePlaylist(playlistID);
                break;
            case 7:
                System.out.println("Enter liked song ID:");
                int likedID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.deleteLikedSong(likedID);
                break;
            case 8:
                System.out.println("Enter playlist ID and song ID:");
                int plID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                int plSongID = getIntInput(scanner, 1, Integer.MAX_VALUE);
                queries.deletePlaylistSong(plID, plSongID);
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                break;
        }
    }

    // Utility methods for input validation and sanitization

    private static int getIntInput(Scanner scanner, int min, int max) {
        int input;
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println(
                        "Invalid input. Please enter a number between " +
                        min +
                        " and " +
                        max +
                        ":"
                    );
                }
            } else {
                System.out.println(
                    "Invalid input. Please enter a valid number:"
                );
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static String getSanitizedStringInput(Scanner scanner) {
        String input = scanner.next();
        return input.replaceAll("[^a-zA-Z0-9_\\- ]", ""); // Sanitize input by removing special characters
    }

    private static String getValidatedDateInput(Scanner scanner) {
        String date;
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        while (true) {
            date = scanner.next();
            Matcher matcher = datePattern.matcher(date);
            if (matcher.matches()) {
                return date;
            } else {
                System.out.println(
                    "Invalid date format. Please enter a date in the format 'yyyy-mm-dd':"
                );
            }
        }
    }

    private static boolean getBooleanInput(Scanner scanner) {
        while (true) {
            String input = scanner.next().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println(
                    "Invalid input. Please enter 'true' or 'false':"
                );
            }
        }
    }
}
