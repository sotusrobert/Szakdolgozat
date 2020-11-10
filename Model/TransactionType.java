
package InvoiceProgram.Model;


public class TransactionType {
    private int id;
    private String name;
    private boolean isactive;

    public TransactionType(int id, String name, boolean isactive) {
        this.id = id;
        this.name = name;
        this.isactive = isactive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    @Override
    public String toString() {
        return "TransactionType{" + "id=" + id + ", name=" + name + ", isactive=" + isactive + '}';
    }
    
}
