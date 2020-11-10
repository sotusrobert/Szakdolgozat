
package InvoiceProgram.Model;

public class BankAccount {
    private int id;
    private String name;
    private Currency currency;
    private Bank bank;
    private String number;
    private boolean isActive;

    public BankAccount(int id, String name, Currency currency, Bank bank, String number, boolean isActive) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.bank = bank;
        this.number = number;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return name +":"+number ;
    }
    
    
    
    
    
    
}
