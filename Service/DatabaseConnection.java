package InvoiceProgram.Service;

import InvoiceProgram.Logger.InvoiceLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseConnection {

    private Connection conn;
    private InvoiceLog log;

    public DatabaseConnection() {
        this.log= new InvoiceLog();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoiceprogram", "root", "");
        } catch (ClassNotFoundException ex) {
            
            log.log(Level.SEVERE, ex.getMessage());
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        }
        

    }

    public Connection getConnection() {
        try {
            
            return this.conn;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public void scriptRunner() {
        BufferedReader reader= null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(con);
            //Creating a reader object
            File file= new File("src\\main\\java\\DB\\invoiceprogram.sql");
            Path path = Paths.get("src\\main\\java\\DB\\invoiceprogram.sql");
            
           
            
            
            System.out.println(path.toAbsolutePath().toString());
            FileInputStream fis = new FileInputStream(path.toAbsolutePath().toString());
            
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            reader = new BufferedReader(isr);
            //reader = new BufferedReader(new FileReader("src\\main\\java\\DB\\invoiceprogram.sql"));
            //System.out.println(reader.toString());
            //Running the script
            sr.setAutoCommit(true);
            sr.setStopOnError(true);
            sr.runScript(reader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, ex.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
                log.log(Level.SEVERE, ex.getMessage());
            }
        }
    
    }
    

}
