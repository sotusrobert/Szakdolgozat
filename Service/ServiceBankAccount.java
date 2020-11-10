package InvoiceProgram.Service;

import InvoiceProgram.Logger.InvoiceLog;
import InvoiceProgram.Model.Bank;
import InvoiceProgram.Model.BankAccount;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.View.BankAccountEdit;
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

public class ServiceBankAccount {

    private ArrayList<BankAccount> bankAccounts;
    private InvoiceLog log = new InvoiceLog();

    public void getAllBankAccount(JTable table) {
        bankAccounts = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "Select * From bankaccount ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceBank serviceBank = new ServiceBank();
                    Bank bank = serviceBank.getOneBank(rs.getInt("bankId"));
                    ServiceCurrency currencyService = new ServiceCurrency();
                    Currency currency = currencyService.getOneCurrencyById(rs.getString("currencyId"));

                    bankAccounts.add(new BankAccount(rs.getInt("id"), rs.getString("name"), currency, bank, rs.getString("number"), rs.getBoolean("isActive")));

                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();

                Object[] row = new Object[6];
                for (int i = 0; i < bankAccounts.size(); i++) {
                    row[0] = bankAccounts.get(i).getId();
                    row[1] = bankAccounts.get(i).getName().toUpperCase();
                    row[2] = bankAccounts.get(i).getBank().getName().toUpperCase();
                    row[3] = bankAccounts.get(i).getCurrency().getId();
                    row[4] = bankAccounts.get(i).getNumber();
                    row[5] = bankAccounts.get(i).isIsActive();

                    model.addRow(row);

                }

            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
                log.log(Level.SEVERE, ex.getMessage());
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
            log.log(Level.SEVERE, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        }
    }

    public ArrayList<BankAccount> getAllBankAccount() {
        ArrayList<BankAccount> list = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "Select * From bankaccount ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceBank serviceBank = new ServiceBank();
                    Bank bank = serviceBank.getOneBank(rs.getInt("bankId"));
                    ServiceCurrency currencyService = new ServiceCurrency();
                    Currency currency = currencyService.getOneCurrencyById(rs.getString("currencyId"));

                    list.add(new BankAccount(rs.getInt("id"), rs.getString("name"), currency, bank, rs.getString("number"), rs.getBoolean("isActive")));

                }
                return list;

            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
                log.log(Level.SEVERE, ex.getMessage());
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
            log.log(Level.SEVERE, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        }
        return list;
    }

    public void newBankAccount(JFrame view, String name, String currencyId, int bankid, String number, boolean active) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "INSERT INTO `bankaccount`( `name`, `currencyId`, `bankId`, `number`, `isActive`) VALUES ( ? , ? , ? , ? ,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, currencyId);
            pst.setInt(3, bankid);
            pst.setString(4, number);
            if (active) {
                pst.setInt(5, 1);
            } else {
                pst.setInt(5, 0);
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
            log.log(Level.SEVERE, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.log(Level.SEVERE, e.getMessage());
                }
            }

        }
    }

    public ArrayList<BankAccount> getOneBankAccount(JFrame view, int id) {
        ArrayList<BankAccount> list = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "SELECT * FROM `bankaccount` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceBank serviceBank = new ServiceBank();
                    Bank bank = serviceBank.getOneBank(rs.getInt("bankId"));
                    ServiceCurrency currencyService = new ServiceCurrency();
                    Currency currency = currencyService.getOneCurrencyById(rs.getString("currencyId"));

                    list.add(new BankAccount(rs.getInt("id"), rs.getString("name"), currency, bank, rs.getString("number"), rs.getBoolean("isActive")));

                }

                return list;

            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
                log.log(Level.SEVERE, ex.getMessage());
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
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        }
        return list;
    }

    public BankAccount getOneBankAccountById(int id) {
        BankAccount list = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "SELECT * FROM `bankaccount` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceBank serviceBank = new ServiceBank();
                    Bank bank = serviceBank.getOneBank(rs.getInt("bankId"));
                    ServiceCurrency currencyService = new ServiceCurrency();
                    Currency currency = currencyService.getOneCurrencyById(rs.getString("currencyId"));

                    list = new BankAccount(rs.getInt("id"), rs.getString("name"), currency, bank, rs.getString("number"), rs.getBoolean("isActive"));

                }

                return list;

            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
                log.log(Level.SEVERE, ex.getMessage());
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
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        }
        return list;
    }

    public void updateBankAccount(BankAccountEdit view, String name, String currencyId, int bankid, String number, boolean active, int id) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "UPDATE `bankaccount` SET `name`= ? ,`currencyId`= ? ,`bankId`= ? ,`number`= ? ,`isActive`=?  WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, currencyId);
            pst.setInt(3, bankid);
            pst.setString(4, number);
            if (active) {
                pst.setInt(5, 1);
            } else {
                pst.setInt(5, 0);
            }
            pst.setInt(6, id);
            int rs = pst.executeUpdate();

            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok rögzítése nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
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

    public void deleteBankAccount(JFrame view, int id) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "DELETE FROM `bankaccount` WHERE id = ?";
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
            log.log(Level.SEVERE, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
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
    
    public boolean isValidAccountNumber(String code) {
        String regex = "^[0-9]{8}([ -]?[0-9]{8}){1,2}$";
        Pattern p = Pattern.compile(regex);

        if (code == null) {
            return false;
        }
        Matcher m = p.matcher(code);

        return m.matches();

    }
}
