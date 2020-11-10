
package InvoiceProgram.Model;


public class CompanyAddress {
    private int id;
    private boolean isFirm;
    private int divisionId;
    private Address address;
    private boolean isPostInherited;
    private AddressPost addressPost;

    public CompanyAddress(int id, boolean isFirm, int divisionId, Address address, boolean isPostInherited, AddressPost addressPost) {
        this.id = id;
        this.isFirm = isFirm;
        this.divisionId = divisionId;
        this.address = address;
        this.isPostInherited = isPostInherited;
        this.addressPost = addressPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsFirm() {
        return isFirm;
    }

    public void setIsFirm(boolean isFirm) {
        this.isFirm = isFirm;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isIsPostInherited() {
        return isPostInherited;
    }

    public void setIsPostInherited(boolean isPostInherited) {
        this.isPostInherited = isPostInherited;
    }

    public AddressPost getAddressPost() {
        return addressPost;
    }

    public void setAddressPost(AddressPost addressPost) {
        this.addressPost = addressPost;
    }

    @Override
    public String toString() {
        return "CompanyAddress{" + "id=" + id + ", isFirm=" + isFirm + ", divisionId=" + divisionId + ", address=" + address + ", isPostInherited=" + isPostInherited + ", addressPost=" + addressPost + '}';
    }
    
    
}
