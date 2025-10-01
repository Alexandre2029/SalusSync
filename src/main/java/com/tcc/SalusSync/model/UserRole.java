package com.tcc.SalusSync.model;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    MEDICO("medico");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
