package com.alkemychallenge.alkemychallenge.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    private String usarname;
    private String password;
}
