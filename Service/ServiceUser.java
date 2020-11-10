package InvoiceProgram.Service;

import InvoiceProgram.Model.User;
import InvoiceProgram.View.Users;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ServiceUser {

    public void newUser(JFrame view, String username, String password, String permission, Boolean isActive) throws NoSuchAlgorithmException {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            byte[] salt = getSalt();
            String securePassword = get_SHA_512_SecurePassword(password, salt);

            conn = db.getConnection();
            String sql = "INSERT INTO `users`(`username`, `password`, `permission`, `isActive`) VALUES ( ? , ? , ? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, securePassword);
            pst.setString(3, permission);
            if (isActive) {
                pst.setInt(4, 1);
            } else {
                pst.setInt(4, 0);
            }
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres rögzítés!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok rögzítése nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

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
    }

    public boolean isValidUser(String username, String password) throws NoSuchAlgorithmException {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            byte [] salt=getSalt();
            String securePassword = get_SHA_512_SecurePassword(password, salt);
            
            
            conn = db.getConnection();
            String sql = "Select * From users Where username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            System.out.println(pst.toString());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
                return true;
            
            }
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
        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
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

    public void getAllUser(JTable table) {
        ArrayList<User> user = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM  `users`";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    user.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("permission"), rs.getBoolean("isactive")));

                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[4];
                for (int i = 0; i < user.size(); i++) {
                    row[0] = user.get(i).getId();
                    row[1] = user.get(i).getUsername();
                    row[2] = user.get(i).getPermission();
                    row[3] = user.get(i).getIsActive();

                    model.addRow(row);

                }

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
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(Users view, int value) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "DELETE FROM  `users` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, value);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A rekord eltávolítása sikeres!", "Adat eltávolítás", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az rekord eltávolítása nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

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
    }

    private static String get_SHA_512_SecurePassword(String passwordToHash, byte [] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                //sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                sb.append(String.format("%02x", bytes[i]));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public User getOneUser(int id) {
        User user = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM  `users` where id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("permission"), rs.getBoolean("isactive"));

                }

                return user;

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
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getOneUserId(String username, String password) throws NoSuchAlgorithmException {
        User user = null;
        Connection conn;
        DatabaseConnection db = new DatabaseConnection();
        conn = null;
        try {
            byte[] salt = getSalt();
            String securePassword = get_SHA_512_SecurePassword(password, salt);
            conn = db.getConnection();
            String sql = "Select * From users Where username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("permission"), rs.getBoolean("isactive"));

                }

                return user;
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
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public Boolean checkUserName(String username) {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = null;
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM  `users` where username = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            return !rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean checkPassword(int id, String password) throws NoSuchAlgorithmException {

        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            byte[] salt = getSalt();
            String securePassword = get_SHA_512_SecurePassword(password, salt);

            conn = db.getConnection();
            String sql = "SELECT * FROM  `users` where id= ? AND password = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, securePassword);
            ResultSet rs = pst.executeQuery();

            return !rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void updateUser(JFrame view, String name, String password, String permission, boolean selected, int id) throws NoSuchAlgorithmException {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            byte[] salt = getSalt();
            String securePassword = get_SHA_512_SecurePassword(password, salt);

            conn = db.getConnection();
            String sql = "UPDATE `users` SET `username`= ? ,`password`= ? ,`permission`= ? ,`isActive`= ?  WHERE id= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, securePassword);
            pst.setString(3, permission);
            if (selected) {
                pst.setInt(4, 1);
            } else {
                pst.setInt(4, 0);
            }
            pst.setInt(5, id);
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres rögzítés!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok rögzítése nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

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
    }

}
