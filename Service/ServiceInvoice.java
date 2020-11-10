package InvoiceProgram.Service;

import InvoiceProgram.Model.BankAccount;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.Invoice;
import InvoiceProgram.Model.InvoiceAddress;
import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.InvoiceItem;
import InvoiceProgram.Model.InvoiceLink;
import InvoiceProgram.Model.Language;
import InvoiceProgram.Model.Partner;
import InvoiceProgram.Model.Payment;
import InvoiceProgram.Model.Tax;
import InvoiceProgram.Model.TransactionType;
import java.awt.Component;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ServiceInvoice {

    private ArrayList<InvoiceAddress> invoiceAddress;
    private ArrayList<InvoiceLink> invoiceLink;

    public int getLastInvoiceNumber(int id) {
        int lastInvoiceNumber = 0;
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "SELECT counter FROM `invoicenumbercounter` WHERE invoicefileId= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {

                    lastInvoiceNumber = rs.getInt("counter");

                }
                return lastInvoiceNumber;

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
        return lastInvoiceNumber;
    }

    public void newInvoice(JFrame view, int invoiceFileId, int bankAccountId, String currencyId, String languageId, Double currencyRate, int transactionTypeId, String voucherNumber, int paymentId, Date invoiceDate, Date fulfilmentDate, Date dueDate, int roundingValue, String headNote, int cancellationState, double amount, double taxAmount, double total) {
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "INSERT INTO `invoice`(`invoicefileId`, `bankaccountId`, `currencyId`, `languageId`, `currencyrate`, `transactiontypeId`, `vouchernumber`, `paymentId`, `invoicedate`, `fulfilmentdate`, `duedate`, `roundingvalue`, `headnote`, `cancellationstate`, `amount`, `tax`, `total`) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, invoiceFileId);
            pst.setInt(2, bankAccountId);
            pst.setString(3, currencyId);
            pst.setString(4, languageId);
            pst.setDouble(5, currencyRate);
            pst.setInt(6, transactionTypeId);
            pst.setString(7, voucherNumber);
            pst.setInt(8, paymentId);
            pst.setDate(9, (java.sql.Date) invoiceDate);
            pst.setDate(10, (java.sql.Date) fulfilmentDate);
            pst.setDate(11, (java.sql.Date) dueDate);
            pst.setInt(12, roundingValue);
            pst.setString(13, headNote);
            pst.setInt(14, cancellationState);
            pst.setDouble(15, amount);
            pst.setDouble(16, taxAmount);
            pst.setDouble(17, total);

            int rs = pst.executeUpdate();
            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A rekord hozzáadása sikeres!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

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

    public int getLastInvoiceId() {
        int invoiceId = 0;
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "SELECT id FROM `invoice` WHERE id=(SELECT MAX(id) FROM invoice)";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                invoiceId = rs.getInt("id");

            }

            return invoiceId;

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
        return invoiceId;
    }

    public void newInvoiceAddress(JFrame view, int invoiceId, int partnerId, int departmentId) {
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "INSERT INTO `invoiceaddress`(`invoiceId`, `partnerId`, `departmentId`) VALUES (? , ? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, invoiceId);
            pst.setInt(2, partnerId);
            pst.setInt(3, departmentId);

            int rs = pst.executeUpdate();
            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A rekord hozzáadása sikeres!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);

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

    public void newInvoiceItem(int invoiceId, int itemNumber, int vatId, String descriptionDefault, String measureDefault, int quantity, double unitPrice, double amount, double taxItem, double totalItem, double currencyUnitPrice, double currencyAmount, double currencyTax, double currencyTotal) {
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "INSERT INTO `invoiceitem`(`invoiceId`, `itemnumber`, `taxid`, `descriptiondefault`, `measuredefault`, `quantity`, `unitprice`, `amount`, `tax`, `total`, `currencyunitprice`, `currencyamount`, `currencytax`, `currencytotal`) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, invoiceId);
            pst.setInt(2, itemNumber);
            pst.setInt(3, vatId);
            pst.setString(4, descriptionDefault);
            pst.setString(5, measureDefault);
            pst.setInt(6, quantity);
            pst.setDouble(7, unitPrice);
            pst.setDouble(8, amount);
            pst.setDouble(9, taxItem);
            pst.setDouble(10, totalItem);
            pst.setDouble(11, currencyUnitPrice);
            pst.setDouble(12, currencyAmount);
            pst.setDouble(13, currencyTax);
            pst.setDouble(14, currencyTotal);

            int rs = pst.executeUpdate();
            if (rs == 1) {

                conn.close();

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

    public Invoice getLastInvoice() {
        Invoice invoice = null;
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "SELECT * FROM `invoice` WHERE id=(SELECT MAX(id) FROM invoice)";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ServiceInvoiceFile invFile = new ServiceInvoiceFile();
                InvoiceFile invoiceFile = invFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));
                ServiceBankAccount bankAcc = new ServiceBankAccount();
                BankAccount bankAccount = bankAcc.getOneBankAccountById(rs.getInt("bankaccountId"));
                ServiceCurrency curr = new ServiceCurrency();
                Currency currency = curr.getOneCurrencyById(rs.getString("currencyId"));
                ServiceLanguage lang = new ServiceLanguage();
                Language language = lang.getOneLanguageById(rs.getString("languageId"));
                ServiceTransactionType transaction = new ServiceTransactionType();
                TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                ServicePayment paym = new ServicePayment();
                Payment payment = paym.getOnePaymentById(rs.getInt("paymentId"));

                invoice = new Invoice(rs.getInt("id"), invoiceFile, bankAccount, currency, language, rs.getDouble("currencyrate"), transactiontype, rs.getString("vouchernumber"), payment, rs.getDate("invoicedate"), rs.getDate("fulfilmentdate"), rs.getDate("duedate"), rs.getInt("roundingvalue"), rs.getString("headnote"), rs.getInt("cancellationstate"), rs.getDouble("amount"), rs.getDouble("tax"), rs.getDouble("total"));

            }

            return invoice;

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
        return invoice;
    }

    public void updateInvoiceNumber(JFrame view, int counter, int id) {
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "UPDATE `invoicenumbercounter` SET `counter`= ?   WHERE invoicefileId= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, counter);
            pst.setInt(2, id);
            int rs = pst.executeUpdate();

            if (rs == 1) {
                //JOptionPane.showMessageDialog(view, "Sikeres módosítás!", "Adat módosítás", JOptionPane.INFORMATION_MESSAGE);

                conn.close();

            } else {
                JOptionPane.showMessageDialog(view, "Az adatok módosítása nem sikerült!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getAllInvoice(JTable table) {

        invoiceAddress = new ArrayList<>();
        invoiceLink = new ArrayList<>();
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "SELECT * FROM `invoice` Inner JOIN invoiceaddress ON invoice.id=invoiceaddress.invoiceId INNER JOIN partner on invoiceaddress.partnerId=partner.id ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceInvoiceFile invFile = new ServiceInvoiceFile();
                    InvoiceFile invoiceFile = invFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));
                    ServiceBankAccount bankAcc = new ServiceBankAccount();
                    BankAccount bankAccount = bankAcc.getOneBankAccountById(rs.getInt("bankaccountId"));
                    ServiceCurrency curr = new ServiceCurrency();
                    Currency currency = curr.getOneCurrencyById(rs.getString("currencyId"));
                    ServiceLanguage lang = new ServiceLanguage();
                    Language language = lang.getOneLanguageById(rs.getString("languageId"));
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServicePayment paym = new ServicePayment();
                    Payment payment = paym.getOnePaymentById(rs.getInt("paymentId"));

                    Invoice invoice = new Invoice(rs.getInt(1), invoiceFile, bankAccount, currency, language, rs.getDouble("currencyrate"), transactiontype, rs.getString("vouchernumber"), payment, rs.getDate("invoicedate"), rs.getDate("fulfilmentdate"), rs.getDate("duedate"), rs.getInt("roundingvalue"), rs.getString("headnote"), rs.getInt("cancellationstate"), rs.getDouble("amount"), rs.getDouble("tax"), rs.getDouble("total"));
                    ServicePartner p = new ServicePartner();
                    Partner partner = p.getOnePartnerById(rs.getInt("partnerId"));
                    invoiceAddress.add(new InvoiceAddress(rs.getInt(20), invoice, partner));

                }

                String sql2 = "SELECT * FROM `invoicelinkinvoice` ";
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery();
                try {
                    while (rs2.next()) {
                        Invoice from = getOneInvoice(rs2.getInt("fromInvoiceId"));
                        Invoice to= getOneInvoice(rs2.getInt("toInvoiceId"));
                        
                        invoiceLink.add(new InvoiceLink(from, to, rs2.getString("mode")));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
                } 

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.getDataVector().removeAllElements();
                    table.revalidate();

                    Object[] row = new Object[7];
                    for (int i = 0; i < invoiceAddress.size(); i++) {
                        row[0] = invoiceAddress.get(i).getId();
                        row[1] = invoiceAddress.get(i).getPartner().getNameOnVoucher().toUpperCase();
                        row[2] = invoiceAddress.get(i).getInvoice().getVoucherNumber();
                        row[3] = invoiceAddress.get(i).getInvoice().getCurrency().getId();
                        row[4] = invoiceAddress.get(i).getInvoice().getTotal();
                        int original= invoiceAddress.get(i).getInvoice().getId();
                        for (int j = 0; j < invoiceLink.size(); j++){
                            int from= invoiceLink.get(j).getFromInvoice().getId();
                            if (original == from) {
                                
                                 row[5] = invoiceLink.get(j).getToInvoice().getVoucherNumber();
                            }
                            else{
                                row[5] = "";
                            }
                        
                        }
                        
                        if (invoiceAddress.get(i).getInvoice().getCancellationState() == 0) {
                            row[6] = true;
                        } else {
                            row[6] = false;
                        }

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

    

    public Invoice getOneInvoice(int id) {

        Invoice invoice = null;
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "SELECT * FROM `invoice` WHERE id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceInvoiceFile invFile = new ServiceInvoiceFile();
                    InvoiceFile invoiceFile = invFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));
                    ServiceBankAccount bankAcc = new ServiceBankAccount();
                    BankAccount bankAccount = bankAcc.getOneBankAccountById(rs.getInt("bankaccountId"));
                    ServiceCurrency curr = new ServiceCurrency();
                    Currency currency = curr.getOneCurrencyById(rs.getString("currencyId"));
                    ServiceLanguage lang = new ServiceLanguage();
                    Language language = lang.getOneLanguageById(rs.getString("languageId"));
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServicePayment paym = new ServicePayment();
                    Payment payment = paym.getOnePaymentById(rs.getInt("paymentId"));

                    invoice = new Invoice(rs.getInt(1), invoiceFile, bankAccount, currency, language, rs.getDouble("currencyrate"), transactiontype, rs.getString("vouchernumber"), payment, rs.getDate("invoicedate"), rs.getDate("fulfilmentdate"), rs.getDate("duedate"), rs.getInt("roundingvalue"), rs.getString("headnote"), rs.getInt("cancellationstate"), rs.getDouble("amount"), rs.getDouble("tax"), rs.getDouble("total"));

                }

                return invoice;

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
        return invoice;
    }

    public InvoiceAddress getOneInvoiceAddress(JFrame view, int id) {

        InvoiceAddress invoiceAddress = null;
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "SELECT * FROM `invoiceaddress` Inner JOIN invoice on invoiceaddress.invoiceId=invoice.id WHERE invoiceaddress.id= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceInvoiceFile invFile = new ServiceInvoiceFile();
                    InvoiceFile invoiceFile = invFile.getOneInvoiceFileById(rs.getInt("invoicefileId"));
                    ServiceBankAccount bankAcc = new ServiceBankAccount();
                    BankAccount bankAccount = bankAcc.getOneBankAccountById(rs.getInt("bankaccountId"));
                    ServiceCurrency curr = new ServiceCurrency();
                    Currency currency = curr.getOneCurrencyById(rs.getString("currencyId"));
                    ServiceLanguage lang = new ServiceLanguage();
                    Language language = lang.getOneLanguageById(rs.getString("languageId"));
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServicePayment paym = new ServicePayment();
                    Payment payment = paym.getOnePaymentById(rs.getInt("paymentId"));

                    Invoice invoice = new Invoice(rs.getInt(6), invoiceFile, bankAccount, currency, language, rs.getDouble("currencyrate"), transactiontype, rs.getString("vouchernumber"), payment, rs.getDate("invoicedate"), rs.getDate("fulfilmentdate"), rs.getDate("duedate"), rs.getInt("roundingvalue"), rs.getString("headnote"), rs.getInt("cancellationstate"), rs.getDouble("amount"), rs.getDouble("tax"), rs.getDouble("total"));
                    ServicePartner p = new ServicePartner();
                    Partner partner = p.getOnePartnerById(rs.getInt("partnerId"));
                    invoiceAddress = new InvoiceAddress(rs.getInt(1), invoice, partner);

                }

                return invoiceAddress;

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
        return invoiceAddress;
    }

    public void getAllInvoiceItemByInvoiceId(JTable table, int id) {
        ArrayList<InvoiceItem> invoiceItem = new ArrayList<>();

        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "SELECT * FROM `invoiceitem` WHERE invoiceId= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Invoice invoive = getOneInvoice(rs.getInt("invoiceId"));
                ServiceTax t = new ServiceTax();
                Tax tax = t.getOneTaxById(rs.getInt("taxid"));

                invoiceItem.add(new InvoiceItem(rs.getInt("id"), invoive, rs.getInt("itemnumber"), tax, rs.getString("descriptiondefault"), rs.getString("measuredefault"), rs.getInt("quantity"), rs.getDouble("unitprice"), rs.getDouble("amount"), rs.getDouble("tax"), rs.getDouble("total"), rs.getDouble("currencyunitprice"), rs.getDouble("currencyamount"), rs.getDouble("currencytax"), rs.getDouble("currencytotal")));

            }

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.getDataVector().removeAllElements();
            table.revalidate();

            Object[] row = new Object[7];
            for (int i = 0; i < invoiceItem.size(); i++) {
                row[0] = invoiceItem.get(i).getId();
                row[1] = invoiceItem.get(i).getDescriptionDefault();
                row[2] = invoiceItem.get(i).getQuantity();
                row[3] = invoiceItem.get(i).getMeasureDefault();
                row[4] = invoiceItem.get(i).getTaxType().getShortName();
                row[5] = invoiceItem.get(i).getUnitPrice();
                row[6] = invoiceItem.get(i).getTotal();

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

    }

    public void setUpComboBoxToJtable(JTable table, TableColumn col) {
        ServiceTax sc2 = new ServiceTax();
        ArrayList<Tax> list2 = new ArrayList<>();
        list2 = sc2.getAllTaxCode();
        JComboBox box2 = new JComboBox();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) box2.getModel();
        box2.removeAllItems();
        box2.revalidate();
        model2.addAll(list2);

        box2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Tax) {
                    Tax tax = (Tax) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(tax.getName());
                    setText(sb.toString());
                }
                return this;
            }

        });

        col.setCellEditor(new DefaultCellEditor(box2));

    }

    public void setSelectedTaxToComboBox(JComboBox box, int id) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Tax> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Tax) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == id) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void updateInvoice(JFrame view, int id) {
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "UPDATE `invoice` SET `cancellationstate`=1 WHERE invoice.id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            int rs = pst.executeUpdate();
            if (rs == 1) {

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

    public ArrayList<InvoiceItem> getAllInvoiceItemByoriginalInvoiceId(int id) {
        ArrayList<InvoiceItem> invoiceItem = new ArrayList<>();

        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "SELECT * FROM `invoiceitem` WHERE invoiceId= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Invoice invoive = getOneInvoice(rs.getInt("invoiceId"));
                ServiceTax t = new ServiceTax();
                Tax tax = t.getOneTaxById(rs.getInt("taxid"));

                invoiceItem.add(new InvoiceItem(rs.getInt("id"), invoive, rs.getInt("itemnumber"), tax, rs.getString("descriptiondefault"), rs.getString("measuredefault"), rs.getInt("quantity"), rs.getDouble("unitprice"), rs.getDouble("amount"), rs.getDouble("tax"), rs.getDouble("total"), rs.getDouble("currencyunitprice"), rs.getDouble("currencyamount"), rs.getDouble("currencytax"), rs.getDouble("currencytotal")));

            }

            return invoiceItem;

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
        return invoiceItem;
    }

    public void newInvoiceLink(JFrame view, int invoiceFromId, int invoiceToId, String mode) {
        Connection conn = null;        DatabaseConnection db= new DatabaseConnection();
        try {
            

            conn=db.getConnection();
            String sql = "INSERT INTO `invoicelinkinvoice`(`fromInvoiceId`, `toInvoiceId`, `mode`) VALUES ( ?, ? , ? )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, invoiceFromId);
            pst.setInt(2, invoiceToId);
            pst.setString(3, mode);

            int rs = pst.executeUpdate();
            if (rs == 1) {

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
}
