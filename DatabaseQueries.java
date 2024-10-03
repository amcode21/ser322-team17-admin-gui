import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSetMetaData;

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
}
