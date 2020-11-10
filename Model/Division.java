
package InvoiceProgram.Model;

public class Division {
    private int id;
    private String name;
    private CompanyAddress address;

    public Division(int id, String name, CompanyAddress address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public CompanyAddress getAddress() {
        return address;
    }

    public void setAddress(CompanyAddress address) {
        this.address = address;
    }
    
    
}
