package com.alkemychallenge.alkemychallenge.auth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    private String username;

    @Size(min=8)
    private String password;

    private boolean acoountNonExpired;
    private boolean acoountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enable;

    public UserEntity(){
        this.acoountNonExpired=true;
        this.acoountNonLocked=true;
        this.credentialsNonExpired=true;
        this.enable=true;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id=id;}

    @Override
    public String getUsername(){return username;}

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username){this.username=username;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword(){return password;}

    public void setPassword(String password){this.password=password;}

}


