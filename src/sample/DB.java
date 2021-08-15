package sample;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DB_NAME = "otozh_db";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";
    private Connection dbConn;

    public Connection getDbConnection() throws ClassNotFoundException,SQLException{
        String connectionString = "jdbc:mysql://"+ HOST + ":"+PORT+"/"+DB_NAME+"?useUnicode=true&serverTimezone"
                                  + "=Europe/Moscow&useSSL=false&allowPublicKeyRetrieval=true";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConn = DriverManager.getConnection(connectionString,LOGIN,PASSWORD);
        return dbConn;
    }


    public void insertTask(String task) throws SQLException,ClassNotFoundException{
        String sql = "INSERT INTO `todo`  (task) VALUES(?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1,task);
        prSt.executeUpdate();
    }

    public ArrayList<String> getTasks() throws SQLException,ClassNotFoundException{
        String sql = "SELECT * FROM todo ORDER BY 'id' DESC";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        ArrayList<String> tasks = new ArrayList<>();
        while(res.next()) {
            tasks.add(res.getString("task"));
        }
        return tasks;
    }


}
