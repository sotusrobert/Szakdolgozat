
package InvoiceProgram.Model;

public class InvoiceNumberCounter {
    private int id;
    private InvoiceFile invoiceFile;
    private int counter;

    public InvoiceNumberCounter(int id, InvoiceFile invoiceFile, int counter) {
        this.id = id;
        this.invoiceFile = invoiceFile;
        this.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InvoiceFile getInvoiceFile() {
        return invoiceFile;
    }

    public void setInvoiceFile(InvoiceFile invoiceFile) {
        this.invoiceFile = invoiceFile;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    
}
