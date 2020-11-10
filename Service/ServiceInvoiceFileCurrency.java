package InvoiceProgram.Service;

import InvoiceProgram.Model.BankAccount;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.InvoiceFileCurrency;
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

public class ServiceInvoiceFileCurrency {

    private ArrayList<InvoiceFileCurrency> invoiceFileCurrency;

    public ArrayList<InvoiceFileCurrency> getAllInvoiceFileCurrency() {
        ArrayList<InvoiceFileCurrency> invoiceFileCurrency = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From invoicefilecurrency ";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceInvoiceFile invoiceFile = new ServiceInvoiceFile();
                    InvoiceFile invoiceFileid = invoiceFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));
                    ServiceCurrency currency = new ServiceCurrency();
                    Currency currencyId = currency.getOneCurrencyById(rs.getString("currencyId"));
                    ServiceBankAccount bankaccount = new ServiceBankAccount();
                    BankAccount bankaccountId = bankaccount.getOneBankAccountById(rs.getInt("bankaccountId"));

                    invoiceFileCurrency.add(new InvoiceFileCurrency(rs.getInt("id"), invoiceFileid, currencyId, bankaccountId, rs.getBoolean("isDefault"), rs.getBoolean("rounditem"), rs.getBoolean("roundvat"), rs.getBoolean("roundtotal")));

                }

                return invoiceFileCurrency;
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
        return invoiceFileCurrency;
    }

    public ArrayList<InvoiceFileCurrency> getAllInvoiceFileCurrency(int id) {
        ArrayList<InvoiceFileCurrency> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From invoicefilecurrency  where invoicefileId= ? ";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceInvoiceFile invoiceFile = new ServiceInvoiceFile();
                    InvoiceFile invoiceFileid = invoiceFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));
                    ServiceCurrency currency = new ServiceCurrency();
                    Currency currencyId = currency.getOneCurrencyById(rs.getString("currencyId"));
                    ServiceBankAccount bankaccount = new ServiceBankAccount();
                    BankAccount bankaccountId = bankaccount.getOneBankAccountById(rs.getInt("bankaccountId"));

                    list.add(new InvoiceFileCurrency(rs.getInt("id"), invoiceFileid, currencyId, bankaccountId, rs.getBoolean("isDefault"), rs.getBoolean("rounditem"), rs.getBoolean("roundvat"), rs.getBoolean("roundtotal")));

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

    public void getAllInvoiceFileCurrency(JTable table, int id) {
        invoiceFileCurrency = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From invoicefilecurrency  where invoicefileId= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceInvoiceFile invoiceFile = new ServiceInvoiceFile();
                    InvoiceFile invoiceFileid = invoiceFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));
                    ServiceCurrency currency = new ServiceCurrency();
                    Currency currencyId = currency.getOneCurrencyById(rs.getString("currencyId"));
                    ServiceBankAccount bankaccount = new ServiceBankAccount();
                    BankAccount bankaccountId = bankaccount.getOneBankAccountById(rs.getInt("bankaccountId"));

                    invoiceFileCurrency.add(new InvoiceFileCurrency(rs.getInt("id"), invoiceFileid, currencyId, bankaccountId, rs.getBoolean("isDefault"), rs.getBoolean("rounditem"), rs.getBoolean("roundvat"), rs.getBoolean("roundtotal")));
                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[7];
                for (int i = 0; i < invoiceFileCurrency.size(); i++) {
                    row[0] = invoiceFileCurrency.get(i).getId();
                    row[1] = invoiceFileCurrency.get(i).getCurrency().getId().toUpperCase();
                    row[2] = invoiceFileCurrency.get(i).getBankAccount().getName() + ":" + invoiceFileCurrency.get(i).getBankAccount().getNumber();
                    row[3] = invoiceFileCurrency.get(i).isIsDefault();
                    row[4] = invoiceFileCurrency.get(i).isRoundItem();
                    row[5] = invoiceFileCurrency.get(i).isRoundVAT();
                    row[6] = invoiceFileCurrency.get(i).isRoundTotal();

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

    public void newInvoiceFileCurrency(JFrame view, int invoicefileId, String currencyId, int bankaccountId, boolean isDefault, boolean rounditem, boolean roundvat, boolean roundtotal) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `invoicefilecurrency`(`invoicefileId`, `currencyId`, `bankaccountId`, `isDefault`, `rounditem`, `roundvat`, `roundtotal`) VALUES (?, ? , ? , ? , ? , ? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, invoicefileId);
            pst.setString(2, currencyId);
            pst.setInt(3, bankaccountId);
            if (isDefault) {
                pst.setInt(4, 1);
            } else {
                pst.setInt(4, 0);
            }
            if (rounditem) {
                pst.setInt(5, 1);
            } else {
                pst.setInt(5, 0);
            }
            if (roundvat) {
                pst.setInt(6, 1);
            } else {
                pst.setInt(6, 0);
            }
            if (roundtotal) {
                pst.setInt(7, 1);
            } else {
                pst.setInt(7, 0);
            }

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

    public void updateInvoiceFileCurrency(JFrame view, int invoicefileId, String currencyId, int bankaccountId, boolean isDefault, boolean rounditem, boolean roundvat, boolean roundtotal, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `invoicefilecurrency` SET `invoicefileId`= ? ,`currencyId`= ? ,`bankaccountId`= ? ,`isDefault`= ? ,`rounditem`= ? ,`roundvat`=? ,`roundtotal`= ?  WHERE id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, invoicefileId);
            pst.setString(2, currencyId);
            pst.setInt(3, bankaccountId);
            if (isDefault) {
                pst.setInt(4, 1);
            } else {
                pst.setInt(4, 0);
            }
            if (rounditem) {
                pst.setInt(5, 1);
            } else {
                pst.setInt(5, 0);
            }
            if (roundvat) {
                pst.setInt(6, 1);
            } else {
                pst.setInt(6, 0);
            }
            if (roundtotal) {
                pst.setInt(7, 1);
            } else {
                pst.setInt(7, 0);
            }
            pst.setInt(8, id);
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

    public void deleteInvoiceFileCurrency(JFrame view, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "DELETE FROM `invoicefilecurrency` WHERE id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

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
}
