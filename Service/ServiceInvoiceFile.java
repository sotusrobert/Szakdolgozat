package InvoiceProgram.Service;

import InvoiceProgram.Model.Division;
import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.TaxYear;
import InvoiceProgram.Model.TransactionType;
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

public class ServiceInvoiceFile {
    
    private ArrayList<InvoiceFile> invoiceFile;
    
    public void getAllInvoiceFile(JTable table) {
        invoiceFile = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "Select * From invoicefile";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServiceTaxYear taxyear = new ServiceTaxYear();
                    TaxYear taxyearid = taxyear.getAllTaxYearById(rs.getInt("taxyearId"));
                    ServiceFirm division = new ServiceFirm();
                    Division divisionid = division.getAllDivision(rs.getInt("taxyearId"));
                    
                    invoiceFile.add(new InvoiceFile(rs.getInt("id"), rs.getString("name"), taxyearid, divisionid, transactiontype, rs.getBoolean("isBankPrint"), rs.getInt("initialCounter"), rs.getString("VoucherSign"), rs.getBoolean("isActive")));
                    
                }
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();
                
                Object[] row = new Object[2];
                for (int i = 0; i < invoiceFile.size(); i++) {
                    row[0] = invoiceFile.get(i).getId();
                    row[1] = invoiceFile.get(i).getName().toUpperCase();
                    
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
    
    public void getAllInvoiceFileByTaxYear(int id, JTable table) {
        invoiceFile = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "SELECT * FROM `invoicefile` WHERE  taxyearId= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServiceTaxYear taxyear = new ServiceTaxYear();
                    TaxYear taxyearid = taxyear.getAllTaxYearById(rs.getInt("taxyearId"));
                    ServiceFirm division = new ServiceFirm();
                    Division divisionid = division.getAllDivision(rs.getInt("taxyearId"));
                    
                    invoiceFile.add(new InvoiceFile(rs.getInt("id"), rs.getString("name"), taxyearid, divisionid, transactiontype, rs.getBoolean("isBankPrint"), rs.getInt("initialCounter"), rs.getString("VoucherSign"), rs.getBoolean("isActive")));
                    
                }
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();
                
                Object[] row = new Object[2];
                for (int i = 0; i < invoiceFile.size(); i++) {
                    row[0] = invoiceFile.get(i).getId();
                    row[1] = invoiceFile.get(i).getName().toUpperCase();
                    
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
    
    public boolean isTaxYearClosed(int id) {
        boolean isClosed= false;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "SELECT isClosed FROM taxyear where id= (Select taxyearId FROM invoicefile where invoicefile.id = ? )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    isClosed= rs.getBoolean("isClosed");
                }
                
              return isClosed;  
                
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
    
    public Integer getInvoiceFileCounter(int id) {
        Integer  counter= 0;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "SELECT counter FROM `invoicenumbercounter` WHERE invoicefileId= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    counter= rs.getInt("counter");
                }
                
              return counter;  
                
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
        return counter;
    }
    
    public boolean isTaxYearClosedById(int id) {
        boolean isClosed= false;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "SELECT isClosed FROM taxyear where id= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    isClosed= rs.getBoolean("isClosed");
                }
                
              return isClosed;  
                
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
    
    public void getAllInvoiceFileSelector(JTable table) {
        invoiceFile = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "Select * From invoicefile";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServiceTaxYear taxyear = new ServiceTaxYear();
                    TaxYear taxyearid = taxyear.getAllTaxYearById(rs.getInt("taxyearId"));
                    ServiceFirm division = new ServiceFirm();
                    Division divisionid = division.getAllDivision(rs.getInt("taxyearId"));
                    
                    invoiceFile.add(new InvoiceFile(rs.getInt("id"), rs.getString("name"), taxyearid, divisionid, transactiontype, rs.getBoolean("isBankPrint"), rs.getInt("initialCounter"), rs.getString("VoucherSign"), rs.getBoolean("isActive")));
                    
                }
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.getDataVector().removeAllElements();
                table.revalidate();
                
                Object[] row = new Object[3];
                for (int i = 0; i < invoiceFile.size(); i++) {
                    row[0] = invoiceFile.get(i).getId();
                    row[1] = invoiceFile.get(i).getName().toUpperCase();
                    row[2] = invoiceFile.get(i).isIsActive();
                    
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
    
    public ArrayList<InvoiceFile> getOneInvoiceFile(int id) {
        ArrayList<InvoiceFile> list = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "Select * From invoicefile where id= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServiceTaxYear taxyear = new ServiceTaxYear();
                    TaxYear taxyearid = taxyear.getAllTaxYearById(rs.getInt("taxyearId"));
                    ServiceFirm division = new ServiceFirm();
                    Division divisionid = division.getAllDivision(rs.getInt("taxyearId"));
                    
                    list.add(new InvoiceFile(rs.getInt("id"), rs.getString("name"), taxyearid, divisionid, transactiontype, rs.getBoolean("isBankPrint"), rs.getInt("initialCounter"), rs.getString("VoucherSign"), rs.getBoolean("isActive")));
                    
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
    
    public InvoiceFile getOneInvoiceFileById(int id) {
        InvoiceFile list = null;
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "Select * From invoicefile where id= ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceTransactionType transaction = new ServiceTransactionType();
                    TransactionType transactiontype = transaction.getOneTransactionById(rs.getInt("transactiontypeId"));
                    ServiceTaxYear taxyear = new ServiceTaxYear();
                    TaxYear taxyearid = taxyear.getAllTaxYearById(rs.getInt("taxyearId"));
                    ServiceFirm division = new ServiceFirm();
                    Division divisionid = division.getAllDivision(rs.getInt("taxyearId"));
                    
                    list = new InvoiceFile(rs.getInt("id"), rs.getString("name"), taxyearid, divisionid, transactiontype, rs.getBoolean("isBankPrint"), rs.getInt("initialCounter"), rs.getString("VoucherSign"), rs.getBoolean("isActive"));
                    
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
    
    public void newInvoiceFile(JFrame view, String name, int taxyearId, int divisionId, int transactiontypeId, boolean isBankPrint, int initialCounter, String VoucherSign, boolean isActive) {
        invoiceFile = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "INSERT INTO `invoicefile`( `name`, `taxyearId`, `divisionId`, `transactiontypeId`, `isBankPrint`, `initialCounter`, `VoucherSign`, `isActive`) VALUES ( ?, ? , ? , ? , ? , ? , ? , ? )";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, taxyearId);
            pst.setInt(3, divisionId);
            pst.setInt(4, transactiontypeId);
            if (isBankPrint) {
                pst.setInt(5, 1);
            } else {
                pst.setInt(5, 0);
            }
            pst.setInt(6, initialCounter);
            pst.setString(7, VoucherSign);
            if (isActive) {
                pst.setInt(8, 1);
            } else {
                pst.setInt(8, 0);
            }
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
    
    public void updateInvoiceFile(JFrame view, String name, int taxyearId, int divisionId, int transactiontypeId, boolean isBankPrint, int initialCounter, String VoucherSign, boolean isActive, int id) {
        invoiceFile = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "UPDATE `invoicefile` SET `name`= ? ,`taxyearId`= ? ,`divisionId`= ? ,`transactiontypeId`= ? ,`isBankPrint`= ? ,`initialCounter`= ? ,`VoucherSign`= ? ,`isActive`= ?  WHERE id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, taxyearId);
            pst.setInt(3, divisionId);
            pst.setInt(4, transactiontypeId);
            if (isBankPrint) {
                pst.setInt(5, 1);
            } else {
                pst.setInt(5, 0);
            }
            pst.setInt(6, initialCounter);
            pst.setString(7, VoucherSign);
            if (isActive) {
                pst.setInt(8, 1);
            } else {
                pst.setInt(8, 0);
            }
            pst.setInt(9, id);
            int rs = pst.executeUpdate();
            if (rs == 1) {
                JOptionPane.showMessageDialog(view, "A rekord módosítása sikeres!", "Adat rögzítés", JOptionPane.INFORMATION_MESSAGE);
                
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
    
    public void deleteInvoiceFile(JFrame view, int id) {
        invoiceFile = new ArrayList<>();
        Connection conn = null;
        DatabaseConnection db = new DatabaseConnection();
        try {
            
            conn = db.getConnection();
            String sql = "DELETE FROM `invoicefile` WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            
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
}
