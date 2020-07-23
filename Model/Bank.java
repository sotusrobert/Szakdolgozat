package InvoiceProgram.Model;

public class Bank {

    private  int id;
    private  String name;
    private  String swift;
    private  Address address;

    public Bank(int id, String name, String swift, Address address ) {
        this.id = id;
        this.name = name;
        this.swift = swift;
        this.address = address;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSwift() {
        return swift;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Bank{" + "id=" + id + ", name=" + name + ", swift=" + swift + ", Country=" + address.getCountry().getName() + '}';
    }



    
}
