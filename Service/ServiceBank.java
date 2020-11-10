package InvoiceProgram.Service;

import InvoiceProgram.Logger.InvoiceLog;
import InvoiceProgram.Model.Address;
import InvoiceProgram.Model.Bank;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.PublicPlace;
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

public class ServiceBank {

    private ArrayList<Bank> banks;
    private InvoiceLog log = new InvoiceLog();

    public void getAllBank(JTable table) {
        banks = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From bank ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceCountry country = new ServiceCountry();
                    Country c = country.getOneCountryById(rs.getString("countryId"));
                    ServiceZips zip = new ServiceZips();
                    Zipcode z = null;
                    if (rs.getInt("zipId") != 0) {
                        z = zip.getOneZipcode(rs.getInt("zipId"));
                    }
                    ServicePublicPlace publicPlace = new ServicePublicPlace();
                    PublicPlace p = publicPlace.getOnePublicPlace(rs.getInt("publicplaceId"));
                    Address address = new Address(c, rs.getString("regio"), z, rs.getString("publicPlaceName"), p, rs.getInt("publicPlaceNumber"), rs.getString("building"), rs.getString("stairWay"), rs.getString("storey"), rs.getInt("doornumber"), rs.getString("district"));
                    banks.add(new Bank(rs.getInt("id"), rs.getString("name"), rs.getString("swift"), address));

                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[5];
                for (int i = 0; i < banks.size(); i++) {
                    row[0] = banks.get(i).getId();
                    row[1] = banks.get(i).getName().toUpperCase();
                    row[2] = banks.get(i).getSwift();
                    row[3] = banks.get(i).getAddress().getCountry().getName();
                    if (banks.get(i).getAddress().getZip()!= null) {
                        row[4] = banks.get(i).getAddress().getZip().getCode() + " " + banks.get(i).getAddress().getZip().getName() + ", " + banks.get(i).getAddress().getPublicPlaceName() + " " + banks.get(i).getAddress().getPublicPlaceKind().getName() + " " + banks.get(i).getAddress().getPublicPlaceNumber();
                    }
                    else{
                        row[4] =   banks.get(i).getAddress().getPublicPlaceName() + " " + banks.get(i).getAddress().getPublicPlaceKind().getName() + " " + banks.get(i).getAddress().getPublicPlaceNumber();
                    }
                    

                    model.addRow(row);

                }

            } catch (SQLException ex) {
                log.log(Level.INFO, ex.getMessage());
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        log.log(Level.SEVERE, e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException ex) {
            log.log(Level.INFO, ex.getMessage());
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Bank> getAllBank() {
        ArrayList<Bank> list = new ArrayList<Bank>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From bank ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceCountry country = new ServiceCountry();
                    Country c = country.getOneCountryById(rs.getString("countryId"));
                    ServiceZips zip = new ServiceZips();
                    Zipcode z = zip.getOneZipcode(rs.getInt("zipId"));
                    ServicePublicPlace publicPlace = new ServicePublicPlace();
                    PublicPlace p = publicPlace.getOnePublicPlace(rs.getInt("publicplaceId"));
                    Address address = new Address(c, rs.getString("regio"), z, rs.getString("publicPlaceName"), p, rs.getInt("publicPlaceNumber"), rs.getString("building"), rs.getString("stairWay"), rs.getString("storey"), rs.getInt("doornumber"), rs.getString("district"));
                    list.add(new Bank(rs.getInt("id"), rs.getString("name"), rs.getString("swift"), address));

                }

                return list;
            } catch (SQLException ex) {
                log.log(Level.INFO, ex.getMessage());
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
            log.log(Level.INFO, ex.getMessage());
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void newBank(JFrame view, String name, String swift, String countryId, String region, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `bank`(`name`, `swift`, `countryid`, `regio`, `zipId`, `publicPlaceName`, `publicplaceId`, `publicPlaceNumber`, `building`, `stairWay`, `storey`, `doornumber`, `district`) VALUES (? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, swift);
            pst.setString(3, countryId);
            pst.setString(4, region);
            pst.setInt(5, zipid);
            pst.setString(6, publicPlaceName);
            pst.setInt(7, publicplaceId);
            pst.setInt(8, publicPlaceNumber);
            pst.setString(9, "");
            pst.setString(10, "");
            pst.setString(11, "");
            pst.setInt(12, 0);
            pst.setString(13, "");
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres rögzítés!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok módosítása nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            log.log(Level.INFO, ex.getMessage());
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.log(Level.SEVERE, e.getMessage());
                    e.printStackTrace();
                }
            }

        }
    }

    public void deleteBank(JFrame view, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "DELETE FROM `bank` WHERE id = ?";
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
            log.log(Level.INFO, ex.getMessage());
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.log(Level.SEVERE, e.getMessage());
                    e.printStackTrace();
                }
            }

        }
    }

    public ArrayList<Bank> getOneBank(JFrame view, int id) {
        ArrayList<Bank> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `bank` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceCountry country = new ServiceCountry();
                    Country c = country.getOneCountryById(rs.getString("countryId"));
                    ServiceZips zip = new ServiceZips();
                    Zipcode z = zip.getOneZipcode(rs.getInt("zipId"));
                    ServicePublicPlace publicPlace = new ServicePublicPlace();
                    PublicPlace p = publicPlace.getOnePublicPlace(rs.getInt("publicplaceId"));
                    Address address = new Address(c, rs.getString("regio"), z, rs.getString("publicPlaceName"), p, rs.getInt("publicPlaceNumber"), rs.getString("building"), rs.getString("stairWay"), rs.getString("storey"), rs.getInt("doornumber"), rs.getString("district"));
                    list.add(new Bank(rs.getInt("id"), rs.getString("name"), rs.getString("swift"), address));

                }

                return list;

            } catch (SQLException ex) {
                log.log(Level.INFO, ex.getMessage());
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        log.log(Level.SEVERE, e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException ex) {
            log.log(Level.INFO, ex.getMessage());
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Bank getOneBank(int id) {
        Bank list = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `bank` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceCountry country = new ServiceCountry();
                    Country c = country.getOneCountryById(rs.getString("countryId"));
                    ServiceZips zip = new ServiceZips();
                    Zipcode z = zip.getOneZipcode(rs.getInt("zipId"));
                    ServicePublicPlace publicPlace = new ServicePublicPlace();
                    PublicPlace p = publicPlace.getOnePublicPlace(rs.getInt("publicplaceId"));
                    Address address = new Address(c, rs.getString("regio"), z, rs.getString("publicPlaceName"), p, rs.getInt("publicPlaceNumber"), rs.getString("building"), rs.getString("stairWay"), rs.getString("storey"), rs.getInt("doornumber"), rs.getString("district"));
                    list = new Bank(rs.getInt("id"), rs.getString("name"), rs.getString("swift"), address);

                }

                return list;

            } catch (SQLException ex) {
                log.log(Level.INFO, ex.getMessage());
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        log.log(Level.SEVERE, e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException ex) {
            log.log(Level.INFO, ex.getMessage());
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updateBank(JFrame view, String name, String swift, String countryId, String region, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `bank` SET `name`= ?,`swift`= ? ,`countryid`= ? ,`regio`= ? ,`zipId`= ? ,`publicPlaceName`= ? ,`publicplaceId`= ? ,`publicPlaceNumber`= ? ,`building`= ? ,`stairWay`= ? ,`storey`= ? ,`doornumber`= ?  WHERE id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, swift);
            pst.setString(3, countryId);
            pst.setString(4, region);
            pst.setInt(5, zipid);
            pst.setString(6, publicPlaceName);
            pst.setInt(7, publicplaceId);
            pst.setInt(8, publicPlaceNumber);
            pst.setString(9, "");
            pst.setString(10, "");
            pst.setString(11, "");
            pst.setInt(12, 0);
            pst.setInt(13, id);
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok rögzítése nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            log.log(Level.INFO, ex.getMessage());
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.log(Level.SEVERE, e.getMessage());
                    e.printStackTrace();
                }
            }

        }
    }

    public boolean isBankAvailable() {

        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From bank ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                if (rs.next()) {
                    return true;

                }

            } catch (SQLException ex) {
                log.log(Level.INFO, ex.getMessage());
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        log.log(Level.SEVERE, e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException ex) {
            log.log(Level.INFO, ex.getMessage());
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isValidSwift(String code) {
        String regex = "[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?";
        Pattern p = Pattern.compile(regex);

        if (code == null) {
            return false;
        }
        Matcher m = p.matcher(code);

        return m.matches();

    }
}
