package InvoiceProgram.Model;

public class Address {
    private  Country country;
    private  String regio;
    private  Zipcode zip;
    private  String publicPlaceName;
    private  PublicPlace publicPlaceKind;
    private  int publicPlaceNumber;
    private  String building;
    private  String stairWay;
    private  String storey;
    private  int doornumber;
    private String district;

    public Address(Country country, String regio, Zipcode zip, String publicPlaceName, PublicPlace publicPlaceKind, int publicPlaceNumber, String building, String stairWay, String storey, int doornumber, String district) {
        this.country = country;
        this.regio = regio;
        this.zip = zip;
        this.publicPlaceName = publicPlaceName;
        this.publicPlaceKind = publicPlaceKind;
        this.publicPlaceNumber = publicPlaceNumber;
        this.building = building;
        this.stairWay = stairWay;
        this.storey = storey;
        this.doornumber = doornumber;
        this.district=district;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
    }

    public Zipcode getZip() {
        return zip;
    }

    public void setZip(Zipcode zip) {
        this.zip = zip;
    }

    public String getPublicPlaceName() {
        return publicPlaceName;
    }

    public void setPublicPlaceName(String publicPlaceName) {
        this.publicPlaceName = publicPlaceName;
    }

    public PublicPlace getPublicPlaceKind() {
        return publicPlaceKind;
    }

    public void setPublicPlaceKind(PublicPlace publicPlaceKind) {
        this.publicPlaceKind = publicPlaceKind;
    }

    public int getPublicPlaceNumber() {
        return publicPlaceNumber;
    }

    public void setPublicPlaceNumber(int publicPlaceNumber) {
        this.publicPlaceNumber = publicPlaceNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStairWay() {
        return stairWay;
    }

    public void setStairWay(String stairWay) {
        this.stairWay = stairWay;
    }

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public int getDoornumber() {
        return doornumber;
    }

    public void setDoornumber(int doornumber) {
        this.doornumber = doornumber;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    
    @Override
    public String toString() {
        return "Address{" + "country=" + country.getName() +  ", regio=" + regio + ", city=" + zip.getName() + ", zip=" + zip.getCode() + ", publicPlaceName=" + publicPlaceName + ", publicPlaceKind=" + publicPlaceKind.getName() + ", publicPlaceNumber=" + publicPlaceNumber + ", building=" + building + ", stairWay=" + stairWay + ", storey=" + storey + ", doornumber=" + doornumber + '}';
    }
    
    

    
    
    
    
    
    
}
