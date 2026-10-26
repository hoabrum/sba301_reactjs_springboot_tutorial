package com.springboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
    // Only used on signup, e.g. ["admin", "manager"]; defaults to "user" when empty
    private Set<String> roles;
}
