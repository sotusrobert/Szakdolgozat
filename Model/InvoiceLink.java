
package InvoiceProgram.Model;

public class InvoiceLink {
    
    private Invoice fromInvoice;
    private Invoice toInvoice;
    private String mode;

    public InvoiceLink(Invoice fromInvoice, Invoice toInvoice, String mode) {
        this.fromInvoice = fromInvoice;
        this.toInvoice = toInvoice;
        this.mode = mode;
    }

    public Invoice getFromInvoice() {
        return fromInvoice;
    }

    public void setFromInvoice(Invoice fromInvoice) {
        this.fromInvoice = fromInvoice;
    }

    public Invoice getToInvoice() {
        return toInvoice;
    }

    public void setToInvoice(Invoice toInvoice) {
        this.toInvoice = toInvoice;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
    
}
