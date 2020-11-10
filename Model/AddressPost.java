
package InvoiceProgram.Model;

public class AddressPost {
    private Country country;
    private Zipcode zip;
    private String address;
    private String region;

    public AddressPost(Country country, Zipcode zip, String address, String region) {
        this.country = country;
        this.zip = zip;
        this.address = address;
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Zipcode getZip() {
        return zip;
    }

    public void setZip(Zipcode zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "AddressPost{" + "country=" + country + ", zip=" + zip + ", address=" + address + ", region=" + region + '}';
    }
    
    
    
}
