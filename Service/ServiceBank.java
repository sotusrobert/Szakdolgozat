
package InvoiceProgram.Service;

import InvoiceProgram.Model.Address;
import InvoiceProgram.Model.Bank;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Model.Zipcode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ServiceBank {
    private ArrayList<Bank> banks;
    public void getAllBank(JTable table) {
        banks = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
            String sql = "Select * From bank ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    ServiceCountry country= new ServiceCountry();
                    Country c=country.getOneCountryById(rs.getString("countryId"));
                    ServiceZips zip= new ServiceZips();
                    Zipcode z=zip.getOneZipcode(rs.getInt("zipId"));
                    ServicePublicPlace publicPlace= new ServicePublicPlace();
                    PublicPlace p=publicPlace.getOnePublicPlace(rs.getInt("publicplaceId"));    
                    Address address= new Address(c, rs.getString("regio"), z, rs.getString("publicPlaceName"), p, rs.getInt("publicPlaceNumber"), rs.getString("building"), rs.getString("stairWay"), rs.getString("storey"), rs.getInt("doornumber"));
                    banks.add(new Bank(rs.getInt("id"), rs.getString("name"), rs.getString("name"),address ));

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
                    row[4] = banks.get(i).getAddress().getZip().getCode()+" "+banks.get(i).getAddress().getZip().getName()+", "+banks.get(i).getAddress().getPublicPlaceName()+" "+banks.get(i).getAddress().getPublicPlaceKind().getName()+" "+banks.get(i).getAddress().getPublicPlaceNumber()    ;

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
}
