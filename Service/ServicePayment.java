package InvoiceProgram.Service;

import InvoiceProgram.Model.Payment;
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

public class ServicePayment {

    private ArrayList<Payment> payments;

    public void getAllPayment(JTable table) {
        payments = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `payment";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    payments.add(new Payment(rs.getInt("id"), rs.getString("name"), rs.getInt("prompt"), rs.getString("mode"), rs.getBoolean("isDefault")));

                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[5];
                for (int i = 0; i < payments.size(); i++) {
                    row[0] = payments.get(i).getId();
                    row[1] = payments.get(i).getName().toUpperCase();
                    row[2] = payments.get(i).getPrompt() + "[nap]";
                    row[3] = payments.get(i).getMode().toUpperCase();
                    row[4] = payments.get(i).isIsDefault();

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

    public ArrayList<Payment> getAllPayment() {
        ArrayList<Payment> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `payment";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    list.add(new Payment(rs.getInt("id"), rs.getString("name"), rs.getInt("prompt"), rs.getString("mode"), rs.getBoolean("isDefault")));

                }
                return list;

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
        return list;
    }

    public ArrayList<Payment> getOnePayment(String id) {
        ArrayList<Payment> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `payment` WHERE id=  ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    list.add(new Payment(rs.getInt("id"), rs.getString("name"), rs.getInt("prompt"), rs.getString("mode"), rs.getBoolean("isDefault")));

                }

                return list;

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
        return list;
    }

    public void newPayment(JFrame view, String name, int prompt, String mode, boolean isDefault) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `payment`(`name`, `prompt`, `mode`, `isDefault`) VALUES ( ? , ? , ? , ? )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, prompt);
            pst.setString(3, mode);

            if (isDefault) {
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

    public void updatePayment(JFrame view, String name, int prompt, String mode, boolean isDefault, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `payment` SET `name`= ? ,`prompt`= ? ,`mode`= ? ,`isDefault`= ?  WHERE id = ?  ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, prompt);
            pst.setString(3, mode);

            if (isDefault) {
                pst.setInt(4, 1);
            } else {
                pst.setInt(4, 0);
            }
            pst.setInt(5, id);
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok módosítása nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
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

    public void deletePayment(JFrame view, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "DELETE FROM `payment` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

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

    public Payment getOnePaymentById(int id) {
        Payment payments = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `payment` where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    payments = new Payment(rs.getInt("id"), rs.getString("name"), rs.getInt("prompt"), rs.getString("mode"), rs.getBoolean("isDefault"));

                }

                return payments;

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
        return payments;
    }
    
     public boolean isValidPrompt(String code) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);

        if (code == null) {
            return false;
        }
        Matcher m = p.matcher(code);

        return m.matches();

    }
}
