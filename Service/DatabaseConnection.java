package InvoiceProgram.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private Connection conn;
    private PreparedStatement ps;

    public DatabaseConnection() {
        this.conn = null;
        this.ps = null;
    }

    public DatabaseConnection(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, user, password);

            System.out.println("Sikeres adatbázis kapcsolódás!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DatabaseConnectionClose() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql) {
        ResultSet rs = null;
        try {
            rs = tryQuery(sql);
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return rs;
    }

    public ResultSet tryQuery(String sql) throws SQLException {
        ResultSet rs = null;
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    


}
