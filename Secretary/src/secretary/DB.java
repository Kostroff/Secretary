package secretary;

import java.sql.*;

public class DB {
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static Connection con = null;
    private static String dbName = null;
    private static String login = null;
    private static String password = null;

    public static Connection getCon() {
        return con;
    }

    public DB(String dbName, String login, String password) {
        DB.dbName = dbName;
        DB.login = login;
        DB.password = password;
        if (!dbExists()) {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(dbName + ";create=true", login, password);
                Event.setCon(con);
            } catch (ClassNotFoundException | SQLException e) {
            }
        }
    }
    
    private Boolean dbExists() {
        Boolean exists = false;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dbName);
            exists = true;
        } catch (ClassNotFoundException | SQLException e) {
        }
        return (exists);
    }

    // запрос на обновление базы данных  (INSERT, UPDATE, CREATE TABLE и т.п.)
    public void executeUpdate(String sql) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    // запрос на выборку данных из базы
    public ResultSet executeQuery(String sql) throws SQLException {
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stmt.executeQuery(sql);
        return result;
    }
}
