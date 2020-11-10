
package InvoiceProgram.Model;

public class Firm {
    private int id;
    private String shorName;
    private String fullName;
    private String voucherName;
    private String VATNumber;
    private String VATNumberEU;
    private String VATNumberGroup;

    public Firm(int id, String shorName, String fullName, String voucherName, String VATNumber, String VATNumberEU, String VATNumberGroup) {
        this.id=id;
        this.shorName = shorName;
        this.fullName = fullName;
        this.voucherName = voucherName;
        this.VATNumber = VATNumber;
        this.VATNumberEU = VATNumberEU;
        this.VATNumberGroup = VATNumberGroup;
    }

    public String getShorName() {
        return shorName;
    }

    public void setShorName(String shorName) {
        this.shorName = shorName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Firm{" + "shorName=" + shorName + ", fullName=" + fullName + ", voucherName=" + voucherName + ", VATNumber=" + VATNumber + ", VATNumberEU=" + VATNumberEU + ", VATNumberGroup=" + VATNumberGroup + '}';
    }
    
    

   
    
    
}
