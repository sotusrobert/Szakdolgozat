
package InvoiceProgram.Model;

public class InvoiceItem {
    private int id;
    private Invoice invoice;
    private int itemNumber;
    private Tax taxType;
    private String descriptionDefault;
    private String measureDefault;
    private int quantity;
    private double unitPrice;
    private double amount;
    private double tax;
    private double total;
    private double currencyUnitPrice;
    private double currencyAmount;
    private double currencyTax;
    private double currencyTotal;

    public InvoiceItem() {
        super();
    }

    
    public InvoiceItem(int id, Invoice invoice, int itemNumber, Tax taxType, String descriptionDefault, String measureDefault, int quantity, double unitPrice, double amount, double tax, double total, double currencyUnitPrice, double currencyAmount, double currencyTax, double currencyTotal) {
        this.id = id;
        this.invoice = invoice;
        this.itemNumber = itemNumber;
        this.taxType = taxType;
        this.descriptionDefault = descriptionDefault;
        this.measureDefault = measureDefault;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.tax = tax;
        this.total = total;
        this.currencyUnitPrice = currencyUnitPrice;
        this.currencyAmount = currencyAmount;
        this.currencyTax = currencyTax;
        this.currencyTotal = currencyTotal;
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

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Tax getTaxType() {
        return taxType;
    }

    public void setTaxType(Tax taxType) {
        this.taxType = taxType;
    }

    public String getDescriptionDefault() {
        return descriptionDefault;
    }

    public void setDescriptionDefault(String descriptionDefault) {
        this.descriptionDefault = descriptionDefault;
    }

    public String getMeasureDefault() {
        return measureDefault;
    }

    public void setMeasureDefault(String measureDefault) {
        this.measureDefault = measureDefault;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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

    public double getCurrencyUnitPrice() {
        return currencyUnitPrice;
    }

    public void setCurrencyUnitPrice(double currencyUnitPrice) {
        this.currencyUnitPrice = currencyUnitPrice;
    }

    public double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public double getCurrencyTax() {
        return currencyTax;
    }

    public void setCurrencyTax(double currencyTax) {
        this.currencyTax = currencyTax;
    }

    public double getCurrencyTotal() {
        return currencyTotal;
    }

    public void setCurrencyTotal(double currencyTotal) {
        this.currencyTotal = currencyTotal;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" + "id=" + id + ", invoice=" + invoice + ", itemNumber=" + itemNumber + ", taxType=" + taxType + ", descriptionDefault=" + descriptionDefault + ", measureDefault=" + measureDefault + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", amount=" + amount + ", tax=" + tax + ", total=" + total + ", currencyUnitPrice=" + currencyUnitPrice + ", currencyAmount=" + currencyAmount + ", currencyTax=" + currencyTax + ", currencyTotal=" + currencyTotal + '}';
    }
    
    
}
