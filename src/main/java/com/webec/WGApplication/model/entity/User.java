package com.webec.WGApplication.model.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    protected User() {}

    public User(String username, String password, Set<String> roles) {
        this.username = requireNonNull(username);
        this.password = requireNonNull(password);
        this.roles = requireNonNull(roles);
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(toSet());
    }

    public int getId() { return id; }

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

}
