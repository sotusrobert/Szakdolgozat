
package InvoiceProgram.Model;


import java.sql.Date;

public class Tax {
    private int id;
    private String name;
    private String shortName;
    private int rate;
    private Date validFrom;
    private Date validUntil;

    public Tax(int id, String name, String shortName, int rate, Date validFrom, Date validUntil) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.rate = rate;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public String toString() {
        return rate + "[%]";
    }
    
    
    
}
