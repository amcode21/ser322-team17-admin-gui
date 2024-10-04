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

    // ======================= UTILITY METHODS =======================

    private void executeInsertOrUpdate(
        String query,
        PreparedStatementSetter setter
    ) {
        try (
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(query)
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
            PreparedStatement ps = conn.prepareStatement(query)
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

    private void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnLabel(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                System.out.print(
                    (value != null ? value.toString() : "") + "\t"
                );
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
