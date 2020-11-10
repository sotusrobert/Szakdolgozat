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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return id;
    }
    
    
    
    
    
    
}
