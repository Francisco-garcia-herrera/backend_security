package com.example.backend_security.domain.entities;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private Long id;
    private String name;
    private String username;
    private String password;

    private Set<Role> roles;
}
