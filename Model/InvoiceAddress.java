
package InvoiceProgram.Model;

public class InvoiceAddress {
    private int id;
    private Invoice invoice;
    private Partner partner;
    private Department department;

    public InvoiceAddress(int id, Invoice invoice, Partner partner) {
        this.id = id;
        this.invoice = invoice;
        this.partner = partner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
    
}


