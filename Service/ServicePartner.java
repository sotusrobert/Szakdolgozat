package InvoiceProgram.Service;

import InvoiceProgram.Model.Address;
import InvoiceProgram.Model.AddressPost;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.Department;
import InvoiceProgram.Model.Partner;
import InvoiceProgram.Model.PartnerAddress;
import InvoiceProgram.Model.Payment;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Model.TransactionType;
import InvoiceProgram.Model.Zipcode;
import java.sql.Connection;
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

public class ServicePartner {

    ArrayList<Partner> partners;

    public void getAllPartner(JTable table) {
        partners = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From partner ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServicePayment payment = new ServicePayment();
                    Payment paymentid = payment.getOnePaymentById(rs.getInt("paymentId"));
                    ServiceCurrency currency = new ServiceCurrency();
                    Currency currencyId = currency.getOneCurrencyById(rs.getString("currencyId"));
                    partners.add(new Partner(rs.getInt("id"), rs.getString("shortName"), rs.getString("fullName"), rs.getString("nameOnVoucher"), rs.getBoolean("isActive"), rs.getString("VATNumber"), rs.getString("VATNumberEU"), rs.getString("VATNumberGroup"), transactiontype, paymentid, currencyId, rs.getBoolean("isVATRegistrated"), rs.getBoolean("isCashAccounting"), rs.getBoolean("isKATA"), rs.getBoolean("isNaturalPerson")));

                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[4];
                for (int i = 0; i < partners.size(); i++) {
                    row[0] = partners.get(i).getId();
                    row[1] = partners.get(i).getShortName().toUpperCase();
                    row[2] = partners.get(i).getVATNumber();
                    row[3] = partners.get(i).isIsActive();

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

    public ArrayList<Partner> getAllPartner() {
        ArrayList<Partner> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From partner ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServicePayment payment = new ServicePayment();
                    Payment paymentid = payment.getOnePaymentById(rs.getInt("paymentId"));
                    ServiceCurrency currency = new ServiceCurrency();
                    Currency currencyId = currency.getOneCurrencyById(rs.getString("currencyId"));
                    list.add(new Partner(rs.getInt("id"), rs.getString("shortName"), rs.getString("fullName"), rs.getString("nameOnVoucher"), rs.getBoolean("isActive"), rs.getString("VATNumber"), rs.getString("VATNumberEU"), rs.getString("VATNumberGroup"), transactiontype, paymentid, currencyId, rs.getBoolean("isVATRegistrated"), rs.getBoolean("isCashAccounting"), rs.getBoolean("isKATA"), rs.getBoolean("isNaturalPerson")));

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

    public ArrayList<Partner> getOnePartner(int id) {
        ArrayList<Partner> partner = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From partner where id= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServicePayment payment = new ServicePayment();
                    Payment paymentid = payment.getOnePaymentById(rs.getInt("paymentId"));
                    ServiceCurrency currency = new ServiceCurrency();
                    Currency currencyId = currency.getOneCurrencyById(rs.getString("currencyId"));
                    partner.add(new Partner(rs.getInt("id"), rs.getString("shortName"), rs.getString("fullName"), rs.getString("nameOnVoucher"), rs.getBoolean("isActive"), rs.getString("VATNumber"), rs.getString("VATNumberEU"), rs.getString("VATNumberGroup"), transactiontype, paymentid, currencyId, rs.getBoolean("isVATRegistrated"), rs.getBoolean("isCashAccounting"), rs.getBoolean("isKATA"), rs.getBoolean("isNaturalPerson")));

                }

                return partner;

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
        return partner;
    }

    public int getLastPartner() {
        int id = 0;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `partner` order by id DESC limit 1";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {

                    id = rs.getInt(1);
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

    public void newPartner(JFrame view, String shorName, String fullName, String voucherName, String VATNumber, String VATNumberEU, String VATNumberGroup, boolean active, int transactionTypeid, int paymentid, String currencyid, boolean vatRegistrated, boolean cashAccounting, boolean kata, boolean naturalPerson) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `partner`( `shortName`, `fullName`, `nameOnVoucher`, `isActive`, `VATNumber`, `VATNumberEU`, `VATNumberGroup`, `transactiontypeId`, `paymentId`, `currencyId`, `isVATRegistrated`, `isCashAccounting`, `isKATA`, `isNaturalPerson`) VALUES ( ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, shorName);
            pst.setString(2, fullName);
            pst.setString(3, voucherName);
            if (active) {
                pst.setInt(4, 1);
            } else {
                pst.setInt(4, 0);
            }
            pst.setString(5, VATNumber);
            pst.setString(6, VATNumberEU);
            pst.setString(7, VATNumberGroup);
            pst.setInt(8, transactionTypeid);
            pst.setInt(9, paymentid);
            pst.setString(10, currencyid);
            if (vatRegistrated) {
                pst.setInt(11, 1);
            } else {
                pst.setInt(11, 0);
            }
            if (cashAccounting) {
                pst.setInt(12, 1);
            } else {
                pst.setInt(12, 0);
            }
            if (kata) {
                pst.setInt(13, 1);
            } else {
                pst.setInt(13, 0);
            }
            if (naturalPerson) {
                pst.setInt(14, 1);
            } else {
                pst.setInt(14, 0);
            }
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A partner hozzáadása sikeres!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

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

    public void newPartnerAddress(JFrame view, String countryId, String regio, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, String building, String stairWay, String storey, int doornumber, String district, boolean postInherited, String postcountryId, int postzipid, String postaddress, String postregio) {
        int isPartner = getLastPartner();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `partneraddress`(`isPartner`,  `departmentId`, `countryId`, `regio`, `zipId`, `publicPlaceName`, `publicPlaceId`, `publicPlaceNumber`, `building`, `stairWay`, `storey`, `doornumber`, `district`, `isPostInherited`, `postcountryId`, `postzipId`, `postaddress`, `postregio`) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, isPartner);
            pst.setInt(2, 0);
            pst.setString(3, countryId);
            pst.setString(4, regio);
            pst.setInt(5, zipid);
            pst.setString(6, publicPlaceName);
            pst.setInt(7, publicplaceId);
            pst.setInt(8, publicPlaceNumber);
            pst.setString(9, building);
            pst.setString(10, stairWay);
            pst.setString(11, storey);
            pst.setInt(12, doornumber);
            pst.setString(13, district);

            if (postInherited) {
                pst.setInt(14, 1);
            } else {
                pst.setInt(14, 0);
            }
            pst.setString(15, postcountryId);
            pst.setInt(16, postzipid);
            pst.setString(17, postaddress);
            pst.setString(18, postregio);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A partner cím rögzítése sikeres!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public void newDepartment(JFrame view, String name) {
        int isPartner = getLastPartner();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `department`(`name`, `partnerId`) VALUES (? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, isPartner);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A partner új telephelyének hozzáadása sikeres!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

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

    public int getLastDepartment() {
        int id = 0;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `department` order by id DESC limit 1";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {

                    id = rs.getInt(1);
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

    public void newDepartmentAddess(JFrame view, String countryId, String region, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, String building, String stairWayDiv, String storey, int doornumber, String district, String postcountryId, int postzipid, String postaddress, String postregion) {
        int departmentId = getLastDepartment();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `partneraddress`(`isPartner`, `departmentId`,  `countryId`, `regio`, `zipId`, `publicPlaceName`, `publicPlaceId`, `publicPlaceNumber`, `building`, `stairWay`, `storey`, `doornumber`, `district`,`isPostInherited`, `postcountryId`, `postzipId`, `postaddress`, `postregio`) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setInt(2, departmentId);
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
                JOptionPane.showMessageDialog(view, "A telephely címének rögzítése sikeres!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public ArrayList<PartnerAddress> gePartnerAddress(int id) {
        ArrayList<PartnerAddress> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `partneraddress` WHERE isPartner = ?";
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
                    list.add(new PartnerAddress(rs.getInt("id"), rs.getBoolean("isPartner"), rs.getInt("departmentId"), address, rs.getBoolean("isPostInherited"), addresspost));

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

    public PartnerAddress gePartnerAddressToXML(int id) {
        PartnerAddress list = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `partneraddress` WHERE isPartner = ?";
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
                    list = new PartnerAddress(rs.getInt("id"), rs.getBoolean("isPartner"), rs.getInt("departmentId"), address, rs.getBoolean("isPostInherited"), addresspost);

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

    public ArrayList<PartnerAddress> gePartnerAddressByDepartment(int id) {
        ArrayList<PartnerAddress> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `partneraddress` WHERE departmentId = ?";
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
                    list.add(new PartnerAddress(rs.getInt("id"), rs.getBoolean("isPartner"), rs.getInt("departmentId"), address, rs.getBoolean("isPostInherited"), addresspost));

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

    public void getAllDepartbentByPartner(JTable table, int id) {
        ArrayList<PartnerAddress> addressDepartment = new ArrayList<>();
        ArrayList<Department> department = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `department` INNER JOIN partneraddress ON department.id=partneraddress.departmentId WHERE department.partnerId = ? ";
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
                    addressDepartment.add(new PartnerAddress(rs.getInt("id"), rs.getBoolean("isPartner"), rs.getInt("departmentId"), address, rs.getBoolean("isPostInherited"), addresspost));
                    department.add(new Department(rs.getInt(1), rs.getString("name"), rs.getInt(3)));
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
                for (int i = 0; i < addressDepartment.size(); i++) {
                    row[0] = department.get(i).getId();
                    row[1] = department.get(i).getName();
                    row[2] = String.format("%s, %s %s, %s %s %d", addressDepartment.get(i).getAddress().getCountry().getName(), addressDepartment.get(i).getAddress().getZip().getCode(), addressDepartment.get(i).getAddress().getZip().getName(), addressDepartment.get(i).getAddress().getPublicPlaceName(), addressDepartment.get(i).getAddress().getPublicPlaceKind().getName(), addressDepartment.get(i).getAddress().getPublicPlaceNumber());

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

    public ArrayList<PartnerAddress> getAllDepartbentByPartner(int id) {
        ArrayList<PartnerAddress> addressDepartment = new ArrayList<>();

        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "SELECT * FROM `department` INNER JOIN partneraddress ON department.id=partneraddress.departmentId WHERE department.partnerId = ? ";
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
                    addressDepartment.add(new PartnerAddress(rs.getInt("id"), rs.getBoolean("isPartner"), rs.getInt("departmentId"), address, rs.getBoolean("isPostInherited"), addresspost));

                }
                return addressDepartment;

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
        return addressDepartment;
    }

    public void updatePartner(JFrame view, String shorName, String fullName, String voucherName, String VATNumber, String VATNumberEU, String VATNumberGroup, boolean active, int transactionTypeid, int paymentid, String currencyid, boolean vatRegistrated, boolean cashAccounting, boolean kata, boolean naturalPerson, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `partner` SET `shortName`= ? ,`fullName`= ? ,`nameOnVoucher`= ? ,`isActive`= ?,`VATNumber`= ? ,`VATNumberEU`= ? ,`VATNumberGroup`= ? ,`transactiontypeId`= ? ,`paymentId`= ? ,`currencyId`= ? ,`isVATRegistrated`= ? ,`isCashAccounting`= ? ,`isKATA`= ? ,`isNaturalPerson`=?  WHERE id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, shorName);
            pst.setString(2, fullName);
            pst.setString(3, voucherName);
            if (active) {
                pst.setInt(4, 1);
            } else {
                pst.setInt(4, 0);
            }
            pst.setString(5, VATNumber);
            pst.setString(6, VATNumberEU);
            pst.setString(7, VATNumberGroup);
            pst.setInt(8, transactionTypeid);
            pst.setInt(9, paymentid);
            pst.setString(10, currencyid);
            if (vatRegistrated) {
                pst.setInt(11, 1);
            } else {
                pst.setInt(11, 0);
            }
            if (cashAccounting) {
                pst.setInt(12, 1);
            } else {
                pst.setInt(12, 0);
            }
            if (kata) {
                pst.setInt(13, 1);
            } else {
                pst.setInt(13, 0);
            }
            if (naturalPerson) {
                pst.setInt(14, 1);
            } else {
                pst.setInt(14, 0);
            }
            pst.setInt(15, id);
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A partner módosítása sikeres!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public void updatePartnerAddress(JFrame view, String countryId, String regio, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, String building, String stairWay, String storey, int doornumber, String district, boolean postInherited, String postcountryId, int postzipid, String postaddress, String postregio, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `partneraddress` SET `departmentId`= ? ,`countryId`= ? ,`regio`= ? ,`zipId`= ? ,`publicPlaceName`= ? ,`publicPlaceId`= ? ,`publicPlaceNumber`= ? ,`building`= ? ,`stairWay`= ?,`storey`= ? ,`doornumber`= ?,`district`= ?,`isPostInherited`= ? ,`postcountryId`= ? ,`postzipId`= ? ,`postaddress`= ?,`postregio`= ? WHERE isPartner= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, 0);
            pst.setString(2, countryId);
            pst.setString(3, regio);
            pst.setInt(4, zipid);
            pst.setString(5, publicPlaceName);
            pst.setInt(6, publicplaceId);
            pst.setInt(7, publicPlaceNumber);
            pst.setString(8, building);
            pst.setString(9, stairWay);
            pst.setString(10, storey);
            pst.setInt(11, doornumber);
            pst.setString(12, district);

            if (postInherited) {
                pst.setInt(13, 1);
            } else {
                pst.setInt(13, 0);
            }
            pst.setString(14, postcountryId);
            pst.setInt(15, postzipid);
            pst.setString(16, postaddress);
            pst.setString(17, postregio);
            pst.setInt(18, id);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A partner cím rögzítése sikeres!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public void newDepartmentByPartnerId(JFrame view, String name, int partnerid) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `department`(`name`, `partnerId`) VALUES (? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, partnerid);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A partner új telephelyének hozzáadása sikeres!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

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

    public void updateDepartment(JFrame view, String name, int depId) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `department` SET `name`= ?  WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, depId);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A telephely módosítása sikeres!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public void updateDepartmentAddess(JFrame view, String countryId, String region, int zipid, String publicPlaceName, int publicplaceId, int publicPlaceNumber, String building, String stairWay, String storey, int doornumber, String district, String postcountryId, int postzipid, String postaddress, String postregion, int depId) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `partneraddress` SET `countryId`= ? ,`regio`= ? ,`zipId`= ? ,`publicPlaceName`= ? ,`publicPlaceId`= ? ,`publicPlaceNumber`= ? ,`building`= ? ,`stairWay`= ?,`storey`= ? ,`doornumber`= ?,`district`= ?,`postcountryId`= ? ,`postzipId`= ? ,`postaddress`= ?,`postregio`= ? WHERE departmentId= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, countryId);
            pst.setString(2, region);
            pst.setInt(3, zipid);
            pst.setString(4, publicPlaceName);
            pst.setInt(5, publicplaceId);
            pst.setInt(6, publicPlaceNumber);
            pst.setString(7, building);
            pst.setString(8, stairWay);
            pst.setString(9, storey);
            pst.setInt(10, doornumber);
            pst.setString(11, district);
            pst.setString(12, postcountryId);
            pst.setInt(13, postzipid);
            pst.setString(14, postaddress);
            pst.setString(15, postregion);
            pst.setInt(16, depId);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A partner cím rögzítése sikeres!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

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

    public void deleteDepartment(JFrame view, int value) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "DELETE FROM `department` WHERE id = ?";
            String sql2 = "DELETE FROM `partneraddress` WHERE departmentId = ?";
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

    public void deletePartner(JFrame view, int value) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql0 = "SELECT partneraddress.id FROM partner Inner JOIN department ON partner.id=department.partnerId Inner JOIN partneraddress on department.id=partneraddress.departmentId where partner.id= ? ";
            PreparedStatement pst0 = conn.prepareStatement(sql0);
            pst0.setInt(1, value);
            ResultSet rs0 = pst0.executeQuery();

            while (rs0.next()) {
                String sql = "DELETE FROM `partneraddress` WHERE id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, rs0.getInt(1));
                int rs = pst.executeUpdate();

            }

            String sql1 = "SELECT id FROM `department` WHERE partnerId = ?";
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setInt(1, value);
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                String sql = "DELETE FROM `department` WHERE id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, rs1.getInt(1));
                int rs = pst.executeUpdate();

            }

            String sql2 = "DELETE FROM `partner` WHERE id = ?";
            PreparedStatement pst2 = conn.prepareStatement(sql2);
            pst2.setInt(1, value);
            int rs2 = pst2.executeUpdate();
            String sql3 = "DELETE FROM `partneraddress` WHERE isPartner = ?";
            PreparedStatement pst3 = conn.prepareStatement(sql3);
            pst2.setInt(1, value);
            int rs3 = pst3.executeUpdate();

            if (rs2 == 1) {
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

    public Partner getOnePartnerById(int id) {
        Partner partner = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From partner where id= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServicePayment payment = new ServicePayment();
                    Payment paymentid = payment.getOnePaymentById(rs.getInt("paymentId"));
                    ServiceCurrency currency = new ServiceCurrency();
                    Currency currencyId = currency.getOneCurrencyById(rs.getString("currencyId"));
                    partner = new Partner(rs.getInt("id"), rs.getString("shortName"), rs.getString("fullName"), rs.getString("nameOnVoucher"), rs.getBoolean("isActive"), rs.getString("VATNumber"), rs.getString("VATNumberEU"), rs.getString("VATNumberGroup"), transactiontype, paymentid, currencyId, rs.getBoolean("isVATRegistrated"), rs.getBoolean("isCashAccounting"), rs.getBoolean("isKATA"), rs.getBoolean("isNaturalPerson"));

                }

                return partner;

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
        return partner;
    }
}
