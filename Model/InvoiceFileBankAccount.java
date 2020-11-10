
package InvoiceProgram.Model;

public class InvoiceFileBankAccount {
    private int id;
    private InvoiceFile invoiceFile;
    private BankAccount bankAccount;

    public InvoiceFileBankAccount(int id, InvoiceFile invoiceFile, BankAccount bankAccount) {
        this.id = id;
        this.invoiceFile = invoiceFile;
        this.bankAccount = bankAccount;
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    
}
