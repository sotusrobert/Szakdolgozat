
package InvoiceProgram.Model;

public class PartnerAddress {
    private int id;
    private boolean isPartner;
    private int departmentId;
    private Address address;
    private boolean isPostInherited;
    private AddressPost addressPost;

    public PartnerAddress(int id, boolean isPartner, int departmentId, Address address, boolean isPostInherited, AddressPost addressPost) {
        this.id = id;
        this.isPartner = isPartner;
        this.departmentId = departmentId;
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

    public boolean isIsPartner() {
        return isPartner;
    }

    public void setIsPartner(boolean isPartner) {
        this.isPartner = isPartner;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
    
    
}
