package InvoiceProgram.Service;

import InvoiceProgram.Model.Address;
import InvoiceProgram.Model.AddressPost;
import InvoiceProgram.Model.CompanyAddress;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Division;
import InvoiceProgram.Model.Firm;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Model.Zipcode;
import InvoiceProgram.View.FirmEdit;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class ServiceFirm {

    private ArrayList<Firm> firm;

    public ArrayList<Firm> getFirm() {
        firm = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From firm ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {

                    firm.add(new Firm(rs.getInt("id"), rs.getString("shortName"), rs.getString("fullName"), rs.getString("voucherName"), rs.getString("VATNumber"), rs.getString("VATNumberEU"), rs.getString("VATNumberGroup")));

                }

                return firm;
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

        return firm;
    }

    public Firm getFirmToXML() {
        Firm firm = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From firm ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {

                    firm = new Firm(rs.getInt("id"), rs.getString("shortName"), rs.getString("fullName"), rs.getString("voucherName"), rs.getString("VATNumber"), rs.getString("VATNumberEU"), rs.getString("VATNumberGroup"));

                }

                return firm;
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

        return firm;
    }

    public ArrayList<CompanyAddress> getCompanyAddress() {
        ArrayList<CompanyAddress> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `companyaddress` WHERE isFirm = 1";
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

                    Country c2 = country.getOneCountryById(rs.getString("postcountryId"));
                    Zipcode z2 = zip.getOneZipcode(rs.getInt("postzipId"));
                    AddressPost addresspost = new AddressPost(c2, z2, rs.getString("postaddress"), rs.getString("postregio"));
                    list.add(new CompanyAddress(rs.getInt("id"), rs.getBoolean("isFirm"), rs.getInt("divisionId"), address, rs.getBoolean("isPostInherited"), addresspost));

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

    public CompanyAddress getCompanyAddressToXML() {
        CompanyAddress list = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `companyaddress` WHERE isFirm = 1";
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

                    Country c2 = country.getOneCountryById(rs.getString("postcountryId"));
                    Zipcode z2 = zip.getOneZipcode(rs.getInt("postzipId"));
                    AddressPost addresspost = new AddressPost(c2, z2, rs.getString("postaddress"), rs.getString("postregio"));
                    list = new CompanyAddress(rs.getInt("id"), rs.getBoolean("isFirm"), rs.getInt("divisionId"), address, rs.getBoolean("isPostInherited"), addresspost);

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

    public void getAllDivision(JTable table) {
        ArrayList<Division> list = new ArrayList<Division>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `division` INNER JOIN companyaddress On division.id=companyaddress.divisionId ";
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
                    Country c2 = country.getOneCountryById(rs.getString("postcountryId"));
                    Zipcode z2 = zip.getOneZipcode(rs.getInt("postzipId"));
                    AddressPost addresspost = new AddressPost(c2, z2, rs.getString("postaddress"), rs.getString("postregio"));
                    CompanyAddress ca = new CompanyAddress(rs.getInt(4), rs.getBoolean("isFirm"), rs.getInt("divisionId"), address, rs.getBoolean("isPostInherited"), addresspost);

                    list.add(new Division(rs.getInt(1), rs.getString("name"), ca));

                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();
                if (table.getColumnModel().getColumnCount() > 0) {
                    table.getColumnModel().getColumn(0).setMinWidth(0);
                    table.getColumnModel().getColumn(0).setPreferredWidth(0);
                    table.getColumnModel().getColumn(0).setMaxWidth(0);
                    table.getColumnModel().getColumn(1).setMinWidth(200);
                    table.getColumnModel().getColumn(1).setPreferredWidth(200);
                    table.getColumnModel().getColumn(1).setMaxWidth(200);
                    table.getColumnModel().getColumn(2).setMinWidth(595);
                    table.getColumnModel().getColumn(2).setPreferredWidth(595);
                    table.getColumnModel().getColumn(2).setMaxWidth(595);

                }

                Object[] row = new Object[3];
                for (int i = 0; i < list.size(); i++) {
                    row[0] = list.get(i).getId();
                    row[1] = list.get(i).getName().toUpperCase();
                    row[2] = String.format("%s, %s, %s %s %d", list.get(i).getAddress().getAddress().getCountry().getName().toUpperCase(), list.get(i).getAddress().getAddress().getZip().getCode(), list.get(i).getAddress().getAddress().getPublicPlaceName().toUpperCase(), list.get(i).getAddress().getAddress().getPublicPlaceKind().getName().toUpperCase(), list.get(i).getAddress().getAddress().getPublicPlaceNumber());

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

    public ArrayList<Division> getAllDivision() {
        ArrayList<Division> division = new ArrayList<Division>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `division` INNER JOIN companyaddress On division.id=companyaddress.divisionId ";
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
                    Country c2 = country.getOneCountryById(rs.getString("postcountryId"));
                    Zipcode z2 = zip.getOneZipcode(rs.getInt("postzipId"));
                    AddressPost addresspost = new AddressPost(c2, z2, rs.getString("postaddress"), rs.getString("postregio"));
                    CompanyAddress ca = new CompanyAddress(rs.getInt(4), rs.getBoolean("isFirm"), rs.getInt("divisionId"), address, rs.getBoolean("isPostInherited"), addresspost);

                    division.add(new Division(rs.getInt(1), rs.getString("name"), ca));

                }
                return division;

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
        return division;
    }

    public Division getAllDivision(int id) {
        Division list = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `division` INNER JOIN companyaddress On division.id=companyaddress.divisionId where division.id= ?";
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
                    Country c2 = country.getOneCountryById(rs.getString("postcountryId"));
                    Zipcode z2 = zip.getOneZipcode(rs.getInt("postzipId"));
                    AddressPost addresspost = new AddressPost(c2, z2, rs.getString("postaddress"), rs.getString("postregio"));
                    CompanyAddress ca = new CompanyAddress(rs.getInt(4), rs.getBoolean("isFirm"), rs.getInt("divisionId"), address, rs.getBoolean("isPostInherited"), addresspost);

                    list = new Division(rs.getInt(1), rs.getString("name"), ca);

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

    public void updateFirm(JFrame view, String shorName, String fullName, String voucherName, String VATNumber, String VATNumberEU, String VATNumberGroup) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `firm` SET `shortName`= ? ,`fullName`= ? ,`voucherName`= ? ,`VATNumber`= ? ,`VATNumberEU`= ? ,`VATNumberGroup`= ?  WHERE id = 1";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, shorName);
            pst.setString(2, fullName);
            pst.setString(3, voucherName);
            pst.setString(4, VATNumber);
            pst.setString(5, VATNumberEU);
            pst.setString(6, VATNumberGroup);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public void updateFirmAddess(JFrame view, String countryId, String regio, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, String building, String stairWay, String storey, int doornumber, String district, boolean postInherited, String postcountryId, int postzipid, String postaddress, String postregio) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `companyaddress` SET `countryId`= ? ,`regio`= ? ,`zipId`= ? ,`publicPlaceName`= ? ,`publicPlaceId`= ? ,`publicPlaceNumber`= ? ,`building`= ? ,`stairWay`= ? ,`storey`= ? ,`doornumber`= ? ,`district`= ? ,`isPostInherited`= ? ,`postcountryId`= ? ,`postzipId`= ? ,`postaddress`= ? ,`postregio`= ?  WHERE `isFirm`= 1";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, countryId);
            pst.setString(2, regio);
            pst.setInt(3, zipid);
            pst.setString(4, publicPlaceName);
            pst.setInt(5, publicplaceId);
            pst.setInt(6, publicPlaceNumber);
            pst.setString(7, building);
            pst.setString(8, stairWay);
            pst.setString(9, storey);
            pst.setInt(10, doornumber);
            pst.setString(11, district);

            if (postInherited) {
                pst.setInt(12, 1);
            } else {
                pst.setInt(12, 0);
            }
            pst.setString(13, postcountryId);
            pst.setInt(14, postzipid);
            pst.setString(15, postaddress);
            pst.setString(16, postregio);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public ArrayList<Division> getOneDivision(int id) {
        ArrayList<Division> division = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `division` INNER JOIN companyaddress On division.id=companyaddress.divisionId  where division.id= ?  ";
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
                    Country c2 = country.getOneCountryById(rs.getString("postcountryId"));
                    Zipcode z2 = zip.getOneZipcode(rs.getInt("postzipId"));
                    AddressPost addresspost = new AddressPost(c2, z2, rs.getString("postaddress"), rs.getString("postregio"));
                    CompanyAddress ca = new CompanyAddress(rs.getInt(4), rs.getBoolean("isFirm"), rs.getInt("divisionId"), address, rs.getBoolean("isPostInherited"), addresspost);

                    division.add(new Division(rs.getInt(1), rs.getString("name"), ca));

                }

                return division;
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

        return division;
    }

    public int getLastDivision() {
        int id = 0;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM division ORDER BY id DESC LIMIT 1";
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

    public void updateDivision(JFrame view, String name, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `division` SET `name`= ?  WHERE id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, id);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public void updateDivisionAddess(JFrame view, int id, String countryId, String region, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, String building, String stairWayDiv, String storey, int doornumber, String district, String postcountryId, int postzipid, String postaddress, String postregion) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `companyaddress` SET `countryId`= ? ,`regio`= ? ,`zipId`= ? ,`publicPlaceName`= ? ,`publicPlaceId`= ? ,`publicPlaceNumber`= ? ,`building`= ? ,`stairWay`= ? ,`storey`= ? ,`doornumber`= ? ,`district`= ? ,`postcountryId`= ? ,`postzipId`= ? ,`postaddress`= ? ,`postregio`= ?  WHERE `divisionId`= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, countryId);
            pst.setString(2, region);
            pst.setInt(3, zipid);
            pst.setString(4, publicPlaceName);
            pst.setInt(5, publicplaceId);
            pst.setInt(6, publicPlaceNumber);
            pst.setString(7, building);
            pst.setString(8, stairWayDiv);
            pst.setString(9, storey);
            pst.setInt(10, doornumber);
            pst.setString(11, district);
            pst.setString(12, postcountryId);
            pst.setInt(13, postzipid);
            pst.setString(14, postaddress);
            pst.setString(15, postregion);
            pst.setInt(16, id);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public void newDivision(JFrame view, String name) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `division`(`name`) VALUES (?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres rögzítés!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

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

    public void newDivisionAddess(JFrame view, String countryId, String region, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, String building, String stairWayDiv, String storey, int doornumber, String district, String postcountryId, int postzipid, String postaddress, String postregion) {
        int divisionid = getLastDivision();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `companyaddress`(`isFirm`, `divisionId`, `countryId`, `regio`, `zipId`, `publicPlaceName`, `publicPlaceId`, `publicPlaceNumber`, `building`, `stairWay`, `storey`, `doornumber`, `district`, `isPostInherited`, `postcountryId`, `postzipId`, `postaddress`, `postregio`) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setInt(2, divisionid);
            pst.setString(3, countryId);
            pst.setString(4, region);
            pst.setInt(5, zipid);
            pst.setString(6, publicPlaceName);
            pst.setInt(7, publicplaceId);
            pst.setInt(8, publicPlaceNumber);
            pst.setString(9, building);
            pst.setString(10, stairWayDiv);
            pst.setString(11, storey);
            pst.setInt(12, doornumber);
            pst.setString(13, district);
            pst.setInt(14, 0);
            pst.setString(15, postcountryId);
            pst.setInt(16, postzipid);
            pst.setString(17, postaddress);
            pst.setString(18, postregion);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres rögzítés!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Sikeres rögzítés!", "Adat rögzítés", JOptionPane.ERROR_MESSAGE);
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

    public void deleteDivision(JFrame view, int value) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "DELETE FROM `division` WHERE id = ?";
            String sql2 = "DELETE FROM `companyaddress` WHERE divisionId = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, value);
            int rs = pst.executeUpdate();
            PreparedStatement pst2 = conn.prepareStatement(sql2);
            pst2.setInt(1, value);
            int rs2 = pst2.executeUpdate();
            if (rs == 1 && rs2 == 1) {
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

    public void newFirm(FirmEdit view, String shorName, String fullName, String voucherName, String VATNumber, String VATNumberEU, String VATNumberGroup) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `firm`(`shortName`, `fullName`, `voucherName`, `VATNumber`, `VATNumberEU`, `VATNumberGroup`) VALUES (? , ? , ? , ? , ? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, shorName);
            pst.setString(2, fullName);
            pst.setString(3, voucherName);
            pst.setString(4, VATNumber);
            pst.setString(5, VATNumberEU);
            pst.setString(6, VATNumberGroup);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A Cég adataitainak rögzítése sikerült!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

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

    public void newFirmAddess(FirmEdit view, String countryId, String regio, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, String building, String stairWay, String storey, int doornumber, String district, boolean postInherited, String postcountryId, int postzipid, String postaddress, String postregio) {
       Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `companyaddress`(`isFirm`, `divisionId`, `countryId`, `regio`, `zipId`, `publicPlaceName`, `publicPlaceId`, `publicPlaceNumber`, `building`, `stairWay`, `storey`, `doornumber`, `district`, `isPostInherited`, `postcountryId`, `postzipId`, `postaddress`, `postregio`) VALUES ('1' , '0' , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, countryId);
            pst.setString(2, regio);
            pst.setInt(3, zipid);
            pst.setString(4, publicPlaceName);
            pst.setInt(5, publicplaceId);
            pst.setInt(6, publicPlaceNumber);
            pst.setString(7, building);
            pst.setString(8, stairWay);
            pst.setString(9, storey);
            pst.setInt(10, doornumber);
            pst.setString(11, district);

            if (postInherited) {
                pst.setInt(12, 1);
            } else {
                pst.setInt(12, 0);
            }
            pst.setString(13, postcountryId);
            pst.setInt(14, postzipid);
            pst.setString(15, postaddress);
            pst.setString(16, postregio);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres adatrögzítés!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

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
    
    public boolean isValidVatNumber(String code) {
        String regex = "^\\d{8}\\-[1-5]\\-(?:0[2-9]|[13][0-9]|2[02-9]|4[0-4]|51)$";
        Pattern p = Pattern.compile(regex);

        if (code == null) {
            return false;
        }
        Matcher m = p.matcher(code);

        return m.matches();

    }
    
    public boolean isValidEUVatNumber(String code) {
        String regex = "^(HU)?[0-9]{8}$";
        Pattern p = Pattern.compile(regex);

        if (code == null) {
            return false;
        }
        Matcher m = p.matcher(code);

        return m.matches();

    }
    public boolean isValidNumber(String code) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);

        if (code == null) {
            return false;
        }
        Matcher m = p.matcher(code);

        return m.matches();

    }

}
