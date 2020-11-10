
package InvoiceProgram.Model;

import InvoiceProgram.Service.DateAdapter;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
@XmlRootElement 
public class InvoiceObject {
    
    private Firm firm;
    private CompanyAddress companyAddress;
    private Partner partner;
    private PartnerAddress partnerAddress;
    private Invoice invoice;
    private ArrayList<InvoiceItem> invoiceItems;
    private InvoiceFile invoiceFile;
    private TaxYear taxYear;

    public InvoiceObject() {
        super();
    }

    public InvoiceObject(Firm firm, CompanyAddress companyAddress, Partner partner, 
            PartnerAddress partnerAddress, Invoice invoice, ArrayList<InvoiceItem> invoiceItems, 
            InvoiceFile invoiceFile, TaxYear taxYear) {
        this.firm = firm;
        this.companyAddress = companyAddress;
        this.partner = partner;
        this.partnerAddress = partnerAddress;
        this.invoice = invoice;
        this.invoiceItems = invoiceItems;
        this.invoiceFile=invoiceFile; 
        this.taxYear=taxYear;
    }

    @XmlElement  
    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
    @XmlElement
    public CompanyAddress getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(CompanyAddress companyAddress) {
        this.companyAddress = companyAddress;
    }
    @XmlElement
    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
    @XmlElement
    public PartnerAddress getPartnerAddress() {
        return partnerAddress;
    }
    
    public void setPartnerAddress(PartnerAddress partnerAddress) {
        this.partnerAddress = partnerAddress;
    }
    
    public Invoice getInvoice() {
        return invoice;
    }
    @XmlElement
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    @XmlElement
    public ArrayList<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(ArrayList<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
    @XmlElement
    public InvoiceFile getInvoiceFile() {
        return invoiceFile;
    }

    public void setInvoiceFile(InvoiceFile invoiceFile) {
        this.invoiceFile = invoiceFile;
    }
    @XmlElement
    public TaxYear getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(TaxYear taxYear) {
        this.taxYear = taxYear;
    }

    @Override
    public String toString() {
        return "InvoiceObject{" + "firm=" + firm + ", companyAddress=" + companyAddress + ", partner=" + partner + ", partnerAddress=" + partnerAddress + ", invoice=" + invoice + ", invoiceItems=" + invoiceItems + ", invoiceFile=" + invoiceFile + ", taxYear=" + taxYear + '}';
    }
    
    
    
    
    
    
    
    
}
