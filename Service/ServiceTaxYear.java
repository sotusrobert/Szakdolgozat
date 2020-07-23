package InvoiceProgram.Service;

import InvoiceProgram.Model.TaxYear;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ServiceTaxYear {

    private ArrayList<TaxYear> taxYears;

    public void getAllTaxYear(JTable table) {
        taxYears = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "Select * From taxyear ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {

                    taxYears.add(new TaxYear(rs.getInt("id"), rs.getInt("parentTaxYearId"), rs.getString("name"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getString("currencyId"), rs.getInt("taxYearNumber"), rs.getBoolean("isKATA"), rs.getBoolean("isClosed"), rs.getBoolean("isCashAccounting"), rs.getString("voucherFormat"), rs.getInt("voucherDigits"), rs.getString("voucherSepFirst"), rs.getString("voucherSepLast"), rs.getDate("closeDate")));

                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[6];
                for (int i = 0; i < taxYears.size(); i++) {
                    row[0] = taxYears.get(i).getId();
                    row[1] = taxYears.get(i).getName().toUpperCase();
                    row[2] = taxYears.get(i).getStartDate();
                    row[3] = taxYears.get(i).getEndDate();
                    row[4] = taxYears.get(i).isIsKATA();
                    row[5] = taxYears.get(i).isIsClosed();

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

    public void getAllTaxYearToComboBox(JComboBox box) {
        ArrayList<TaxYear> taxYearsToCombo = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "SELECT * FROM `taxyear` where id!=(SELECT max(id) FROM taxyear) ORDER BY id desc";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {

                    taxYearsToCombo.add(new TaxYear(rs.getInt("id"), rs.getInt("parentTaxYearId"), rs.getString("name"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getString("currencyId"), rs.getInt("taxYearNumber"), rs.getBoolean("isKATA"), rs.getBoolean("isClosed"), rs.getBoolean("isCashAccounting"), rs.getString("voucherFormat"), rs.getInt("voucherDigits"), rs.getString("voucherSepFirst"), rs.getString("voucherSepLast"), rs.getDate("closeDate")));

                }
                if (taxYearsToCombo.size() == 0) {
                    DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
                    box.removeAllItems();
                    box.setEnabled(false);
                } else {

                    DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
                    box.removeAllItems();
                    box.revalidate();
                    for (int i = 0; i < taxYearsToCombo.size(); i++) {
                        box.addItem(taxYearsToCombo.get(i));
                    }

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

    public void newTaxYear(JFrame view, int parentTaxYearId, String name, Date startDate, Date endDate, String currencyId, int taxYearNumber, boolean isKATA, boolean isClosed, boolean isCashAccounting, String voucherFormat, int voucherDigits, String voucherSepFirst, String voucherSepLast, Date closeDate) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "INSERT INTO `taxyear`( `parentTaxYearId`, `name`, `startDate`, `endDate`, `currencyId`, `taxYearNumber`, `isKATA`, `isClosed`, `isCashAccounting`, `voucherFormat`, `voucherDigits`, `voucherSepFirst`, `voucherSepLast`, `closeDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, parentTaxYearId);
            pst.setString(2, name);
            pst.setDate(3, (java.sql.Date) startDate);
            pst.setDate(4, (java.sql.Date) endDate);
            pst.setString(5, currencyId);
            pst.setInt(6, taxYearNumber);
            if (isKATA) {
                pst.setInt(7, 1);
            } else {
                pst.setInt(7, 1);
            }
            if (isClosed) {
                pst.setInt(8, 1);
            } else {
                pst.setInt(8, 1);
            }
            if (isCashAccounting) {
                pst.setInt(9, 1);
            } else {
                pst.setInt(9, 1);
            }
            pst.setString(10, voucherFormat);
            pst.setInt(11, voucherDigits);
            pst.setString(12, voucherSepFirst);
            pst.setString(13, voucherSepLast);
            pst.setDate(14, (java.sql.Date) closeDate);
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

    public void updateTaxYear(JFrame view, int parentTaxYearId, String name, Date startDate, Date endDate, String currencyId, int taxYearNumber, boolean isKATA, boolean isClosed, boolean isCashAccounting, String voucherFormat, int voucherDigits, String voucherSepFirst, String voucherSepLast, Date closeDate, int id) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "UPDATE `taxyear` SET `parentTaxYearId`= ? ,`name`= ? ,`startDate`= ? ,`endDate`= ? ,`currencyId`= ? ,`taxYearNumber`= ? ,`isKATA`= ? ,`isClosed`= ? ,`isCashAccounting`= ? ,`voucherFormat`= ? ,`voucherDigits`= ? ,`voucherSepFirst`= ? ,`voucherSepLast`= ? ,`closeDate`= ?  WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, parentTaxYearId);
            pst.setString(2, name);
            pst.setDate(3, (java.sql.Date) startDate);
            pst.setDate(4, (java.sql.Date) endDate);
            pst.setString(5, currencyId);
            pst.setInt(6, taxYearNumber);
            if (isKATA) {
                pst.setInt(7, 1);
            } else {
                pst.setInt(7, 1);
            }
            if (isClosed) {
                pst.setInt(8, 1);
            } else {
                pst.setInt(8, 1);
            }
            if (isCashAccounting) {
                pst.setInt(9, 1);
            } else {
                pst.setInt(9, 1);
            }
            pst.setString(10, voucherFormat);
            pst.setInt(11, voucherDigits);
            pst.setString(12, voucherSepFirst);
            pst.setString(13, voucherSepLast);
            pst.setDate(14, (java.sql.Date) closeDate);
            pst.setInt(15, id);
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

    public void deleteTaxYear(JFrame view, int id) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "DELETE FROM `taxyear` WHERE id = ?";
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

    public ArrayList<TaxYear> getOneTaxYear(JFrame view, int id) {
        ArrayList<TaxYear> list = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "SELECT * FROM `taxyear` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    list.add(new TaxYear(rs.getInt("id"), rs.getInt("parentTaxYearId"), rs.getString("name"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getString("currencyId"), rs.getInt("taxYearNumber"), rs.getBoolean("isKATA"), rs.getBoolean("isClosed"), rs.getBoolean("isCashAccounting"), rs.getString("voucherFormat"), rs.getInt("voucherDigits"), rs.getString("voucherSepFirst"), rs.getString("voucherSepLast"), rs.getDate("closeDate")));

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
}
