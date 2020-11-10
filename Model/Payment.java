
package InvoiceProgram.Model;


public class Payment {
    private int id;
    private String name;
    private int prompt;
    private String mode;
    private boolean isDefault;

    public Payment(int id, String name, int prompt, String mode, boolean isDefault) {
        this.id = id;
        this.name = name;
        this.prompt = prompt;
        this.mode = mode;
        this.isDefault = isDefault;
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

    public int getPrompt() {
        return prompt;
    }

    public void setPrompt(int prompt) {
        this.prompt = prompt;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", name=" + name + ", prompt=" + prompt + ", mode=" + mode + ", isDefault=" + isDefault + '}';
    }
    
    
}
