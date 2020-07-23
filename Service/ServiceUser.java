package InvoiceProgram.Service;

import InvoiceProgram.Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceUser {
    
    public void newUser(String username, String password){
        User user = new User(username, password);
    }
    

    public boolean isValidUser(String username, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "Select * From users Where username=? AND password=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                conn.close();
                return true;
            } else {
                return false;

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean isValidPassword(String password) {
        String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        Pattern p = Pattern.compile(regex);

        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);

        return m.matches();

    }

    public boolean isValidUsername(String username) {
        String regex = "^[aA-zZ]\\w{5,29}$";
        Pattern p = Pattern.compile(regex);

        if (username == null) {
            return false;
        }
        Matcher m = p.matcher(username);

        return m.matches();

    }
}
