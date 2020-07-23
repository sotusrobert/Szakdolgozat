package InvoiceProgram.Service;

import InvoiceProgram.Model.PublicPlace;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ServicePublicPlace {

    private ArrayList<PublicPlace> publicPlaces;

    public void getAllPublicPlace(JTable table) {
        publicPlaces = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "SELECT * FROM `publicplace`";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    publicPlaces.add(new PublicPlace(rs.getInt("id"), rs.getString("countryId"), rs.getString("name")));

                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[3];
                for (int i = 0; i < publicPlaces.size(); i++) {
                    row[0] = publicPlaces.get(i).getId();
                    row[1] = publicPlaces.get(i).getCountryId().toUpperCase();
                    row[2] = publicPlaces.get(i).getName().toUpperCase();
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newPublicPlace(JFrame view, String id, String name) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "INSERT INTO `publicplace`(`countryId`, `name`) VALUES (?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, name);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres rögzítés!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok rögzítése nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<PublicPlace> getOnePublicPlace(String id) {
        ArrayList<PublicPlace> list = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "SELECT * FROM `publicplace` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    list.add(new PublicPlace(rs.getInt("id"), rs.getString("countryId"), rs.getString("name")));

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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public PublicPlace getOnePublicPlace(int id) {
        PublicPlace list = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "SELECT * FROM `publicplace` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    list= new PublicPlace(rs.getInt("id"), rs.getString("countryId"), rs.getString("name"));

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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void deletePublicPlace(JFrame view, String id) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "DELETE FROM `publicplace` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A rekord eltávolítása sikeres!", "Adat eltávolítás", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az rekord eltávolítása nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
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

    public void updatePublicPlace(JFrame view, String id, String name, String languageId) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "UPDATE `publicplace` SET `countryId`=?,`name`=? WHERE id= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, languageId);
            pst.setString(2, name);
            pst.setString(3, id);
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok módosítása nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
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