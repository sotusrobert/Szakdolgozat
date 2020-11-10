
package InvoiceProgram.Model;

public class InvoiceFile {
    
    private int id;
    private String name;
    private TaxYear taxYear;
    private Division division;
    private TransactionType transactionType;
    private boolean isBankAccountPrintToInv;
    private int initialCounter;
    private String VoucherSign;
    private boolean isActive;

    public InvoiceFile(int id, String name, TaxYear taxYear, Division division, TransactionType transactionType, boolean isBankAccountPrintToInv, int initialCounter, String VoucherSign, boolean isActive) {
        this.id=id;
        this.name = name;
        this.taxYear = taxYear;
        this.division = division;
        this.transactionType = transactionType;
        this.isBankAccountPrintToInv = isBankAccountPrintToInv;
        this.initialCounter = initialCounter;
        this.VoucherSign = VoucherSign;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaxYear getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(TaxYear taxYear) {
        this.taxYear = taxYear;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public boolean isIsBankAccountPrintToInv() {
        return isBankAccountPrintToInv;
    }

    public void setIsBankAccountPrintToInv(boolean isBankAccountPrintToInv) {
        this.isBankAccountPrintToInv = isBankAccountPrintToInv;
    }

    public int getInitialCounter() {
        return initialCounter;
    }

    public void setInitialCounter(int initialCounter) {
        this.initialCounter = initialCounter;
    }

    public String getVoucherSign() {
        return VoucherSign;
    }

    public void setVoucherSign(String VoucherSign) {
        this.VoucherSign = VoucherSign;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "InvoiceFile{" + "id=" + id + ", name=" + name + ", taxYear=" + taxYear + ", division=" + division + ", transactionType=" + transactionType + ", isBankAccountPrintToInv=" + isBankAccountPrintToInv + ", initialCounter=" + initialCounter + ", VoucherSign=" + VoucherSign + ", isActive=" + isActive + '}';
    }
    
    
    
    
}
