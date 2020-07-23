package InvoiceProgram.Model;

import InvoiceProgram.Service.DatabaseConnection;

public class Language {
    private String id;
    private String nyelv;
    private boolean active;
    private DatabaseConnection conn;

    public Language(String id, String nyelv, boolean active) {
        this.id = id;
        this.nyelv = nyelv;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNyelv() {
        return nyelv;
    }

    public void setNyelv(String nyelv) {
        this.nyelv = nyelv;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        
    }
    
    

    @Override
    public String toString() {
        return "Language{" + "id=" + id + ", nyelv=" + nyelv + ", active=" + active + '}';
    }
    
    
}
