import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DatabaseQueries {
    private String _url;
    private String cName;
    private String user;
    private String Pword;

    public DatabaseQueries(String url, String className, String username, String password) {
        _url=url;
        cName=className;
        user=username;
        Pword=password;
    }

    // insertion scripts
    public void insertUser(int userID, String userName, int joinYear, String birthday) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("INSERT INTO USERS (user_id, username, year_of_joining, birthday) VALUES (?, ?, ?, ?);");
            stmt.setInt(1, userID);
            stmt.setString(2,userName);
            stmt.setInt(3,joinYear);
            stmt.setDate(4,Date.valueOf(birthday));
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the user_id being a duplicate.");
            System.out.println("It could also be due to the date being in the wrong format.");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void insertSubscription(int subscriptionID, int userID, String billingDetails, String subscriptionDate) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("INSERT INTO SUBSCRIPTIONS (subscription_id, user_id, billing_details, subscription_date) VALUES (?, ?, ?, ?);");
            stmt.setInt(1, subscriptionID);
            stmt.setInt(2,userID);
            stmt.setString(3,billingDetails);
            stmt.setDate(4,Date.valueOf(subscriptionDate));
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the subscription_id being a duplicate.");
            System.out.println("It could also be due to the date being in the wrong format.");
            System.out.println("Or it could be because the user_id does not exist in the system yet.");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void insertArtist(int artistID, String name, String description, boolean isVerified) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("INSERT INTO ARTISTS (artist_id, name, description, is_verified) VALUES (?, ?, ?, ?);");
            stmt.setInt(1, artistID);
            stmt.setString(2,name);
            stmt.setString(3,description);
            stmt.setBoolean(4,isVerified);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the artist_id being a duplicate.");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void insertAlbum(int albumID, String name, int releaseYear, String image, int artistID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("INSERT INTO ALBUMS (album_id, name, release_year, image, artist_id) VALUES (?, ?, ?, ?);");
            stmt.setInt(1, albumID);
            stmt.setString(2,name);
            stmt.setInt(3,releaseYear);
            stmt.setString(4,image);
            stmt.setInt(5,artistID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the album being a duplicate.");
            System.out.println("It could also be because the artist ID does not exist yet.");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    //TODO: insert songs, likedSongs, playlists, and playlistSongs

    //selection scripts
    public void SelectUsersWithSubscription() {
        //initialization
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.createStatement();
            rs=stmt.executeQuery("SELECT u.username FROM USERS u JOIN SUBSCRIPTIONS s ON u.user_id = s.user_id;");
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void SelectUsersWithNoSubscription() {
        //initialization
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.createStatement();
            rs=stmt.executeQuery("SELECT u.username FROM USERS u LEFT JOIN SUBSCRIPTIONS s ON u.user_id = s.user_id WHERE s.user_id IS NULL;");
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void SelectUsersJoinedBeforeYear(int year) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.prepareStatement("SELECT u.username FROM USERS u WHERE u.year_of_joining < ?;");
            stmt.setInt(1, year);
            rs=stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void compareSubscriptionDateJoinDate() {
        //initialization
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.createStatement();
            rs=stmt.executeQuery("SELECT u.username, u.year_of_joining, s.subscription_date FROM USERS u JOIN SUBSCRIPTIONS s ON u.user_id = s.user_id;");
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void SelectUsersWithBirthday(String birthday) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.prepareStatement("SELECT u.username FROM USERS u WHERE u.birthday = ?;");
            stmt.setDate(1, Date.valueOf(birthday));
            rs=stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
            System.out.println("This is likely because the date is in the wrong format.");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void getSubscriberCount() {
        //initialization
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.createStatement();
            rs=stmt.executeQuery("SELECT COUNT(*) AS subscriber_count FROM SUBSCRIPTIONS;");
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void getLikedSongPostSubscription() {
        //initialization
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.createStatement();
            rs=stmt.executeQuery("SELECT COUNT(DISTINCT s.user_id) AS subscribers_liked_after_subscription FROM SUBSCRIPTIONS s JOIN LIKED_SONGS ls ON s.user_id = ls.user_id WHERE s.subscription_date < ls.liked_date;");
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void SelectSongsLikedByUser(int userID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.prepareStatement("SELECT s.song_name, s.release_year, ls.liked_date FROM LIKED_SONGS ls JOIN SONGS s ON ls.song_id = s.song_id WHERE ls.user_id = ?;");
            stmt.setInt(1, userID);
            rs=stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void SelectSongsByAlbum(int albumID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.prepareStatement("SELECT s.song_name, s.release_year FROM SONGS s WHERE s.album_id = ?;");
            stmt.setInt(1, albumID);
            rs=stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void SelectSongsLikedBeforeDate(String day) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            //set up a prepared statement
            stmt=conn.prepareStatement("SELECT s.song_name, ls.liked_date FROM LIKED_SONGS ls JOIN SONGS s ON ls.song_id = s.song_id WHERE ls.liked_date < ?;");
            stmt.setDate(1, Date.valueOf(day));
            rs=stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            //print out column headers
            for (int i=1; i <= numColumns; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("");
            //print out the data
            Object obj = null;
            //while there are upcoming items
            while (rs.next()) {
                //go through each column, and print it out
                for (int i=1; i <= numColumns; i++) {
                    obj = rs.getObject(i);
                    if (obj != null) {
                        System.out.print(rs.getObject(i).toString() + "\t");
                    }
                    else {
                        System.out.print("\t\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query");
            System.out.println("This is likely because the date is in the wrong format.");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    //TODO: select songs in playlistID, select playlists by userID, select songs liked by users joining before year, select verified artists, select albums from year, select albums by artist id, select artists with albums from after a year, select users who liked a song in a playlist id, select artists whose songs are in a playlist id, select top n most liked songs
    //TODO: from lines 54 onwards in selection SQL script

    //update scripts
    public void updateUsername(String username, int userID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE users SET username=? WHERE user_id=?;");
            stmt.setString(1,username);
            stmt.setInt(2, userID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the user id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateYearofJoining(int joinYear, int userID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE users SET year_of_joining=? WHERE user_id=?;");
            stmt.setInt(1,joinYear);
            stmt.setInt(2, userID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the user id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateUserBirthday(String birthday, int userID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE users SET birthday=? WHERE user_id=?;");
            stmt.setDate(1,Date.valueOf(birthday));
            stmt.setInt(2, userID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the user id not currently existing");
            System.out.println("This could also be because the date is in the wrong format");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateBillingDetails(String billingDetails, int subscriptionID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE subscriptions SET billing_details=? WHERE subscription_id=?;");
            stmt.setString(1,billingDetails);
            stmt.setInt(2, subscriptionID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the subscription id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateSubscriptionDate(String subscriptionDate, int subscriptionID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE subscriptions SET subscription_date=? WHERE subscription_id=?;");
            stmt.setDate(1,Date.valueOf(subscriptionDate));
            stmt.setInt(2, subscriptionID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the subscription id not currently existing");
            System.out.println("This could also be because the subscription date is in the wrong format");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateArtistName(String name, int artistID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE artists SET name=? WHERE artist_id=?;");
            stmt.setString(1,name);
            stmt.setInt(2, artistID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the artist id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateArtistDescription(String desc, int artistID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE artists SET description=? WHERE artist_id=?;");
            stmt.setString(1,desc);
            stmt.setInt(2, artistID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the artist id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateArtistVerificationStatus(boolean isVerified, int artistID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE artists SET is_verified=? WHERE artist_id=?;");
            stmt.setBoolean(1,isVerified);
            stmt.setInt(2, artistID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the artist id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateAlbumName(String name, int albumID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE albums SET nameimage=? WHERE album_id=?;");
            stmt.setString(1,name);
            stmt.setInt(2, albumID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the album id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateAlbumYear(int year, int albumID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE albums SET release_year=? WHERE album_id=?;");
            stmt.setInt(1,year);
            stmt.setInt(2, albumID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the album id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateAlbumImage(String imgURL, int albumID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE albums SET image=? WHERE album_id=?;");
            stmt.setString(1,imgURL);
            stmt.setInt(2, albumID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the album id not currently existing");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void updateAlbumArtist(int artistID, int albumID) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("UPDATE albums SET artist_id=? WHERE album_id=?;");
            stmt.setInt(1,artistID);
            stmt.setInt(2, albumID);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to the album id not currently existing");
            System.out.println("This could also be because the artist id does not currently exist");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    //TODO: add songs.artistID, albumID, songName, releaseYear, category, producer, credits, playlists.creatorID, playlistName, createdDate, published, description

    //delete scripts
    public void deleteUser(int user_id) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("DELETE FROM users WHERE user_id=?;");
            stmt.setInt(1,user_id);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to other tables depending on this user ID. Please delete them first.");
            System.out.println("This could also be due to the user ID already not existing. ");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void deleteSubscription(int user_id) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("DELETE FROM users WHERE user_id=?;");
            stmt.setInt(1,user_id);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to other tables depending on this user ID. Please delete them first.");
            System.out.println("This could also be due to the user ID already not existing. ");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void deleteArtist(int user_id) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("DELETE FROM users WHERE user_id=?;");
            stmt.setInt(1,user_id);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to other tables depending on this user ID. Please delete them first.");
            System.out.println("This could also be due to the user ID already not existing. ");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    public void deleteAlbum(int user_id) {
        //initialization
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName(cName);
            conn = DriverManager.getConnection(_url, user, Pword);
            conn.setAutoCommit(false);
            //set up a prepared statement
            stmt=conn.prepareStatement("DELETE FROM users WHERE user_id=?;");
            stmt.setInt(1,user_id);
            rs=stmt.executeQuery();
            //if the statement executes, print SUCCESS, and commit it to the database
            if(stmt.executeUpdate()>0) {
                System.out.println("The statement successfully executed");
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist");
        } catch (SQLException s) {
            System.out.println("There was an error executing the given query.");
            System.out.println("This is likely due to other tables depending on this user ID. Please delete them first.");
            System.out.println("This could also be due to the user ID already not existing. ");
        } finally {
            try {
                //close resources
                if(rs!=null) {
                    rs.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (SQLException s) {
                System.out.println("Resources failed to close");
            }
        }
    }
    //TODO: delete songs, likedSongs, playlists, and playlistSongs
}
