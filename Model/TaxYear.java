
package InvoiceProgram.Model;

import java.sql.Date;



public class TaxYear {
    private int id;
    private int parentTaxYearId;
    private String name;
    private Date startDate;
    private Date endDate;
    private String currencyId;
    private int taxYearNumber;
    private boolean isKATA;
    private boolean isClosed;
    private boolean isCashAccounting;
    private String voucherFormat;
    private int voucherDigits;
    private String voucherSepFirst;
    private String voucherSepLast;
    private Date closeDate;

    public TaxYear(int id, int parentTaxYearId, String name, Date startDate, Date endDate, String currencyId, int taxYearNumber, boolean isKATA, boolean isClosed, boolean isCashAccounting, String voucherFormat, int voucherDigits, String voucherSepFirst, String voucherSepLast, Date closeDate) {
        this.id = id;
        this.parentTaxYearId = parentTaxYearId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currencyId = currencyId;
        this.taxYearNumber = taxYearNumber;
        this.isKATA = isKATA;
        this.isClosed = isClosed;
        this.isCashAccounting = isCashAccounting;
        this.voucherFormat = voucherFormat;
        this.voucherDigits = voucherDigits;
        this.voucherSepFirst = voucherSepFirst;
        this.voucherSepLast = voucherSepLast;
        this.closeDate= closeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentTaxYearId() {
        return parentTaxYearId;
    }

    public void setParentTaxYearId(int parentTaxYearId) {
        this.parentTaxYearId = parentTaxYearId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCurrency() {
        return currencyId;
    }

    public void setCurrency(String currencyId) {
        this.currencyId = currencyId;
    }

    public int getTaxYearNumber() {
        return taxYearNumber;
    }

    public void setTaxYearNumber(int taxYearNumber) {
        this.taxYearNumber = taxYearNumber;
    }

    public boolean isIsKATA() {
        return isKATA;
    }

    public void setIsKATA(boolean isKATA) {
        this.isKATA = isKATA;
    }

    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public boolean isIsCashAccounting() {
        return isCashAccounting;
    }

    public void setIsCashAccounting(boolean isCashAccounting) {
        this.isCashAccounting = isCashAccounting;
    }

    public String getVoucherFormat() {
        return voucherFormat;
    }

    public void setVoucherFormat(String voucherFormat) {
        this.voucherFormat = voucherFormat;
    }

    public int getVoucherDigits() {
        return voucherDigits;
    }

    public void setVoucherDigits(int voucherDigits) {
        this.voucherDigits = voucherDigits;
    }

    public String getVoucherSepFirst() {
        return voucherSepFirst;
    }

    public void setVoucherSepFirst(String voucherSepFirst) {
        this.voucherSepFirst = voucherSepFirst;
    }

    public String getVoucherSepLast() {
        return voucherSepLast;
    }

    public void setVoucherSepLast(String voucherSepLast) {
        this.voucherSepLast = voucherSepLast;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    
    
    
}
