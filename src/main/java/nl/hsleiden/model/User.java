package nl.hsleiden.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.security.Principal;

public class User implements Principal {
    private String email;
    private String password;
    private boolean admin;

    public User(String email,String password, boolean admin) {
        this.email = email;
        this.password = password;
        this.admin = admin;
    }
    public User(){
    }
    @Override
    @JsonIgnore
    public String getName() {
        return null;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
