package InvoiceProgram.Model;


import java.util.ArrayList;
import java.util.Date;

public class Country {
    private String id;
    private String name;
    private String languages;
    private boolean isactive;
    private boolean isEUmembership;

    public Country(String id, String name, String languages, boolean isactive, boolean isEUmembership) {
        this.id = id;
        this.name = name;
        this.languages = languages;
        this.isactive = isactive;
        this.isEUmembership = isEUmembership;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isIsEUmembership() {
        return isEUmembership;
    }

    public void setIsEUmembership(boolean isEUmembership) {
        this.isEUmembership = isEUmembership;
    }

    
    
    
    
    
    
    
    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name=" + name + ", languages=" + languages + ", isactive=" + isactive + ", isEUmembership=" + isEUmembership + '}';
    }

  
}
