package Sorry;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DBconnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private String name, tempName;
    private int gamesPlayed;
    private int gamesWon;

    //Method that connects to database
    public DBconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://webdb.uvm.edu/GDCOHEN_cs148", "gdcohen_admin", "5DsTrZPCAMN7TFOL");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // Gets data that's in the database
    public void getData() {
        try {
            String query = "SELECT * FROM tblPlayers";
            rs = st.executeQuery(query);
            System.out.println("Records from Database");
            while (rs.next()) {
                name = rs.getString("pmkUserName");
                gamesPlayed = rs.getInt("fldGamesPlayed");
                gamesWon = rs.getInt("fldGamesWon");
//                System.out.println("Name: " + name + "  " + "Games Played: " +
//                        gamesPlayed + "Games Won: " + gamesWon);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int getNumPlayers() {
        int numPlayers = 0;
        try {
            String query = "SELECT pmkUserName FROM tblPlayers";
            rs = st.executeQuery(query);
            while (rs.next()) {
                numPlayers++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return numPlayers;
    }

    // TODO CONVERT ALL get___Data functions to return arrayList
    public ArrayList<String> getNameData() {
        ArrayList<String> nameList = new ArrayList<String>();
        int count = 0;
        try {
            String query = "SELECT pmkUserName FROM tblPlayers";
            rs = st.executeQuery(query);
            while (rs.next()) {
                tempName = rs.getString("pmkUserName");
                nameList.add(tempName);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return nameList;
    }

    public int getGamesPlayedValue(String name) {
        try {
            String query = "SELECT * from tblPlayers WHERE pmkUserName = '" + name + "'";
            rs = st.executeQuery(query);
            rs.next();
            gamesPlayed = rs.getInt("fldGamesPlayed");
            return gamesPlayed;
        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    //Gets the amount of games that a particular use won from the database
    public int getGamesWonValue(String name) {
        try {
            String query = "SELECT * from tblPlayers WHERE pmkUserName = '" + name + "'";
            rs = st.executeQuery(query);
            rs.next();
            gamesWon = rs.getInt("fldGamesWon");
            return gamesWon;
        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    //Writes info to the database and checks to see if the name is already in the database
    public void writeData(String newName) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tblPlayers WHERE pmkUserName =?");
            ps.setString(1, newName);
            ResultSet result = ps.executeQuery();
            //If value is alreayd in database show error to user
            if (result.next()) {
                System.out.println("Error");
                JFrame frame = new JFrame("JOptionPane showMessageDialog component example");

                JOptionPane.showMessageDialog(frame, "User Name Already Exists! Type in a " +
                        "Different User Name.");
            } else {
                ps.executeUpdate("INSERT INTO tblPlayers (pmkUserName) VALUE('" + newName + "')");
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
}
