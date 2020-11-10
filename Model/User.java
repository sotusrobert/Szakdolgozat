package InvoiceProgram.Model;

import InvoiceProgram.Service.DatabaseConnection;

public class User {
    private int id;
    private String username;
    private String password;
    private String permission;
    private Boolean isActive;
    private DatabaseConnection conn;

    public User( int id, String username, String password, String permission, Boolean isActive ) {
        this.id=id ;
        this.username = username;
        this.password = password;
        this.permission = permission;
        this.isActive = isActive;
        
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

}
