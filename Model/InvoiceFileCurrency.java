
package InvoiceProgram.Model;

public class InvoiceFileCurrency {
    private int id;
    private InvoiceFile invoiceFile;
    private Currency currency;
    private BankAccount bankAccount;
    private boolean isDefault;
    private boolean roundItem;
    private boolean roundVAT;
    private boolean roundTotal;

    public InvoiceFileCurrency(int id,InvoiceFile invoiceFile, Currency currency, BankAccount bankAccount, boolean isDefault, boolean roundItem, boolean roundVAT, boolean roundTotal) {
        this.id = id;
        this.invoiceFile=invoiceFile;
        this.currency = currency;
        this.bankAccount = bankAccount;
        this.isDefault = isDefault;
        this.roundItem = roundItem;
        this.roundVAT = roundVAT;
        this.roundTotal = roundTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean isRoundItem() {
        return roundItem;
    }

    public void setRoundItem(boolean roundItem) {
        this.roundItem = roundItem;
    }

    public boolean isRoundVAT() {
        return roundVAT;
    }

    public void setRoundVAT(boolean roundVAT) {
        this.roundVAT = roundVAT;
    }

    public boolean isRoundTotal() {
        return roundTotal;
    }

    public void setRoundTotal(boolean roundTotal) {
        this.roundTotal = roundTotal;
    }

    public InvoiceFile getInvoiceFile() {
        return invoiceFile;
    }

    public void setInvoiceFile(InvoiceFile invoiceFile) {
        this.invoiceFile = invoiceFile;
    }
    
    
}
