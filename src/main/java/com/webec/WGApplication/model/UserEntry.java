package com.webec.WGApplication.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserEntry {
    public int id;
    public String username;
    public String password;
    public Collection<? extends GrantedAuthority> roles;


    public UserEntry(
            int id,
            String username,
            String password,
            Collection<? extends GrantedAuthority> roles
    ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;

    }
}
