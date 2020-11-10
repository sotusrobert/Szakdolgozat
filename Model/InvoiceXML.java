
package InvoiceProgram.Model;

import java.sql.Date;

public class InvoiceXML {
    private String bankkAccountNumber;
    private String currencyId;
    private double currencyRate;
    private String voucherNum;
    private boolean continousFulfilment;
    private String headNoteDefault;
    private Date invoiceDate;
    private Date fulfilmentDate;
    private Date dueDate;
    private String languageId;
    private double invoiceTotal;
    private double invoiceVatTotal;

    public InvoiceXML() {
        super();
    }

    public InvoiceXML(String bankkAccountNumber, String currencyId, double currencyRate, String voucherNum, boolean continousFulfilment, String headNoteDefault, Date invoiceDate, Date fulfilmentDate, Date dueDate, String languageId, double invoiceTotal, double invoiceVatTotal) {
        this.bankkAccountNumber = bankkAccountNumber;
        this.currencyId = currencyId;
        this.currencyRate = currencyRate;
        this.voucherNum = voucherNum;
        this.continousFulfilment = continousFulfilment;
        this.headNoteDefault = headNoteDefault;
        this.invoiceDate = invoiceDate;
        this.fulfilmentDate = fulfilmentDate;
        this.dueDate = dueDate;
        this.languageId = languageId;
        this.invoiceTotal = invoiceTotal;
        this.invoiceVatTotal = invoiceVatTotal;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public double getInvoiceVatTotal() {
        return invoiceVatTotal;
    }

    public void setInvoiceVatTotal(double invoiceVatTotal) {
        this.invoiceVatTotal = invoiceVatTotal;
    }
    
    



    public String getBankkAccountNumber() {
        return bankkAccountNumber;
    }

    public void setBankkAccountNumber(String bankkAccountNumber) {
        this.bankkAccountNumber = bankkAccountNumber;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getVoucherNum() {
        return voucherNum;
    }

    public void setVoucherNum(String voucherNum) {
        this.voucherNum = voucherNum;
    }

    public boolean isContinousFulfilment() {
        return continousFulfilment;
    }

    public void setContinousFulfilment(boolean continousFulfilment) {
        this.continousFulfilment = continousFulfilment;
    }

    public String getHeadNoteDefault() {
        return headNoteDefault;
    }

    public void setHeadNoteDefault(String headNoteDefault) {
        this.headNoteDefault = headNoteDefault;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getFulfilmentDate() {
        return fulfilmentDate;
    }

    public void setFulfilmentDate(Date fulfilmentDate) {
        this.fulfilmentDate = fulfilmentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }
    
    
    
}
