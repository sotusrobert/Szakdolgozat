
package InvoiceProgram.Service;

import InvoiceProgram.Model.InvoiceObject;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class ServiceInvoiceObjectToXML {
    private InvoiceObject invocieObject;    
    
    public ServiceInvoiceObjectToXML(InvoiceObject invocieObject) {
        
           try {  
               JAXBContext contextObj =JAXBContext.newInstance(InvoiceObject.class);
               Marshaller marshallerObj = contextObj.createMarshaller();
               marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
               this.invocieObject=invocieObject;
               String XMLname= invocieObject.getInvoice().getVoucherNumber();
               marshallerObj.marshal(invocieObject, new FileOutputStream(XMLname+".xml"));  
               
           } catch (JAXBException | FileNotFoundException ex) {
               Logger.getLogger(ServiceInvoiceObjectToXML.class.getName()).log(Level.SEVERE, null, ex);
           }
          
    }
       
       
       
}

