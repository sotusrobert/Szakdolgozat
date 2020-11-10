package InvoiceProgram.Model;

import InvoiceProgram.Service.DateAdapter;
import java.sql.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Invoice {
    private int id;
    private InvoiceFile invocieFile;
    private BankAccount bankAccount;
    private Currency currency;
    private Language language;
    private double currencyRate;
    private TransactionType transactionType;
    private String voucherNumber;
    private Payment payment;
    private Date invoiceDate;
    private Date fulfilmentDate;
    private Date dueDate;
    private int roundingValue;
    private String headNote;
    private int cancellationState;
    private double amount;
    private double tax;
    private double total;

    public Invoice() {
        
    }

    
    
    public Invoice(int id, InvoiceFile invocieFile, BankAccount bankAccount, Currency currency, Language language, double currencyRate, TransactionType transactionType, String voucherNumber, Payment payment, Date invoiceDate, Date fulfilmentDate, Date dueDate, int roundingValue, String headNote, int cancellationState, double amount, double tax, double total) {
        this.id = id;
        this.invocieFile = invocieFile;
        this.bankAccount = bankAccount;
        this.currency = currency;
        this.language = language;
        this.currencyRate = currencyRate;
        this.transactionType = transactionType;
        this.voucherNumber = voucherNumber;
        this.payment = payment;
        this.invoiceDate = invoiceDate;
        this.fulfilmentDate = fulfilmentDate;
        this.dueDate = dueDate;
        this.roundingValue = roundingValue;
        this.headNote = headNote;
        this.cancellationState = cancellationState;
        this.amount = amount;
        this.tax = tax;
        this.total = total;
    }

    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InvoiceFile getInvocieFile() {
        return invocieFile;
    }

    public void setInvocieFile(InvoiceFile invocieFile) {
        this.invocieFile = invocieFile;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getFulfilmentDate() {
        return fulfilmentDate;
    }
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setFulfilmentDate(Date fulfilmentDate) {
        this.fulfilmentDate = fulfilmentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getRoundingValue() {
        return roundingValue;
    }

    public void setRoundingValue(int roundingValue) {
        this.roundingValue = roundingValue;
    }

    public String getHeadNote() {
        return headNote;
    }

    public void setHeadNote(String headNote) {
        this.headNote = headNote;
    }

    public int getCancellationState() {
        return cancellationState;
    }

    public void setCancellationState(int cancellationState) {
        this.cancellationState = cancellationState;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", invocieFile=" + invocieFile + ", bankAccount=" + bankAccount + ", currency=" + currency + ", language=" + language + ", currencyRate=" + currencyRate + ", transactionType=" + transactionType + ", voucherNumber=" + voucherNumber + ", payment=" + payment + ", invoiceDate=" + invoiceDate + ", fulfilmentDate=" + fulfilmentDate + ", dueDate=" + dueDate + ", roundingValue=" + roundingValue + ", headNote=" + headNote + ", cancellationState=" + cancellationState + ", amount=" + amount + ", tax=" + tax + ", total=" + total + '}';
    }
    
    
}
