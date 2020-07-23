package InvoiceProgram.Model;

public class PublicPlace {
    private int id;
    private String countryId;
    private String name;

    public PublicPlace(int id, String countryId, String name) {
        this.id = id;
        this.countryId = countryId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }

    
    
    
    
    
    @Override
    public String toString() {
        return "PublicPlace{" + "id=" + id + ", countryId=" + countryId + ", name=" + name + '}';
    }
        
}
