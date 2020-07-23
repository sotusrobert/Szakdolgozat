package InvoiceProgram.Model;

public class Currency {
    private String id;
    private String name;
    private boolean isActive;

    public Currency(String id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isIsActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "Currency{" + "id=" + id + ", name=" + name + ", isActive=" + isActive + '}';
    }
    
    
    
    
    
    
}
