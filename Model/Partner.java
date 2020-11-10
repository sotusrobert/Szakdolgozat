
package InvoiceProgram.Model;


public class Partner {
    private int id;
    private String shortName;
    private String fullName;
    private String nameOnVoucher;
    private boolean isActive;
    private String VATNumber;
    private String VATNumberEU;
    private String VATNumberGroup;
    private TransactionType transactionType;
    private Payment defaultPayment;
    private Currency defaultCurrency;
    private boolean isVATRegistrated;
    private boolean isCashAccounting;
    private boolean isKATA;
    private boolean isNaturalPerson;

    public Partner(int id, String shortName, String fullName, String nameOnVoucher, boolean isActive, String VATNumber, String VATNumberEU, String VATNumberGroup, TransactionType transactionType, Payment defaultPayment, Currency defaultCurrency, boolean isVATRegistrated, boolean isCashAccounting, boolean isKATA, boolean isNaturalPerson) {
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
        this.nameOnVoucher = nameOnVoucher;
        this.isActive = isActive;
        this.VATNumber = VATNumber;
        this.VATNumberEU = VATNumberEU;
        this.VATNumberGroup = VATNumberGroup;
        this.transactionType = transactionType;
        this.defaultPayment = defaultPayment;
        this.defaultCurrency = defaultCurrency;
        this.isVATRegistrated = isVATRegistrated;
        this.isCashAccounting = isCashAccounting;
        this.isKATA = isKATA;
        this.isNaturalPerson = isNaturalPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNameOnVoucher() {
        return nameOnVoucher;
    }

    public void setNameOnVoucher(String nameOnVoucher) {
        this.nameOnVoucher = nameOnVoucher;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getVATNumber() {
        return VATNumber;
    }

    public void setVATNumber(String VATNumber) {
        this.VATNumber = VATNumber;
    }

    public String getVATNumberEU() {
        return VATNumberEU;
    }

    public void setVATNumberEU(String VATNumberEU) {
        this.VATNumberEU = VATNumberEU;
    }

    public String getVATNumberGroup() {
        return VATNumberGroup;
    }

    public void setVATNumberGroup(String VATNumberGroup) {
        this.VATNumberGroup = VATNumberGroup;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Payment getDefaultPayment() {
        return defaultPayment;
    }

    public void setDefaultPayment(Payment defaultPayment) {
        this.defaultPayment = defaultPayment;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public boolean isIsVATRegistrated() {
        return isVATRegistrated;
    }

    public void setIsVATRegistrated(boolean isVATRegistrated) {
        this.isVATRegistrated = isVATRegistrated;
    }

    public boolean isIsCashAccounting() {
        return isCashAccounting;
    }

    public void setIsCashAccounting(boolean isCashAccounting) {
        this.isCashAccounting = isCashAccounting;
    }

    public boolean isIsKATA() {
        return isKATA;
    }

    public void setIsKATA(boolean isKATA) {
        this.isKATA = isKATA;
    }

    public boolean isIsNaturalPerson() {
        return isNaturalPerson;
    }

    public void setIsNaturalPerson(boolean isNaturalPerson) {
        this.isNaturalPerson = isNaturalPerson;
    }
    
    
}
