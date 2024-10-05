import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseQueries {

    private String url;
    private String className;
    private String username;
    private String password;

    public DatabaseQueries(
        String url,
        String className,
        String username,
        String password
    ) {
        this.url = url;
        this.className = className;
        this.username = username;
        this.password = password;
    }

    // ======================= INSERTION METHODS =======================

    public void insertUser(
        int userID,
        String userName,
        int joinYear,
        String birthday
    ) {
        String query =
            "INSERT INTO USERS (user_id, username, year_of_joining, birthday) VALUES (?, ?, ?, ?);";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, userID);
            ps.setString(2, userName);
            ps.setInt(3, joinYear);
            ps.setDate(4, Date.valueOf(birthday));
        });
    }

    public void insertSubscription(
        int subscriptionID,
        int userID,
        String billingDetails,
        String subscriptionDate
    ) {
        String query =
            "INSERT INTO SUBSCRIPTIONS (subscription_id, user_id, billing_details, subscription_date) VALUES (?, ?, ?, ?);";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, subscriptionID);
            ps.setInt(2, userID);
            ps.setString(3, billingDetails);
            ps.setDate(4, Date.valueOf(subscriptionDate));
        });
    }

    public void insertArtist(
        int artistID,
        String name,
        String description,
        boolean isVerified
    ) {
        String query =
            "INSERT INTO ARTISTS (artist_id, name, description, is_verified) VALUES (?, ?, ?, ?);";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, artistID);
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setBoolean(4, isVerified);
        });
    }

    public void insertAlbum(
        int albumID,
        String name,
        int releaseYear,
        String image,
        int artistID
    ) {
        String query =
            "INSERT INTO ALBUMS (album_id, name, release_year, image, artist_id) VALUES (?, ?, ?, ?, ?);";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, albumID);
            ps.setString(2, name);
            ps.setInt(3, releaseYear);
            ps.setString(4, image);
            ps.setInt(5, artistID);
        });
    }

    public void insertSong(
        int songID,
        int artistID,
        int albumID,
        String songName,
        int releaseYear,
        String category,
        String producer,
        String credits
    ) {
        String query =
            "INSERT INTO SONGS (song_id, artist_id, album_id, song_name, release_year, category, producer, credits) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, songID);
            ps.setInt(2, artistID);
            ps.setInt(3, albumID);
            ps.setString(4, songName);
            ps.setInt(5, releaseYear);
            ps.setString(6, category);
            ps.setString(7, producer);
            ps.setString(8, credits);
        });
    }

    public void insertLikedSong(
        int likedID,
        int userID,
        int songID,
        String likedDate
    ) {
        String query =
            "INSERT INTO LIKED_SONGS (liked_id, user_id, song_id, liked_date) VALUES (?, ?, ?, ?);";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, likedID);
            ps.setInt(2, userID);
            ps.setInt(3, songID);
            ps.setDate(4, Date.valueOf(likedDate));
        });
    }

    public void insertPlaylist(
        int playlistID,
        int creatorID,
        String playlistName,
        String createdDate,
        boolean published,
        String description
    ) {
        String query =
            "INSERT INTO PLAYLISTS (playlist_id, creator_id, playlist_name, created_date, published_status, description) VALUES (?, ?, ?, ?, ?, ?);";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, playlistID);
            ps.setInt(2, creatorID);
            ps.setString(3, playlistName);
            ps.setDate(4, Date.valueOf(createdDate));
            ps.setBoolean(5, published);
            ps.setString(6, description);
        });
    }

    public void insertPlaylistSong(int playlistID, int songID) {
        String query =
            "INSERT INTO PLAYLIST_SONGS (playlist_id, song_id) VALUES (?, ?);";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, playlistID);
            ps.setInt(2, songID);
        });
    }

    // ======================= SELECTION METHODS =======================

    public void selectUsersWithSubscription() {
        String query =
            "SELECT u.username FROM USERS u JOIN SUBSCRIPTIONS s ON u.user_id = s.user_id;";
        executeSelect(query);
    }

    public void selectUsersWithoutSubscription() {
        String query =
            "SELECT u.username FROM USERS u LEFT JOIN SUBSCRIPTIONS s ON u.user_id = s.user_id WHERE s.user_id IS NULL;";
        executeSelect(query);
    }

    public void selectUsersJoinedBeforeYear(int year) {
        String query =
            "SELECT u.username FROM USERS u WHERE u.year_of_joining < ?;";
        executeSelect(query, ps -> ps.setInt(1, year));
    }

    public void compareSubscriptionDateJoinDate() {
        String query =
            "SELECT u.username, u.year_of_joining, s.subscription_date FROM USERS u JOIN SUBSCRIPTIONS s ON u.user_id = s.user_id;";
        executeSelect(query);
    }

    public void selectUsersWithBirthday(String birthday) {
        String query = "SELECT u.username FROM USERS u WHERE u.birthday = ?;";
        executeSelect(query, ps -> ps.setDate(1, Date.valueOf(birthday)));
    }

    public void getSubscriberCount() {
        String query =
            "SELECT COUNT(*) AS subscriber_count FROM SUBSCRIPTIONS;";
        executeSelect(query);
    }

    public void getLikedSongPostSubscription() {
        String query =
            "SELECT COUNT(DISTINCT s.user_id) AS subscribers_liked_after_subscription FROM SUBSCRIPTIONS s JOIN LIKED_SONGS ls ON s.user_id = ls.user_id WHERE s.subscription_date < ls.liked_date;";
        executeSelect(query);
    }

    public void selectSongsLikedByUser(int userID) {
        String query =
            "SELECT s.song_name, s.release_year, ls.liked_date FROM LIKED_SONGS ls JOIN SONGS s ON ls.song_id = s.song_id WHERE ls.user_id = ?;";
        executeSelect(query, ps -> ps.setInt(1, userID));
    }

    public void selectSongsByAlbum(int albumID) {
        String query =
            "SELECT s.song_name, s.release_year FROM SONGS s WHERE s.album_id = ?;";
        executeSelect(query, ps -> ps.setInt(1, albumID));
    }

    public void selectSongsLikedBeforeDate(String day) {
        String query =
            "SELECT s.song_name, ls.liked_date FROM LIKED_SONGS ls JOIN SONGS s ON ls.song_id = s.song_id WHERE ls.liked_date < ?;";
        executeSelect(query, ps -> ps.setDate(1, Date.valueOf(day)));
    }

    public void selectSongsInPlaylist(int playlistID) {
        String query =
            "SELECT s.song_name, s.release_year FROM PLAYLIST_SONGS ps JOIN SONGS s ON ps.song_id = s.song_id WHERE ps.playlist_id = ?;";
        executeSelect(query, ps -> ps.setInt(1, playlistID));
    }

    public void selectPlaylistsByUserID(int userID) {
        String query =
            "SELECT p.playlist_name, p.created_date FROM PLAYLISTS p WHERE p.creator_id = ?;";
        executeSelect(query, ps -> ps.setInt(1, userID));
    }

    public void selectSongsLikedByUsersBeforeYear(int year) {
        String query =
            "SELECT s.song_name, u.username, u.year_of_joining FROM LIKED_SONGS ls JOIN SONGS s ON ls.song_id = s.song_id JOIN USERS u ON ls.user_id = u.user_id WHERE u.year_of_joining < ?;";
        executeSelect(query, ps -> ps.setInt(1, year));
    }

    public void selectVerifiedArtists() {
        String query =
            "SELECT a.artist_id, a.name FROM ARTISTS a WHERE a.is_verified = TRUE;";
        executeSelect(query);
    }

    public void selectAlbumsFromYear(int year) {
        String query =
            "SELECT al.album_id, al.name, al.release_year FROM ALBUMS al WHERE al.release_year = ?;";
        executeSelect(query, ps -> ps.setInt(1, year));
    }

    public void selectAlbumsByArtistID(int artistID) {
        String query =
            "SELECT al.album_id, al.name, al.release_year FROM ALBUMS al WHERE al.artist_id = ?;";
        executeSelect(query, ps -> ps.setInt(1, artistID));
    }

    public void selectArtistsWithAlbumsAfterYear(int year) {
        String query =
            "SELECT a.name, al.name AS album_name, al.release_year FROM ARTISTS a JOIN ALBUMS al ON a.artist_id = al.artist_id WHERE al.release_year > ?;";
        executeSelect(query, ps -> ps.setInt(1, year));
    }

    public void selectUsersWhoLikedSongInPlaylist(int playlistID) {
        String query =
            "SELECT u.username, s.song_name, p.playlist_name FROM USERS u JOIN LIKED_SONGS ls ON u.user_id = ls.user_id JOIN SONGS s ON ls.song_id = s.song_id JOIN PLAYLIST_SONGS ps ON s.song_id = ps.song_id JOIN PLAYLISTS p ON ps.playlist_id = p.playlist_id WHERE ps.playlist_id = ?;";
        executeSelect(query, ps -> ps.setInt(1, playlistID));
    }

    public void selectArtistsWithSongsInPlaylist(int playlistID) {
        String query =
            "SELECT ar.name AS artist_name, s.song_name, p.playlist_name FROM ARTISTS ar JOIN SONGS s ON ar.artist_id = s.artist_id JOIN PLAYLIST_SONGS ps ON s.song_id = ps.song_id JOIN PLAYLISTS p ON ps.playlist_id = p.playlist_id WHERE ps.playlist_id = ?;";
        executeSelect(query, ps -> ps.setInt(1, playlistID));
    }

    public void selectTopLikedSongs(int limit) {
        String query =
            "SELECT s.song_name, COUNT(ls.song_id) AS like_count FROM LIKED_SONGS ls JOIN SONGS s ON ls.song_id = s.song_id GROUP BY ls.song_id, s.song_name ORDER BY like_count DESC LIMIT ?;";
        executeSelect(query, ps -> ps.setInt(1, limit));
    }

    // ======================= UPDATE METHODS =======================

    public void updateUsername(String username, int userID) {
        String query = "UPDATE USERS SET username=? WHERE user_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, username);
            ps.setInt(2, userID);
        });
    }

    public void updateYearofJoining(int joinYear, int userID) {
        String query = "UPDATE USERS SET year_of_joining=? WHERE user_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, joinYear);
            ps.setInt(2, userID);
        });
    }

    public void updateUserBirthday(String birthday, int userID) {
        String query = "UPDATE USERS SET birthday=? WHERE user_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setDate(1, Date.valueOf(birthday));
            ps.setInt(2, userID);
        });
    }

    public void updateBillingDetails(
        String billingDetails,
        int subscriptionID
    ) {
        String query =
            "UPDATE SUBSCRIPTIONS SET billing_details=? WHERE subscription_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, billingDetails);
            ps.setInt(2, subscriptionID);
        });
    }

    public void updateSubscriptionDate(
        String subscriptionDate,
        int subscriptionID
    ) {
        String query =
            "UPDATE SUBSCRIPTIONS SET subscription_date=? WHERE subscription_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setDate(1, Date.valueOf(subscriptionDate));
            ps.setInt(2, subscriptionID);
        });
    }

    public void updateArtistName(String name, int artistID) {
        String query = "UPDATE ARTISTS SET name=? WHERE artist_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, name);
            ps.setInt(2, artistID);
        });
    }

    public void updateArtistDescription(String desc, int artistID) {
        String query = "UPDATE ARTISTS SET description=? WHERE artist_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, desc);
            ps.setInt(2, artistID);
        });
    }

    public void updateArtistVerificationStatus(
        boolean isVerified,
        int artistID
    ) {
        String query = "UPDATE ARTISTS SET is_verified=? WHERE artist_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setBoolean(1, isVerified);
            ps.setInt(2, artistID);
        });
    }

    public void updateAlbumName(String name, int albumID) {
        String query = "UPDATE ALBUMS SET name=? WHERE album_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, name);
            ps.setInt(2, albumID);
        });
    }

    public void updateAlbumYear(int year, int albumID) {
        String query = "UPDATE ALBUMS SET release_year=? WHERE album_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, year);
            ps.setInt(2, albumID);
        });
    }

    public void updateAlbumImage(String imgURL, int albumID) {
        String query = "UPDATE ALBUMS SET image=? WHERE album_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, imgURL);
            ps.setInt(2, albumID);
        });
    }

    public void updateAlbumArtist(int artistID, int albumID) {
        String query = "UPDATE ALBUMS SET artist_id=? WHERE album_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, artistID);
            ps.setInt(2, albumID);
        });
    }

    public void updateSongName(int songID, String songName) {
        String query = "UPDATE SONGS SET song_name=? WHERE song_id=?";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, songName);
            ps.setInt(2, songID);
        });
    }

    public void updateSongCategory(int songID, String category) {
        String query = "UPDATE SONGS SET category=? WHERE song_id=?";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, category);
            ps.setInt(2, songID);
        });
    }

    public void updateSongProducer(int songID, String producer) {
        String query = "UPDATE SONGS SET producer=? WHERE song_id=?";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, producer);
            ps.setInt(2, songID);
        });
    }

    public void updateSongCredits(int songID, String credits) {
        String query = "UPDATE SONGS SET credits=? WHERE song_id=?";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, credits);
            ps.setInt(2, songID);
        });
    }

    public void updatePlaylistName(int playlistID, String name) {
        String query =
            "UPDATE PLAYLISTS SET playlist_name=? WHERE playlist_id=?";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, name);
            ps.setInt(2, playlistID);
        });
    }

    public void updatePlaylistDescription(int playlistID, String description) {
        String query = "UPDATE PLAYLISTS SET description=? WHERE playlist_id=?";
        executeInsertOrUpdate(query, ps -> {
            ps.setString(1, description);
            ps.setInt(2, playlistID);
        });
    }

    public void updatePlaylistPublishedStatus(
        int playlistID,
        boolean publishedStatus
    ) {
        String query =
            "UPDATE PLAYLISTS SET published_status=? WHERE playlist_id=?";
        executeInsertOrUpdate(query, ps -> {
            ps.setBoolean(1, publishedStatus);
            ps.setInt(2, playlistID);
        });
    }

    // ======================= DELETE METHODS =======================

    public void deleteUser(int userID) {
        String query = "DELETE FROM USERS WHERE user_id=?;";
        executeInsertOrUpdate(query, ps -> ps.setInt(1, userID));
    }

    public void deleteSubscription(int subscriptionID) {
        String query = "DELETE FROM SUBSCRIPTIONS WHERE subscription_id=?;";
        executeInsertOrUpdate(query, ps -> ps.setInt(1, subscriptionID));
    }

    public void deleteArtist(int artistID) {
        String query = "DELETE FROM ARTISTS WHERE artist_id=?;";
        executeInsertOrUpdate(query, ps -> ps.setInt(1, artistID));
    }

    public void deleteAlbum(int albumID) {
        String query = "DELETE FROM ALBUMS WHERE album_id=?;";
        executeInsertOrUpdate(query, ps -> ps.setInt(1, albumID));
    }

    public void deleteSong(int songID) {
        String query = "DELETE FROM SONGS WHERE song_id=?;";
        executeInsertOrUpdate(query, ps -> ps.setInt(1, songID));
    }

    public void deleteLikedSong(int likedID) {
        String query = "DELETE FROM LIKED_SONGS WHERE liked_id=?;";
        executeInsertOrUpdate(query, ps -> ps.setInt(1, likedID));
    }

    public void deletePlaylist(int playlistID) {
        String query = "DELETE FROM PLAYLISTS WHERE playlist_id=?;";
        executeInsertOrUpdate(query, ps -> ps.setInt(1, playlistID));
    }

    public void deletePlaylistSong(int playlistID, int songID) {
        String query =
            "DELETE FROM PLAYLIST_SONGS WHERE playlist_id=? AND song_id=?;";
        executeInsertOrUpdate(query, ps -> {
            ps.setInt(1, playlistID);
            ps.setInt(2, songID);
        });
    }

    // ======================= UTILITY METHODS =======================

    private void executeInsertOrUpdate(
        String query,
        PreparedStatementSetter setter
    ) {
        try (
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(
                query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        ) {
            conn.setAutoCommit(false);
            setter.setParameters(ps);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("The statement successfully executed.");
            }
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            handleException(e);
        }
    }

    private void executeSelect(String query) {
        executeSelect(query, null);
    }

    private void executeSelect(String query, PreparedStatementSetter setter) {
        try (
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(
                query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        ) {
            if (setter != null) {
                setter.setParameters(ps);
            }
            try (ResultSet rs = ps.executeQuery()) {
                printResultSet(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleException(e);
        }
    }

    private void printResultSet(ResultSet rs)
        throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Initialize an array to store the maximum lengths of each column
        int[] columnLengths = new int[columnCount];

        // Set initial column lengths to the length of the column names
        for (int i = 1; i <= columnCount; i++) {
            columnLengths[i - 1] = metaData.getColumnLabel(i).length();
        }

        // First, iterate through the ResultSet once to determine the maximum column lengths
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                if (value != null) {
                    // Update the column length if the current value is longer
                    columnLengths[i - 1] = Math.max(
                        columnLengths[i - 1],
                        value.toString().length()
                    );
                }
            }
        }

        // Print the column headers with appropriate spacing
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf(
                "%-" + columnLengths[i - 1] + "s",
                metaData.getColumnLabel(i)
            );
            System.out.print("\t");
        }
        System.out.println();

        // Move the cursor back to the start to print the result rows again
        rs.beforeFirst();

        // Print each row in the ResultSet
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                System.out.printf(
                    "%-" + columnLengths[i - 1] + "s",
                    (value != null ? value.toString() : "")
                );
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        return DriverManager.getConnection(url, username, password);
    }

    private void handleException(Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
        e.printStackTrace();
    }

    @FunctionalInterface
    private interface PreparedStatementSetter {
        void setParameters(PreparedStatement ps) throws SQLException;
    }
}
