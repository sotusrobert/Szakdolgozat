package InvoiceProgram.Service;

import InvoiceProgram.Model.Zipcode;
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

public class ServiceZips {

    private ArrayList<Zipcode> zips;

    public void getAllZip(JTable table) {
        zips = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From zip";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    zips.add(new Zipcode(rs.getInt("id"), rs.getString("countryId"), rs.getString("code"), rs.getString("name")));

                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[4];
                for (int i = 0; i < zips.size(); i++) {
                    row[0] = zips.get(i).getId();
                    row[1] = zips.get(i).getCountryId().toUpperCase();
                    row[2] = zips.get(i).getCode();
                    row[3] = zips.get(i).getName().toUpperCase();

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

    public void newZip(JFrame view, String id, int code, String name) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `zip`(`countryId`, `code`, `name`) VALUES (?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setInt(2, code);
            pst.setString(3, name);

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

    public void updateZip(JFrame view, String countryId, int code, String name, String id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `zip` SET `countryId`=? ,`code`=? ,`name`=? WHERE `id`=?  ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, countryId);
            pst.setInt(2, code);
            pst.setString(3, name);
            pst.setString(4, id);
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

    public ArrayList<Zipcode> getOneZipcode(String id) {
        ArrayList<Zipcode> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `zip` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    list.add(new Zipcode(rs.getInt("id"), rs.getString("countryId"), rs.getString("code"), rs.getString("name")));

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

    public boolean getOneZipByCode(int code) {

        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `zip` WHERE code = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, code);
            ResultSet rs = pst.executeQuery();
            try {
                if (rs.next()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public int getOneZipIdByCode(int code) {
        int id = 0;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `zip` WHERE code = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, code);
            ResultSet rs = pst.executeQuery();
            try {
                if (rs.next()) {

                    id = rs.getInt("id");

                }

                return id;

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
        return id;

    }

    public Zipcode getOneZipcode(int id) {
        Zipcode list = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `zip` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    list = new Zipcode(rs.getInt("id"), rs.getString("countryId"), rs.getString("code"), rs.getString("name"));

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

    public void deleteZip(JFrame view, String id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "DELETE FROM `zip` WHERE id = ?";
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

    public int getLatestZipId() {
        int id = 0;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `zip` order by id DESC LIMIT 1";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    id = rs.getInt("id");

                }

                return id;

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
        return id;
    }

    public boolean isAvailableZip(String countryId, int code) {

        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `zip` WHERE countryId = ? AND  code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, countryId);
            pst.setInt(2, code);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getOneZipIdByCode(String countryId, int code) {
        int id = 0;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `zip` WHERE countryId = ? AND  code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, countryId);
            pst.setInt(2, code);
            ResultSet rs = pst.executeQuery();
           
            try {
                while (rs.next()) {

                    id = rs.getInt("id");

                }

                return id;

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
        return id;

    }
    
    public String getOneZipIdByCodes(String countryId, int code) {
        String name="";
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `zip` WHERE countryId = ? AND  code=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, countryId);
            pst.setInt(2, code);
            ResultSet rs = pst.executeQuery();
           
            try {
                while (rs.next()) {

                    name = rs.getString("name");

                }

                return name;

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
        return name;

    }

    public boolean isValidZip(String code) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);

        if (code == null) {
            return false;
        }
        Matcher m = p.matcher(code);

        return m.matches();

    }

}
