
package InvoiceProgram.Model;

public class Department {
    private int id;
    private String name;
    private int partnerId;

    public Department(int id, String name, int partnerId) {
        this.id = id;
        this.name = name;
        this.partnerId = partnerId;
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

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }
    
    
}
