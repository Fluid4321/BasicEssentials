package basicessentials.basicessentials.Utils;

import basicessentials.basicessentials.BasicEssentials;

import java.sql.*;

/*
This class is used to handle all MySQL functions.
 */

public class SQLUtils {
    public static String host, database, username, password;
    public static int port;
    public static Connection connection;
    
    public static void InitialiseSQL() {
        host = BasicEssentials.getInstance().getConfig().getString("Database.host");
        port = BasicEssentials.getInstance().getConfig().getInt("Database.port");
        database = BasicEssentials.getInstance().getConfig().getString("Database.database");
        username = BasicEssentials.getInstance().getConfig().getString("Database.username");
        password = BasicEssentials.getInstance().getConfig().getString("Database.password");
        try {
            openConnection();
            String pinfo = "CREATE TABLE IF NOT EXISTS pinfo(uuid varchar(64) NOT NULL, firstjoindate datetime  NOT NULL, lastjoindate datetime  NOT NULL, rank text NOT NULL, rankperm boolean NOT NULL, rankexpirytime datetime, rankreason text DEFAULT 'N/A', rankbefore text NOT NULL DEFAULT 'DEFAULT') engine=InnoDB;";
            String ranks = "CREATE TABLE IF NOT EXISTS ranks(name varchar(100) NOT NULL,prefix text NOT NULL,weight int NOT NULL,permissions longtext) engine=InnoDB;";
            try {
                SQLUtils.PrepNom(pinfo);
                SQLUtils.PrepNom(ranks);
                String s = "SELECT * FROM `ranks` WHERE name = 'DEFAULT';";
                ResultSet rs = preparedStatement(s).executeQuery();
                if (!rs.next()) { // Create default rank if it doesn't exist
                    SQLUtils.PrepNom("INSERT INTO `ranks`(`name`, `prefix`, `weight`, `permissions`) VALUES ('Default','&7[&c&lDefault&7] ','1','test.node,another.node')");
                }
            } catch (SQLException x) {
                x.printStackTrace();
            }
            System.out.println("Connected to MySQL!");
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    public static PreparedStatement preparedStatement(String query) {
        PreparedStatement ps = null;
        try {
            String query2 = query.replace("`", "").replace("(", "").replace(")", "");
            ps = SQLUtils.connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static ResultSet ExecuteResultStatement(String statement) {
        try {
            PreparedStatement stmt = connection.prepareStatement(statement);
            ResultSet results = stmt.executeQuery();
            return results;
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }


    private static void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
    }

    public static void PrepNom(String query) throws SQLException {
        preparedStatement(query).executeUpdate();
    }

}
