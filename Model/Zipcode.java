package InvoiceProgram.Model;

public class Zipcode {
    private int id;
    private String countryId;
    private String code;
    private String name;

    
    public Zipcode(int id, String countryId, String code, String name) {
        this.id = id;
        this.countryId = countryId;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Zipcode{" + "id=" + id + ", countryId=" + countryId + ", code=" + code + ", name=" + name + '}';
    }
    
    
    
}
