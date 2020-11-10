package InvoiceProgram.Service;

import InvoiceProgram.Model.BankAccount;
import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.InvoiceFileBankAccount;
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

public class ServiceInvoiceFileBankAccount {

    private ArrayList<InvoiceFileBankAccount> invoiceFileBankAccount;

    public void getAllInvoiceFileBankAccount(JTable table, int id) {
        invoiceFileBankAccount = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From invoicefilebankaccount  where invoicefileId= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceInvoiceFile invoiceFile = new ServiceInvoiceFile();
                    InvoiceFile invoiceFileid = invoiceFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));

                    ServiceBankAccount bankaccount = new ServiceBankAccount();
                    BankAccount bankaccountId = bankaccount.getOneBankAccountById(rs.getInt("bankaccountId"));

                    invoiceFileBankAccount.add(new InvoiceFileBankAccount(rs.getInt("id"), invoiceFileid, bankaccountId));

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.getDataVector().removeAllElements();
                    table.revalidate();

                    Object[] row = new Object[2];
                    for (int i = 0; i < invoiceFileBankAccount.size(); i++) {
                        row[0] = invoiceFileBankAccount.get(i).getId();
                        row[1] = invoiceFileBankAccount.get(i).getBankAccount().getName();

                        model.addRow(row);
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
        }
    }

    public ArrayList<InvoiceFileBankAccount> getAllInvoiceFileBankAccount(int id) {
        ArrayList<InvoiceFileBankAccount> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "Select * From invoicefilebankaccount  where invoicefileId= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            try {
                while (rs.next()) {
                    ServiceInvoiceFile invoiceFile = new ServiceInvoiceFile();
                    InvoiceFile invoiceFileid = invoiceFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));

                    ServiceBankAccount bankaccount = new ServiceBankAccount();
                    BankAccount bankaccountId = bankaccount.getOneBankAccountById(rs.getInt("bankaccountId"));

                    list.add(new InvoiceFileBankAccount(rs.getInt("id"), invoiceFileid, bankaccountId));

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

    public void newInvoiceFileBankAccount(JFrame view, int invoicefileId, int bankaccountId) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "INSERT INTO `invoicefilebankaccount`(`invoicefileId`, `bankaccountId`) VALUES (?, ? )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, invoicefileId);
            pst.setInt(2, bankaccountId);

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

    public void updateInvoiceFileBankAccount(JFrame view, int invoicefileId, int bankaccountId, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "UPDATE `invoicefilebankaccount` SET `invoicefileId`= ? ,`bankaccountId`= ?  WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, invoicefileId);
            pst.setInt(2, bankaccountId);
            pst.setInt(3, id);

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

    public void deleteInvoiceFileBankAccount(JFrame view, int id) {
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {

            conn = db.getConnection();
            String sql = "DELETE FROM `invoicefilebankaccount`  WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres eltávolítás!", "Adat eltávolítás", JOptionPane.INFORMATION_MESSAGE);

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
